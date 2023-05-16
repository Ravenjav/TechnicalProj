package com.softarex.technical_proj.controllers;

import com.softarex.technical_proj.entities.Question;
import com.softarex.technical_proj.entities.User;
import com.softarex.technical_proj.services.QuestionService;
import com.softarex.technical_proj.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public final class MainController {

    private final UserService userService;
    private final QuestionService questionService;

    public MainController(UserService userService, QuestionService questionService) {
        this.userService = userService;
        this.questionService = questionService;
    }

    @GetMapping("/questions")
    public String questionPage(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Page<Question> page = questionService.findSendQuestionsByUsername(user.getUsername(), pageable);*/
        List<Question> page = new ArrayList<>();
        page = questionService.findAll();
        model.addAttribute("questions", page);
        System.out.println(page.size());
        System.out.println("---------------------------------------------");
        return "questions";
    }
}
