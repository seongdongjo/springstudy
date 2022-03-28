package com.company.singleton;

public class SocketClient {
    private static SocketClient socketClient = null;

    //public socketClient() { 이렇게 새로 객체를 만들면 서로 객체가다르다 AClazz참조
    //}
    private SocketClient() { //기본생성자 막고

    }

    public static SocketClient getInstance() { //최초에 한번만 생성
        if(socketClient == null) {
            socketClient = new SocketClient();
        }
        return socketClient;
    }

    public void connect() {
        System.out.println("connect");
    }
}
