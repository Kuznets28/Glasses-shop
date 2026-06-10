package org.example.model;

import java.util.Objects;

public class Glasses {
    private int id;
    private String article;
    private String nameModel;
    private int price;
    private String description;
    private int countGlasses;
    private String pathToPhoto;

    public Glasses(int id, String article, String name_model, int price, String description, int count_glasses, String pathToPhoto) {
        this.id = id;
        this.article = article;
        this.nameModel = name_model;
        this.price = price;
        this.description = description;
        this.countGlasses = count_glasses;
        this.pathToPhoto = pathToPhoto;
    }

    public Glasses(int id, String article, String name_model, int price, String description, String pathToPhoto) {
        this.id = id;
        this.article = article;
        this.nameModel = name_model;
        this.price = price;
        this.description = description;
        this.pathToPhoto = pathToPhoto;
    }

    public Glasses (String article, String name_model, int price, String pathToPhoto){
        this.article = article;
        this.nameModel = name_model;
        this.price = price;
        this.pathToPhoto = pathToPhoto;

    }

    public String getPathToPhoto() {
        return pathToPhoto;
    }

    public void setPatToPhoto(String patToPhoto) {
        this.pathToPhoto = patToPhoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public Glasses setArticle(String article) {
        this.article = article;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public Glasses setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getCountGlasses() {
        return countGlasses;
    }

    public Glasses setCountGlasses(int countGlasses) {
        this.countGlasses = countGlasses;
        return this;
    }

    public Glasses setPathToPhoto(String pathToPhoto) {
        this.pathToPhoto = pathToPhoto;
        return this;
    }

    public String getNameModel() {
        return nameModel;
    }

    public void setNameModel(String name_model) {
        this.nameModel = name_model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Glasses glasses = (Glasses) o;
        return Objects.equals(id, glasses.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
