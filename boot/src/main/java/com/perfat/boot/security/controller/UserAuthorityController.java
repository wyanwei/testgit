/*
 * Chsi
 * Created on 2019-04-26
 */
package com.perfat.boot.security.controller;

import com.perfat.boot.security.UserAuthorityModel;
import com.perfat.boot.security.entity.UserInfoData;
import com.perfat.boot.security.service.SecurityService;
import com.perfat.boot.security.vo.UserInfoVo;
import com.perfat.boot.util.BootUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户权限控制管理
 *
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id: UserAuthorityController.java 16 2019-08-07 08:28:05Z 二进制 $
 */
@Controller
@RequestMapping(value = "/admin/auth")
public class UserAuthorityController {
    private final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/show")
    public String showUser(Model model) {
        List<UserInfoVo> userInfoVoList = securityService.getAllUserInfoList();
        model.addAttribute("userInfoVo", userInfoVoList);
        return "security/index";
    }

    @RequestMapping(value = "/showadd")
    public String showAddUser(Model model) {
        model.addAttribute("userAuthorityModel", new UserAuthorityModel());
        return "security/add";
    }

    @RequestMapping(value = "/create")
    public String addUser(@Valid UserAuthorityModel userAuthorityModel, BindingResult bindResult, Model model) {
        if ( bindResult.hasErrors() ) {
            return "security/add";
        }

        UserInfoData userInfoData = securityService.getUserInfoDataByUsername(userAuthorityModel.getUsername());
        if ( null != userInfoData ) {
            model.addAttribute("username", "该用户名已存在");
            return "security/add";
        }

        userInfoData = new UserInfoData();
        userInfoData.setPhone(userAuthorityModel.getPhone());
        userInfoData.setEmail(userAuthorityModel.getEmail());
        userInfoData.setPassword(PASSWORD_ENCODER.encode(userAuthorityModel.getPassword()));
        userInfoData.setUsername(userAuthorityModel.getUsername());

        securityService.saveOrUpdate(userInfoData, userAuthorityModel.getRole(), userAuthorityModel.getDesc());
        return "redirect:/admin/auth/show";
    }

    @RequestMapping(value = "/delete")
    public String deleteUser(String id, Model model) {
        securityService.deleteUserInfoDataById(id);
        return "redirect:/admin/auth/show";
    }

    @RequestMapping(value = "/edit")
    public String editUser(String id, Model model) {
        UserInfoVo userInfoVo = securityService.getUserInfoById(id);
        model.addAttribute("userInfoVo", userInfoVo);
        return "redirect:/admin/auth/show";
    }
}
