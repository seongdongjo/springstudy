package com.company;

import com.company.adapter.*;
import com.company.aop.AopBrowser;
import com.company.decorator.*;
import com.company.facade.Ftp;
import com.company.facade.Reader;
import com.company.facade.SftpClient;
import com.company.facade.Writer;
import com.company.observer.Button;
import com.company.observer.IButtonListener;
import com.company.proxy.Browser;
import com.company.proxy.BrowserProxy;
import com.company.proxy.IBrowser;
import com.company.singleton.AClazz;
import com.company.singleton.BClazz;
import com.company.singleton.SocketClient;
import com.company.strategy.*;

import java.util.concurrent.atomic.AtomicLong;

public class Main {

    public static void main(String[] args) {
//        AClazz aClazz = new AClazz();
//        BClazz bClazz = new BClazz();
//
//        SocketClient aClient = aClazz.getSocketClient();
//        SocketClient bClient = bClazz.getSocketClient();
//
//        System.out.println("두개의 객체가 동일한가");
//        System.out.println(aClient.equals(bClient));
//
        HairDryer hairDryer = new HairDryer();
        connect(hairDryer); //출력: 헤어드라이어 110V

        Cleaner cleaner = new Cleaner();
        //connect(cleaner); 이렇게하면에러가난다. 현재 connect메서드는 110V만 연결가능하다.
        //그래서 어댑터가 필요.(220 -> 110v로 바꿔주는)

        Electronic110V adapter = new SocketAdapter(cleaner); //220v을 어댑터를 통해 연결
        connect(adapter);
        //인터페이스가 서로달라서 맞추지못할때(110,220v) 중간에서 어댑터클래스를 통해 연결 -> 어댑터 패턴

        AirConditioner airConditioner = new AirConditioner();
        Electronic110V airAdapter = new SocketAdapter(airConditioner);
        connect(airAdapter);

        //콘센트
        //main함수가 static이니까 여기도 static
    }
        public static void connect (Electronic110V electronic110V){
            electronic110V.powerOn();
        }
//        Browser browser = new Browser("www.naver.com");
//        browser.show(); //html파일하나 받는다. 출력: browser loading html from : www.naver.com
//        browser.show();
//        browser.show();
//        browser.show(); //캐시를 사용안하면 새로 계속 html을 생성한다.
//
//        //프록시를 통해호출
//        IBrowser browser1 = new BrowserProxy("www.naver.com"); //처음은 html을 생성
//        browser.show(); //여기서부터는 cache를 이용함
//        browser.show();
//        browser.show();
//        browser.show();
//        browser.show();
//
//        AtomicLong start = new AtomicLong();
//        AtomicLong end = new AtomicLong();
//        IBrowser aopBrowser = new AopBrowser("www.naver.com",
//                () -> {
//                    System.out.println("before");
//                    start.set(System.currentTimeMillis());
//                },
//                () -> {
//                    long now = System.currentTimeMillis();
//                    end.set(now - start.get()); //몇초걸렸는지 end에 들어간다.
//                }
//
//        );
//        aopBrowser.show(); //처음에는 생성쪽에 1.5초 delay줬으니 1.5초걸리고
//        System.out.println("loading time: " + end.get());
//
//        aopBrowser.show(); //두번쨰 호출부터는 loadingtime이 0이다.
//        System.out.println("loading time: " + end.get());
//        ICar audi = new Audi(1000);
//        audi.showPrice();
//
//        //등급에따라 가격이 증가
//        //a3
//        ICar audi3 = new A3(audi, "A3");
//        audi3.showPrice();
//        //a4
//        ICar audi4 = new A4(audi, "A4");
//        audi4.showPrice();
//        //a5
//        ICar audi5 = new A5(audi, "A5");
//        audi5.showPrice();
//
//        Button button = new Button("버튼");
//
//        button.addListener(new IButtonListener() {
//            @Override
//            public void clickEvent(String event) {
//                System.out.println(event);
//            }
//        });
//        System.out.println("11");
//        button.click("메시지 전달: click1"); //이벤트를 위에 전달해주는
//        button.click("메시지 전달: click2");
//        button.click("메시지 전달: click3");
//        button.click("메시지 전달: click4");
//       SftpClient sftpClient = new SftpClient("www.foo.co.kr", 22, "/home/etc", "text.tmp");
//       sftpClient.connect();
//       sftpClient.write();
//       sftpClient.read();
//       sftpClient.disConnect();
//       //객체들의 의존을 숨기는게 퍼싸드패턴이다.

        //전략 메서드를 가진 전략객체(NormalStrategy, Base64Strategy)
        //전략 객체를 사용하는 컨텍스트(Encoder)
        //전략 객체를 생성해 컨텍스트에 주입하는 클라이언트(main)

//        Encoder encoder = new Encoder();
//
//        //base64전략 생성
//        EncodingStrategy base64 = new Base64Strategy();
//
//        //normal전략생성
//        EncodingStrategy normal = new NormalStrategy();
//
//        String message = "hello java";
//        //전략수정(원본객체는 그대로 두고)
//        encoder.setEncodingStrategy(base64);
//        String base64Result = encoder.getMessage(message);
//        System.out.println(base64Result); //출력: aGVsbG8gamF2YQ==
//
//        encoder.setEncodingStrategy(normal);
//        String normalResult = encoder.getMessage(message);
//        System.out.println(normalResult); //출력: hello java
//
//        encoder.setEncodingStrategy(new AppendStrategy());
//        String appendResult = encoder.getMessage(message);
//        System.out.println(appendResult); //출력: ABCDhello java
    }
