package com.company.facade;

public class Reader { //파일에서 읽어오기때문에 파일이름을 알아야한다.
    private String fileName;

    public Reader(String fileName) {
        this.fileName = fileName;
    }

    public void fileConnect() {
        String msg = String.format("Reader %s로 연결합니다.",fileName);
        System.out.println(msg);
    }

    public void fileRead() {
        String msg = String.format("Reader %s의 내용을 읽어옵니다.",fileName);
        System.out.println(msg);
    }

    public void fileDisconnect() {
        String msg = String.format("Reader %s로 연결 종료합니다.",fileName);
        System.out.println(msg);
    }
}
