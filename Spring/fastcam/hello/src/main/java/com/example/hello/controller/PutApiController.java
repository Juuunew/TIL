package com.example.hello.controller;

import com.example.hello.dto.PutRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {

    @PutMapping("/put")
    public PutRequestDto put(@RequestBody PutRequestDto putRequestDto) {
        System.out.println("putRequestDto = " + putRequestDto);
        return putRequestDto;
    }

    @PutMapping("/put/{userId}")
    public PutRequestDto put02(@PathVariable Long userId,
                               @RequestBody PutRequestDto putRequestDto) {
        System.out.println("userId = " + userId + ", putRequestDto = " + putRequestDto);
        return putRequestDto;
    }
}
