package com.softarex.technical_proj.controllers;

import com.softarex.technical_proj.entities.User;
import com.softarex.technical_proj.entities.UserInfo;
import com.softarex.technical_proj.exceptions.ServiceException;
import com.softarex.technical_proj.services.QuestionService;
import com.softarex.technical_proj.services.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public final class ProfileSettingsController {

    private final UserService userService;

    private final QuestionService questionService;

    private final PasswordEncoder passwordEncoder;

    public ProfileSettingsController(UserService userService, QuestionService questionService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.questionService = questionService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("editProfile")
    public String editProfile(HttpServletRequest request, Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userInfo", user.getUserInfo());
        return "editProfile";
    }

    @PostMapping("editProfile")
    public String editProfile(HttpServletRequest request, @ModelAttribute("userInfo") UserInfo userInfo, Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{
            user = userService.setNewUserInfo(user, userInfo);
        }
        catch (ServiceException ex){
            model.addAttribute("userBox", user);
            model.addAttribute("errorMessage", ex.getMessage());
            return "editProfile";
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/questions";
    }

    @GetMapping("deleteProfile")
    public String deleteProfile(){
        return "deleteProfile";
    }

    @PostMapping("deleteProfile")
    public String deleteProfile(@ModelAttribute("password") String password, HttpServletRequest request,
                                HttpServletResponse response, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (!passwordEncoder.matches(password, user.getPassword())){
            model.addAttribute( "errorMessage", "wrong password!");
            return "deleteProfile";
        }
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        userService.deleteUser(user);
        return "redirect:/login";
    }
}
