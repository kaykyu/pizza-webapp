package kq.practice.assessmentpractice.repository;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import kq.practice.assessmentpractice.model.Customer;
import kq.practice.assessmentpractice.model.Pizza;

@Repository
public class OrderRepo {

    @Autowired
    @Qualifier("redis")
    RedisTemplate<String, String> template;

    public void save(Pizza pizza, Customer customer, Map<String, String> map) {

        ValueOperations<String, String> vOps = template.opsForValue();

        JsonObject order = Json.createObjectBuilder()
                .add("orderID", map.get("orderID"))
                .add("name", customer.getName())
                .add("address", customer.getAddress())
                .add("phone", customer.getPhone())
                .add("rush", customer.getRush().toString())
                .add("comments", customer.getComments())
                .add("pizza", pizza.getType())
                .add("size", pizza.getSize())
                .add("quantity", "%d".formatted(pizza.getQuantity()))
                .add("total", map.get("totalCost"))
                .build();

        vOps.set(map.get("orderID"), order.toString());
    }

    public Boolean exist(String orderID) {

        ValueOperations<String, String> vOps = template.opsForValue();
        if (vOps.get(orderID) == null) {
            return false;
        }

        return true;
    }

    public String find(String orderID) {

        ValueOperations<String, String> vOps = template.opsForValue();
        if (exist(orderID)) {
            return vOps.get(orderID);
        } else {
            JsonObject jObject = Json.createObjectBuilder()
                    .add("message", "Order %s not found".formatted(orderID))
                    .build();
            return jObject.toString();
        }
    }
}
