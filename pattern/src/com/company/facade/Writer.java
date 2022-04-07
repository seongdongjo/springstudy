package com.company.facade;

public class Writer { //파일쓰기
    private String fileName;
    
    public Writer(String fileName) {
        this.fileName = fileName;
    }
    
    public void fileConnect() { //파일 생성또는 이어쓰기
        String msg = String.format("Writer %s로 연결합니다.",fileName);
        System.out.println(msg);
    }
    
    public void fileDisconnect() {
        String msg = String.format("Writer %s로 연결 종료합니다.",fileName);
        System.out.println(msg);
    }
    
    public void write() {
        String msg = String.format("Writer %s로 파일쓰기를 합니다.",fileName);
        System.out.println(msg);
    }
}
