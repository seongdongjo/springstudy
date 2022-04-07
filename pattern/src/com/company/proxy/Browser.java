package com.company.proxy;

public class Browser implements IBrowser{
    private String url;
    
    public Browser(String url){
        this.url = url;
    }

    @Override
    public Html show() {
        System.out.println("browser loading html from: " + url);
        return new Html(url); //url접근시 html파일만들어서 반환
    }
}
