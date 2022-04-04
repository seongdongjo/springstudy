package com.example.post.dto;

public class PostRequestDto {
    private String account;
    private String email;
    private String address;
    private String password;
    //기본은 카멜케이스이다. 
    private String phoneNumber; //phone_number
    //크롬에서 Talend로 테스트할 때 body에 "phone_number" : "010-1111-1111" 이렇게 해서 보내면 폰넘버에서 null이 뜬다.
    //그러면 매핑할려면?? 일일이 변수에다가 해야하는 귀찮음이 있긴하지만
    //실제로 들어오는 이름값을 매핑시켜준다. "phone_number" : "010-1111-1111" -> phoneNumber로 매핑시켜줌
    //@JsonProperty("phone_number") phone_number로 오면 phoneNumber로 매핑해줘라
    //private String phoneNumber;

    //@JsonProperty("OTP") 스네이크케이스, 카멜케이스도 아닌경우에는 이렇게
    //private String OTP;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "PostRequestDto{" +
                "account='" + account + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
