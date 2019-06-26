package model;

import javax.servlet.http.Part;

public class Product {
    private int id;
    private String name;
    private String descreption;
    private float price;
    private boolean status;
    private String avatar;

    public Product() {
    }

    public Product(int id, String name, String descreption, float price, boolean status, String avatar) {
        this.id = id;
        this.name = name;
        this.descreption = descreption;
        this.price = price;
        this.status = status;
        this.avatar = avatar;

    }

    public String getAvatar() {
        return avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setAvatar (String avatar) {
        this.avatar = avatar;
    }
}
