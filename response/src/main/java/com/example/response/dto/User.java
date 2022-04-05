package com.example.response.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

//일괄적으로 스네이크케이스 적용 시 @JsonNaming(value=PropertyNamingStrategy.SnakeCaseStrategy.class)
//response로 응답을 줄 때 phone_number로 주게된다.
//단, 보낼 때도 json body에 phone_number로 보내줘야한다.
// { phone_number : "010-1111-1111" } 이렇게
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private String name;
    private int age; //이렇게 primitive타입의 int는 값이 없으면 0 , wrapper클래스인 Integer로 하면 null이 된다. -> null값을 JSON으로 넘기고 싶지않을 때
    //@JsonInclude를 쓴다. user.setName("steve");
    //        user.setAddress("패스트 캠퍼스"); 이렇게 age에 값이 없으면 null이지만 response할 때 null값은 제외시키는게 @JsonInclude이다.
//    {
//        "name" : "steve",
//        "address": "패스트 캠퍼스"
//    } //이렇게 null값은 제외시키고 있는값만 json으로 response한다
    private String phoneNumber;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
