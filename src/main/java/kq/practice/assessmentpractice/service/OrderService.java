package kq.practice.assessmentpractice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kq.practice.assessmentpractice.model.Customer;
import kq.practice.assessmentpractice.model.Pizza;
import kq.practice.assessmentpractice.repository.OrderRepo;

@Service
public class OrderService {

    @Autowired
    OrderRepo repo;
    
    public Map<String, String> newOrder(Pizza pizza, Customer customer) {

        Map<String, String> map = new HashMap<>();
        String orderID = UUID.randomUUID().toString().substring(0, 8);
        Double cost = 0.0;

        switch (pizza.getType()) {
            case "bella":
            case "marinara":
            case "spianatacalabrese":
                cost += 30;
                break;
            case "trioformaggio":
                cost += 25;
                break;
            case "margherita":
                cost += 22;
                break;
        }

        switch (pizza.getSize()) {
            case "sm":
                cost *= 1;
                break;
            case "md":
                cost *= 1.2;
                break;
            case "lg":
                cost *= 1.5;
                break;
        }

        cost *= pizza.getQuantity();
        Double totalCost = cost;

        if (customer.getRush() == true) {
            totalCost += 2;
        }

        map.put("orderID", orderID);
        map.put("cost", "%.2f".formatted(cost));
        map.put("totalCost", "%.2f".formatted(totalCost));

        repo.save(pizza, customer, map);

        return map;
    }
    
    public List<Object> findOrder(String orderID) {

        List<Object> list = new ArrayList<>();

        if (repo.exist(orderID)) {
            list.add(200);
            list.add(repo.find(orderID));
        } else {
            list.add(404);
            list.add(repo.find(orderID));
        }

        return list;
    }
}
