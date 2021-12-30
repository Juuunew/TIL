package com.example.hello.controller;

import com.example.hello.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ResponseApiController {

    // TEXT
    @GetMapping("/text")
    public String text(@RequestParam String account) {
        return account;
    }

    // JSON

    /**
     * request -> object mapper -> object -> method -> object -> object mapper -> json -> response
     */
    @PostMapping("/json")
    public User json(@RequestBody User user) {
        return user;    // 200 ok
    }

    // ResponseEntity
    @PutMapping("/json-put")
    public ResponseEntity<User> put(@RequestBody User user) {

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
