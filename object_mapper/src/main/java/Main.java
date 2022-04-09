import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.Car;
import dto.User;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("main");
    
        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        user.setName("홍길동");
        user.setAge(10);

        Car car1 = new Car();
        car1.setName("k5");
        car1.setCarNumber("11가 1111");
        car1.setType("sedan");

        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCarNumber("12가 1111");
        car2.setType("SUV");

        List<Car> carList = Arrays.asList(car1,car2);
        user.setCars(carList);

        //인코딩설정 UTF-8 해야된다. settings, help에서 (사진),안하면 한글깨진다. json의 기본 인코딩이 UTF-8이기 때문에(윈도우에서는 설정해야한다)
        System.out.println(user); //User{name='홍길동', age=10, cars=[Car{name='k5', carNumber='11가 1111', type='sedan'}, Car{name='Q5', carNumber='12가 1111', type='SUV'}]}

        //json으로 바꿔보자
        String json = objectMapper.writeValueAsString(user);
        System.out.println(json); //{"name":"홍길동","age":10,"cars":[{"name":"k5","carNumber":"11가 1111","type":"sedan"},{"name":"Q5","carNumber":"12가 1111","type":"SUV"}]}
        //이렇게 보면 헷갈릴수있으니 크롬에서 json validator가서 붙여넣으면 보기쉽게 나온다.
        //JsonProperty붙인후(Car)
        //{"name":"홍길동","age":10,"cars":[{"name":"k5","car_number":"11가 1111","TYPE":"sedan"},{"name":"Q5","car_number":"12가 1111","TYPE":"SUV"}]}

        //이제 json노드 접근
        JsonNode jsonNode = objectMapper.readTree(json); //여기서jsonNode는 sample.json에 정의한 전체 json을 의미
        String _name = jsonNode.get("name").asText(); //jsonNode.get하면 object가 나오고 거기에 형변환으로 asText
        int _age = jsonNode.get("age").asInt();
        System.out.println("name : " +_name);
        System.out.println("age : " +_age);

        //Cars는 리스트 안에 또다른 jsonnode가 들어있는거나 마찬가지이다. 그래서 cars안에 있는 노드를 가져와야한다.
        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode)cars; //(ArrayNode)는 asText와같이 형변환을 위해서

        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {}); //convertValue : object 즉, json을 우리가 원하는 클래스로 변환가능
        //이렇게 하면 arrayNode(object)를 받아서 우리가 원하는 List<Car>라는 타입으로 변환
        System.out.println(_cars); //[Car{name='k5', carNumber='11가 1111', type='sedan'}, Car{name='Q5', carNumber='12가 1111', type='SUV'}]

        //위에 처럼 json을 가져온 후 값이 바꿔보자
        ObjectNode objectNode = (ObjectNode)jsonNode;
        objectNode.put("name","steve");
        objectNode.put("age", 20);

        System.out.println(objectNode.toPrettyString());
//        {
//            "name" : "steve",  //이렇게 바뀐걸볼수있다. 이렇게 objectmapper를 통해서 각각의 jsonnode에 접근가능하다.
//                "age" : 20,
//                "cars" : [ {
//            "name" : "k5",
//                    "car_number" : "11가 1111",
//                    "TYPE" : "sedan"
//        }, {
//            "name" : "Q5",
//                    "car_number" : "12가 1111",
//                    "TYPE" : "SUV"
//        } ]
//        }

    }
}
