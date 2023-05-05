package Belarus.Softarex.TechnicalProj.controllers;

import Belarus.Softarex.TechnicalProj.entities.User;
import Belarus.Softarex.TechnicalProj.exceptions.ServiceException;
import Belarus.Softarex.TechnicalProj.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AccessController {

    @Autowired
    private UserService userService;

    @GetMapping("/login-error")
    public String loginError(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null){
            AuthenticationException ex = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null){
                errorMessage = ex.getMessage();
            }
        }
        System.out.println("--------" + errorMessage);
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @GetMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute("userBox", new User());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String tryAddUser(@ModelAttribute("userBox") User user, BindingResult bindingResult, Model model){
        try {
            System.out.println(user.toString());
            userService.saveUser(user);
            return "login";
        }catch (ServiceException ex){
            model.addAttribute("errorMessage", ex.getMessage());
            return "signUp";
        }
    }
}
