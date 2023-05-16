package com.softarex.technical_proj.controllers;

import com.softarex.technical_proj.entities.Question;
import com.softarex.technical_proj.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public final class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/questions")
    public void questionPage(Model model){
        List<Question> questions;
    }
}
