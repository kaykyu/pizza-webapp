package kq.practice.assessmentpractice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kq.practice.assessmentpractice.model.Customer;
import kq.practice.assessmentpractice.model.Pizza;
import kq.practice.assessmentpractice.service.OrderService;

@Controller
@RequestMapping(path = "/pizza")
public class PizzaController {

    @Autowired
    OrderService svc;

    @PostMapping
    public String postOrder(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult binding, Model model, HttpSession session) {

        if (binding.hasErrors()) {
            return "index";
        }
        session.setAttribute("pizza", pizza);
        model.addAttribute("customer", new Customer());
        return "orderdetails";
    }

    @PostMapping(path = "/order")
    public String postOrder(@Valid @ModelAttribute("customer") Customer customer, BindingResult binding, Model model,
            HttpSession session) {

        if (binding.hasErrors()) {
            return "orderdetails";
        }

        Map<String, String> map = svc.newOrder((Pizza) session.getAttribute("pizza"), customer);

        model.addAttribute("customer", customer);
        model.addAttribute("map", map);
        return "orderconfirmation";
    }
    
    @PostMapping(path = "/cancel")
    public String cancelOrder(HttpSession session, Model model) {

        session.invalidate();
        model.addAttribute("pizza", new Pizza());
        return "redirect:/";
    }
}
