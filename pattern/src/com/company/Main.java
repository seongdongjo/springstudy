package com.company;

import com.company.adapter.*;
import com.company.singleton.AClazz;
import com.company.singleton.BClazz;
import com.company.singleton.SocketClient;

public class Main {

    public static void main(String[] args) {
        AClazz aClazz = new AClazz();
        BClazz bClazz = new BClazz();

        SocketClient aClient = aClazz.getSocketClient();
        SocketClient bClient = bClazz.getSocketClient();

        System.out.println("두개의 객체가 동일한가");
        System.out.println(aClient.equals(bClient));

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
        
    }
    //콘센트
    //main함수가 static이니까 여기도 static
    public static void connect(Electronic110V electronic110V) {
        electronic110V.powerOn();
    }

}
