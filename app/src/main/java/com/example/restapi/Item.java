package com.example.restapi;

public class Item {

    private String name;
    private String imageLink;
    private String phone;
    private String email;
    private String address;
    private String birthday;
    private String gender;

    public Item() {

    }

    public Item(String name, String imageLink, String phone, String email, String address, String birthday, String gender) {
        this.name = name;
        this.imageLink = imageLink;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }
}
