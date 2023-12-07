package kq.practice.assessmentpractice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kq.practice.assessmentpractice.service.OrderService;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    OrderService svc;

    @GetMapping(path = "/{id}")
    public ResponseEntity<String> getOrder(@PathVariable("id") String orderID) {

        List<Object> list = svc.findOrder(orderID);

        return ResponseEntity.status((Integer) list.get(0))
                .body((String)list.get(1));
    }
}
