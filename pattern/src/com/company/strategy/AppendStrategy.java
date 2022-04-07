package com.company.strategy;

public class AppendStrategy implements EncodingStrategy{

    @Override
    public String encode(String text) {
        return "ABCD" + text; //abcd를 추가시키는
    }
}
