package com.fastcampus.getinline.controller.api;

import com.fastcampus.getinline.dto.AdminRequest;
import com.fastcampus.getinline.dto.ApiDataResponse;
import com.fastcampus.getinline.dto.LoginRequest;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class ApiAuthController {

    @GetMapping("/sign-up")
    public String signUp() {
        return "done";
    }

    @GetMapping("/login")
    public String login() {
        return "done";
    }

    @PostMapping("/sign-up")
    public ApiDataResponse<String> signUp(@RequestBody AdminRequest adminRequest) {
        return ApiDataResponse.empty();
    }

    @PostMapping("/login")
    public ApiDataResponse<String> login(@RequestBody LoginRequest loginRequest) {
        return ApiDataResponse.empty();
    }
}
