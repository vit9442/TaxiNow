package org.top.taxinow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.top.taxinow.entity.Car;

@Controller
@RequestMapping("dispatcher")
public class DispatcherController {

    @GetMapping("")
    public String findAll(Model model){

        return "dispatcher/dispatcher-list";
    }
}
