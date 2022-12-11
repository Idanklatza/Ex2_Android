package com.example.assignmenttwostudentapp.model;

public class Student {
    public String name;
    public String id;
    public String phone;
    public String address;
    public String avatarUrl;
    public Boolean cb;

    public Student(String name, String id, String phone, String address, String avatarUrl, Boolean cb) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.avatarUrl = avatarUrl;
        this.cb = cb;
    }
}
