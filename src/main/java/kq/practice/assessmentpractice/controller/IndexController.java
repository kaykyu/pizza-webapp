package kq.practice.assessmentpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kq.practice.assessmentpractice.model.Pizza;

@Controller
@RequestMapping(path = { "/", "/index" })
public class IndexController {
    
    @GetMapping
    public String getHome(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "index";
    }
}
