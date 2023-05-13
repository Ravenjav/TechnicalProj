package com.softarex.technical_proj.controllers;

import com.softarex.technical_proj.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public final class SettingsController {

    @GetMapping("editProfile")
    public String editProfile(HttpServletRequest request){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.toString());
        return "edit";
    }
}
