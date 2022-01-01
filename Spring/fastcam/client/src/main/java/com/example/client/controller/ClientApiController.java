package com.example.client.controller;

import com.example.client.dto.Req;
import com.example.client.dto.UserResponse;
import com.example.client.service.RestTemplateService;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * WebClient 는 R
 */

@RestController
@RequestMapping("/api/client")
public class ClientApiController {

    private final RestTemplateService restTemplateService;

    public ClientApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    /*@GetMapping("/hello")
    public UserResponse getHello() {
        return restTemplateService.hello();
    }*/

    /**
     * server post 조회
     */
/*    @GetMapping("/hello")
    public UserResponse getHello() {
        return restTemplateService.post();
    }*/

/*    @GetMapping("hello")
    public UserResponse getHello() {
        restTemplateService.exchange();
        return new UserResponse();
    }*/

    @GetMapping("hello")
    public Req<UserResponse> getHello() {
        return restTemplateService.genericExchange();
    }
}
