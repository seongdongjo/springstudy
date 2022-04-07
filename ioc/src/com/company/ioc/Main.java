package com.company.ioc;

public class Main {

    public static void main(String[] args) {
	    String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot"; //인코딩할 url

        //주입객체만 바꾼다.
        //Encoder를 건드릴 필요는 없다.
        Encoder encoder = new Encoder(new Base64Encoder());
        //Encoder encoder = new Encoder(new UrlEncoder());
        String result = encoder.encode(url);
        System.out.println(result);

    }
}
