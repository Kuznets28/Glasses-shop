package org.example.model;

import java.util.ArrayList;

public class Order {
    private int id;
    private ArrayList<Glasses> glasses = new ArrayList<>();
    private User user;
    private Address address;
    private StatusOrder statusOrder;

    public Order(){

    }

    public ArrayList<Glasses> getGlasses() {
        return glasses;
    }

    public void addGlasses(Glasses glasses){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
