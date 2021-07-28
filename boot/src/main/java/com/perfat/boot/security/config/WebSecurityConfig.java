/*
 * Chsi
 * Created on 2019-04-18
 */
package com.perfat.boot.security.config;

import com.perfat.boot.security.service.impl.CustomUserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    UserDetailsService customUserService() {
        return new CustomUserServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 1、匹配路径的时候需要去除根路径。不然匹配不到
     * 2、权限名称默认加上ROLE_,配置的不需要再加上ROLE_,加上会直接报异常的
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//http.authorizeRequests() 方法中的自定义匹配
                .antMatchers("/index.jsp", "/anonymous/**", "/upload", "/ces", "/stripe/**", "/js/**").permitAll()//定义哪些请求不用拦截保护
                .antMatchers("/admin/**").hasAnyRole("SECURITY_ADMIN", "CUSTOMER")

                .anyRequest().authenticated()//任何请求，登录后可以访问
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(loginSuccessHandler())
                        .failureUrl("/login?error=true")
                        .permitAll()//登录页面用户任意访问
                .and()
                    .logout()
                    .logoutUrl("/logout") //指定注销路径
                    .logoutSuccessUrl("/boot/show") //指定成功注销后的跳转路径
                    .logoutSuccessHandler(logoutSuccessHandler()) //指定成功注销后的处理类
                    .deleteCookies("JSESSIONID")
                    .permitAll();//注销任意访问
        //关闭打开的csrf保护
        http.csrf().disable();
        //csrfTokenRepository(new CookieCsrfTokenRepository()).configure(http);
    }

    /**
     * //登出处理
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                System.out.println("退出成功！");
                httpServletResponse.sendRedirect("/boot/show");
            }
        };
    }

    /**
     * 登录成功进入
     */
    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler() { //登入处理
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                User userDetails = (User) authentication.getPrincipal();
                logger.info("USER : " + userDetails.getUsername() + " LOGIN SUCCESS !  ");
                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }
}
