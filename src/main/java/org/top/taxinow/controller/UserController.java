package org.top.taxinow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.top.taxinow.entity.User;
import org.top.taxinow.service.UserService;

import java.util.Optional;

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



    @GetMapping("user")
    public String findAll(Model model){

        Iterable<User> users = userService.findAll();
                if (users.iterator().hasNext()) {
            model.addAttribute("users", users);
        } else {
            model.addAttribute("users", null);
        }

        return "user/user-list";
    }

    @GetMapping("user/{id}")
    public String details(@PathVariable Integer id, Model model){
        Optional<User> user = userService.findById(id);

        if(user.isPresent()) {
            model.addAttribute("user", user.get());
        } else {
            model.addAttribute("user", null);
        }
        return "user/user-details";
    }

    @GetMapping("user/update/{id}")
    public String updateCar(@PathVariable Integer id, Model model){
        Optional<User> user = userService.findById(id);
        if(user.isPresent()) {
            model.addAttribute("user", user.get());
        } else {
            model.addAttribute("user", null);
        }
        return "user/user-edit";
    }

    @PostMapping("user/update/{id}")
    public String updateCar(@PathVariable Integer id, User user, RedirectAttributes redirectAttributes){
       user.setRole("USER");
        Optional<User> userUpdated = userService.updateById(id, user);
        if(userUpdated.isPresent()){
            redirectAttributes.addFlashAttribute("successMessage", "Информация о " + user.getName() + " успешно обновлён");
        } else
            redirectAttributes.addFlashAttribute("errMessage", "Информация о" + user.getName() + " не был обновлёна");
        return "redirect:/user";
    }
    @GetMapping("user/delete/{id}")
    public String deleteByID(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        Optional<User> removableUser = userService.deleteById(id);
        if(removableUser.isPresent()){
            redirectAttributes.addFlashAttribute("successMessage", "Диспетчер " + removableUser.get().getName() + "был успешно удалён");
            return "redirect:/user";
        }
        else {
            redirectAttributes.addFlashAttribute("errMessage", "Диспетчер не был удалён");
        }
        return "redirect:/user";
    }

}
