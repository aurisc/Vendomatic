package com.techelevator.view;

public class Products {
    String slot;
    String name;
    double price;
    String type;
    String quantity;
    Products(String slot, String name, double price, String type, String quantity){
        this.slot= slot;
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;

    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
