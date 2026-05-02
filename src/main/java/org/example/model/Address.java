package org.example.model;

public class Address {
    String city;
    String streetAndHome;

    public Address(String city, String address){
        this.city = city;
        streetAndHome = address;
    }

    public String getStreetAndHome() {
        return streetAndHome;
    }

    public void setStreetAndHome(String streetAndHome) {
        this.streetAndHome = streetAndHome;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
