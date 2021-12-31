package com.example.server.controller;

import com.example.server.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    /**
     *
     * @param name
     * @param age
     * @return String
     */
    /*@GetMapping("/hello")
    public String hello() {
        return "hello server";
    }*/

    /**
     * http://localhost:9090/api/server/hello
     * @param name
     * @param age
     * @return Json / ResponseEntity
     */
    /*@GetMapping("/hello")
    public User hello() {
        User user = new User();

        user.setName("steve");
        user.setAge(10);

        return user;
    }*/

    // http://localhost:9090/api/server/hello?name=steve&age=10
    @GetMapping("/hello")
    public User hello(@RequestParam String name, @RequestParam int age) {
        User user = new User();

        user.setName(name);
        user.setAge(age);

        return user;
    }
}
