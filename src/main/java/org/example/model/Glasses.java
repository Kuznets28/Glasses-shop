package org.example.model;

public class Glasses {
    private long id;
    private String sku;
    private String name_model;
    private int price;
    private String patToPhoto;

    public Glasses(long id, String sku, String name_model, int price, String patToPhoto) {
        this.id = id;
        this.sku = sku;
        this.name_model = name_model;
        this.price = price;
        this.patToPhoto = patToPhoto;
    }

    public Glasses (String sku, String model, int price, String patToPhoto){
        this.sku= sku;
        name_model = model;
        this.price = price;
        this.patToPhoto = patToPhoto;

    }

    public String getPatToPhoto() {
        return patToPhoto;
    }

    public void setPatToPhoto(String patToPhoto) {
        this.patToPhoto = patToPhoto;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName_model() {
        return name_model;
    }

    public void setName_model(String name_model) {
        this.name_model = name_model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
