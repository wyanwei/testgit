/*
 * Chsi
 * Created on 2019-04-18
 */
package com.perfat.boot.security.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登陆控制页面
 *
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
@Controller
@Scope("prototype")
public class LoginController {

    /**
     * 登录
     * <p>
     * 1.未登录跳转到登录页面
     * 2.登录成功后跳转到首页
     */
    @RequestMapping(value = "/login")
    public String index() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            return "jsp/login";
        }
        return "redirect:/show";
    }

    @RequestMapping(value = "/show")
    public String show(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        return "jsp/index";
    }

    @RequestMapping(value = "/ceshi")
    public String showHtml() {
        return "html/index";
    }
}
