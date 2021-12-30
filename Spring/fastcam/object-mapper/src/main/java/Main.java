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

        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();

        user.setName("홍길동");
        user.setAge(10);

        Car car1 = new Car();

        car1.setName("K5");
        car1.setCarNumber("11가 1111");
        car1.setType("sedan");

        Car car2 = new Car();

        car2.setName("Q5");
        car2.setCarNumber("22가 2222");
        car2.setType("SUV");

        List<Car> carList = Arrays.asList(car1, car2);
        user.setCars(carList);

        System.out.println("user = " + user);   // user = User{name='홍길동', age=10, cars=[Car{name='K5', carNumber='11가 1111', type='sedan'}, Car{name='Q5', carNumber='22가 2222', type='SUV'}]}

        String json = objectMapper.writeValueAsString(user);
        System.out.println("json = " + json);   // json = {"name":"홍길동","age":10,"cars":[{"name":"K5","car_number":"11가 1111","TYPE":"sedan"},{"name":"Q5","car_number":"22가 2222","TYPE":"SUV"}]}


        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();

        System.out.println("_name = " + _name); // _name = 홍길동
        System.out.println("_age = " + _age);   // _age = 10


        /**
         * jsonNode.get()
         *      -> object 가져오기
         *      -> 원하는 형으로 형변환
         *          -> 파싱을 원하면 convertValue ( object 와 원하는 type 을 넣고 매핑 )
         */
        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode) cars;

        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});
        System.out.println("_cars = " + _cars); // _cars = [Car{name='K5', carNumber='11가 1111', type='sedan'}, Car{name='Q5', carNumber='22가 2222', type='SUV'}]

        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "steve");
        objectNode.put("age", "20");

        System.out.println("objectNode.toPrettyString() = " + objectNode.toPrettyString());
        /*
        {
          "name" : "steve",
          "age" : "20",
          "cars" : [ {
            "name" : "K5",
            "car_number" : "11가 1111",
            "TYPE" : "sedan"
          }, {
            "name" : "Q5",
            "car_number" : "22가 2222",
            "TYPE" : "SUV"
          } ]
        }
         */
    }
}
