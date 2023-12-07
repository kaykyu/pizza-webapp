package kq.practice.assessmentpractice.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Pizza {

    @NotNull(message = "Pizza selection is required")
    private String type;

    @NotEmpty(message = "Pizza size is required")
    private String size;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be more than 1")
    @Max(value = 10, message = "Quantity must be less than 10")
    private Integer quantity;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Pizza() {
    }

    public Pizza(String type, String size, Integer quantity) {
        this.type = type;
        this.size = size;
        this.quantity = quantity;
    }
    
    
}
