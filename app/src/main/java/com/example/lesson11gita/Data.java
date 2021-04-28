package com.example.lesson11gita;

public class Data {
    private int id;
    private String name;
    private String phone;

    public Data(int id, String name, String phone) {
        this.name = name;
        this.id = id;
        this.phone = phone;
    }

    public Data() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }
}
