package com.company.singleton;

public class AClazz {
    private SocketClient socketClient;

    public AClazz() {
        this.socketClient = SocketClient.getInstance();
        //this.socketClient = new SocketClient(); 이렇게하면 새로운객체만듬
    }
    public SocketClient getSocketClient() {
        return this.socketClient;
    }
}