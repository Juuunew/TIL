package com.example.validtaion.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
public class User {

    @NotBlank
    private String name;

    @Max(value = 90)
    private int age;

    /**
     * 특정 클래스나 특정 변수에 대하여 검증하고 싶으면 @Valid 를 꼭 붙여줘야 함.
     */
    @Valid
    private List<Car> cars;

}
