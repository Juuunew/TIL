package com.example.hello.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostRequestDto {

    private String account;
    private String email;
    private String address;
    private String password;
    // 카멜케이스  / 스네이크케이스 -> 변환, 매칭
    @JsonProperty("phone_number")
    private String phoneNumber; // phone_number
}
