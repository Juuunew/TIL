package com.example.springcalculator.controller;

import com.example.springcalculator.component.Calculator;
import com.example.springcalculator.component.DollarCalculator;
import com.example.springcalculator.component.MarketApi;
import com.example.springcalculator.dto.Req;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Web 단위 테스트
 *
 * @SpringBootTest 에 비해 WEB 에 필요한 것들만 로딩 시키기 때문에 resource 소비를 줄일 수 있음
 */
@WebMvcTest(CalculatorApiController.class)
@AutoConfigureWebMvc
@Import({Calculator.class, DollarCalculator.class})
class CalculatorApiControllerTest {

    @MockBean
    private MarketApi marketApi;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        Mockito.when(marketApi.connect()).thenReturn(3000);
    }

    @Test
    void sumTest() throws Exception {
        // http://localhost:8080/api/sum

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/sum")
                        .queryParam("x", "10")
                        .queryParam("y", "10")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content().string("60000")
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void minus() throws Exception {

        Req req = new Req();

        req.setX(10);
        req.setY(10);

        String json = new ObjectMapper().writeValueAsString(req);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("http://localhost:8080/api/minus")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                ).andExpect(
                        MockMvcResultMatchers.status().isOk()
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$.result").value("0")
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$.response.resultCode").value("OK")
                ).andDo(MockMvcResultHandlers.print());
    }

}