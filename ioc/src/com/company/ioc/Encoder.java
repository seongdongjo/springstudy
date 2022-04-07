package com.company.ioc;

import java.util.Base64;

public class Encoder {
    private IEncoder iEncoder;
    
    //외부에서 iEncoder를 주입받음
    public Encoder(IEncoder iEncoder) { //new Base64Encoder()
//        this.iEncoder = new Base64Encoder();
//        this.iEncoder = new UrlEncoder();
        this.iEncoder = iEncoder;
    }

    public String encode(String message)
    {
        return iEncoder.encode(message);
    }
}
