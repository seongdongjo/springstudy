package com.example.objectmapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String name;
    private int age;
    @JsonProperty("phone_number")
    private String phoneNumber;

    public User() {}

    public User(String name, int age, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    //만약에 내가만든 클래스가 objectmapper가 활용하는 클래스이면 get이라는 이름을 붙이는 것은 좋지않다.
//    public User getDefaultUser() {
//        return new User("default",0);
//    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
