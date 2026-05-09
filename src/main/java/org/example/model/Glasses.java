package org.example.model;

public class Glasses {
    private long id;
    private String sku;
    private String name_model;
    private int price;
    private String pathToPhoto;

    public Glasses(long id, String sku, String name_model, int price, String pathToPhoto) {
        this.id = id;
        this.sku = sku;
        this.name_model = name_model;
        this.price = price;
        this.pathToPhoto = pathToPhoto;
    }

    public Glasses (String sku, String model, int price, String pathToPhoto){
        this.sku= sku;
        name_model = model;
        this.price = price;
        this.pathToPhoto = pathToPhoto;

    }

    public String getPathToPhoto() {
        return pathToPhoto;
    }

    public void setPatToPhoto(String patToPhoto) {
        this.pathToPhoto = patToPhoto;
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

    public String getNameModel() {
        return name_model;
    }

    public void setNameModel(String name_model) {
        this.name_model = name_model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
