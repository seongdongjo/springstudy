package com.company.strategy;

public class Encoder { //그떄그때마다 전략을 받아야한다. 어떤 인코드를 쓸지
    private EncodingStrategy encodingStrategy;

    public String getMessage(String message) {
        return this.encodingStrategy.encode(message);
    }

    public void setEncodingStrategy(EncodingStrategy encodingStrategy) {
        this.encodingStrategy = encodingStrategy;
    }
}
