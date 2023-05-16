package com.softarex.technical_proj.controllers;

import com.softarex.technical_proj.entities.User;
import com.softarex.technical_proj.exceptions.ServiceException;
import com.softarex.technical_proj.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public final class AccessController {
    private final UserService userService;

    public AccessController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login-error")
    public String loginError(HttpServletRequest request, Model model){
       /* HttpSession session = request.getSession(false);
        String errorMessage = null;

        if (session != null){
            AuthenticationException ex = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null){
                errorMessage = ex.getMessage();
            }
        }
        System.out.println("--------" + errorMessage);
        model.addAttribute("errorMessage", errorMessage);*/
        return "login";
    }

    @GetMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute("userBox", new User());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String tryAddUser(@ModelAttribute("userBox") User user, Model model){
        try {
            userService.saveUser(user);
            return "login";
        }catch (ServiceException ex){
            model.addAttribute("errorMessage", ex.getMessage());
            return "signUp";
        }
    }
}
