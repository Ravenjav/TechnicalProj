package com.softarex.technical_proj.controllers;

import com.softarex.technical_proj.entities.Question;
import com.softarex.technical_proj.entities.User;
import com.softarex.technical_proj.services.QuestionService;
import com.softarex.technical_proj.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        //System.out.println(page.size());
        //System.out.println("---------------------------------------------");
        return "questions";
    }

    @GetMapping("/addQuestion")
    public String addQuestion(Model model){
        Question question = new Question();
        model.addAttribute("question", question);
        return "add_question";
    }

    @PostMapping("/addQuestion")
    public String addQuestion(Model model, @ModelAttribute("question") Question question, @ModelAttribute("forUser") String forUser){
        User responsible = userService.findUserById(forUser);
        if (responsible == null){
            model.addAttribute("question", question);
            model.addAttribute("errorMessage", "User doesn't exist");
            return "add_question";
        }
        question.setResponsible(responsible);
        User sender = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        question.setSender(sender);
        System.out.println("---------------------");
        System.out.println(question.getViewType());
        questionService.createQuestion(question);
        return "redirect:/questions";
    }

    @PostMapping("/changeQuestionAfter")
    public String changeQuestionAfter(Model model, @ModelAttribute("question") Question question, @ModelAttribute("forUser") String forUser,
                                        @ModelAttribute("type") String type, @ModelAttribute("id") Long id){
        User responsible = userService.findUserById(forUser);
        if (responsible == null){
            model.addAttribute("question", question);
            model.addAttribute("errorMessage", "User doesn't exist");
            return "add_question";
        }
        question.setResponsible(responsible);
        User sender = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        question.setSender(sender);
        question.setId(id);
        System.out.println("---------------------");
        System.out.println(question.getViewType());
        questionService.editQuestion(question, type);
        return "redirect:/questions";
    }

    @PostMapping("/changeQuestion")
    public String changeQuestion(@ModelAttribute("id") Long id, Model model){
        System.out.println("----------------------------------------");
        System.out.println(id);
        Question question = questionService.findById(id);
        model.addAttribute("question",question);
        return "/change_question";
    }
}
