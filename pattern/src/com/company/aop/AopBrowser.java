package com.company.aop;

import com.company.proxy.Html;
import com.company.proxy.IBrowser;

public class AopBrowser implements IBrowser {
    private String url;
    private Html html;
    private Runnable before;
    private Runnable after;

    public AopBrowser(String url, Runnable before, Runnable after) {
        this.url = url;
        this.before = before;
        this.after = after;
    }

    //Aop는 관점기준이라 before,after로 비슷하게 해봄
   @Override
    public Html show() {
       before.run(); //전후로 내가 원하는 기능넣을것이다.

       if(html == null) {
           this.html = new Html(url);
           System.out.println("AopBrowser html loading from: " + url);
           try {
               Thread.sleep(1500);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
       after.run();
       System.out.println("AopBrowser html cache: " +url);
       return html;
    }
}
