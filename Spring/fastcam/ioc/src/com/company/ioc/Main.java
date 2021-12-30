package com.company.ioc;

public class Main {

    public static void main(String[] args) {
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-book";

        // Base 64 encoding
        /*
        Base64Encoder encoder = new Base64Encoder();
        String result = encoder.encode(url);

        System.out.println("result = " + result);

        // url encoding
        UrlEncoder urlEncoder = new UrlEncoder();
        String urlResult = urlEncoder.encode(url);

        System.out.println("urlResult = " + urlResult);*/

        /**
         * IEncoder Interface 추가 후
         */
        /*IEncoder encoder = new Base64Encoder();
        String result = encoder.encode(url);

        System.out.println("result = " + result);

        IEncoder urlEncoder = new UrlEncoder();
        String urlResult = urlEncoder.encode(url);

        System.out.println("urlResult = " + urlResult);*/

        /**
         * DI
         */
        Encoder encoder = new Encoder(new UrlEncoder());
        String result = encoder.encode(url);

        System.out.println("result = " + result);

    }
}
