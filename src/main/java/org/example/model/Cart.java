package org.example.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Glasses, Integer> cart = new HashMap<>();
    private int total_price;
    public Map<Glasses, Integer> getCart() {
        return cart;
    }

    public Cart setCart(Map<Glasses, Integer> cart) {
        this.cart = cart;
        return this;
    }

    public void addCartItem(Glasses glasses, int count){
        cart.put(glasses, count);
    }

    public int getTotal_price() {
        return total_price;
    }

    public Cart setTotal_price(int total_price) {
        this.total_price = total_price;
        return this;
    }
}
