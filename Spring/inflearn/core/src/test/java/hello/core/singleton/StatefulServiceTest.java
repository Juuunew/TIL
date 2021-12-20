package hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


/**
 * 싱글톤 방식의 주의할 점
 *
 * 객체 인스턴스를 하나만 생성해서 공유하는 싱글톤 방식은
 * 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에
 * 싱글톤 객체는 상태를 유지(stateful)하게 설계하면 안된다. -> 무상태(stateless)로 설계할 것
 *
 * 무상태(stateless)
 * -> 특정 클라이언트에 의존적인 필드가 있으면 안됨.
 * -> 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안됨.
 * -> 가급적 읽기만 가능해야 함.
 * -> 필드 대신에 자바에서 공유되지 않는 지역변수, 파라미터, ThreadLocal 등을 사용용
 */
class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

/*        // ThreadA : A 사용자가 10000원 주문
        statefulService1.order("userA", 10000);

        // ThreadB : B 사용자가 20000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA : A의 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);
        // return : 20000
        // 같은 instance를 사용하기 때문에 중간에 20000으로 변경되어짐

        assertThat(statefulService1.getPrice()).isEqualTo(20000);*/

        // 무상태로 설계
        int userAPrice = statefulService1.order("userA", 10000);
        int userBPrice = statefulService2.order("userB", 20000);

        System.out.println("price = " + userAPrice);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}