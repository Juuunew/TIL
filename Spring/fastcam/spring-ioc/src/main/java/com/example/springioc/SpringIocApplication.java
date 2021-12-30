package com.example.springioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 스프링 빈이 하나만 있으면 자동으로 매칭이 된다.
 * 하지만 여러 개가 있을 경우엔 스프링에서 어느 것을 매칭해야할 지 모르기 때문에
 * @Qualifier 를 이용해서 매칭할 빈을 지정해줄 수 있다.
 */

@SpringBootApplication
public class SpringIocApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIocApplication.class, args);

        ApplicationContext context = ApplicationContextProvider.getContext();

        /*Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
        UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);

        Encoder encoder = new Encoder(base64Encoder);

        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

        String result = encoder.encode(url);
        System.out.println("result = " + result);

        encoder.setIEncoder(urlEncoder);
        result = encoder.encode(url);
        System.out.println("result = " + result);*/

        Encoder encoder = context.getBean("urlEncode", Encoder.class);

        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";
        String result = encoder.encode(url);
        System.out.println("result = " + result);
    }

    @Configuration  // 한 개의 class 에서 여러 개의 Bean 등록
    class AppConfig {

        @Bean("base64Encode")
        public Encoder encoder(Base64Encoder base64Encoder) {
            return new Encoder(base64Encoder);
        }

        @Bean("urlEncode")
        public Encoder encoder(UrlEncoder urlEncoder) {
            return new Encoder(urlEncoder);
        }
    }
}
