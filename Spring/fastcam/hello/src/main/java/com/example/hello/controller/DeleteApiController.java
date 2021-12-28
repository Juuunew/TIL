package com.example.hello.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeleteApiController {

    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable Long userId,
                       @RequestParam String account) {
        System.out.println("userId = " + userId + ", account = " + account);
    }
}
