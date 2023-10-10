package org.top.taxinow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.taxinow.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
   @PostMapping("register")
    public String register(
            @RequestParam String name,
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String passwordConfirmation,
            RedirectAttributes model){

       if(userService.register(name, login, password, passwordConfirmation)){
            model.addFlashAttribute("successMessage", "Успешно");

       }else {

       model.addFlashAttribute("errMessage", "Ошибка");}
    return "redirect:/login";
   }
}
