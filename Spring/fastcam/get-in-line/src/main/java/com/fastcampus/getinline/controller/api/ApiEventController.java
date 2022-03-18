package com.fastcampus.getinline.controller.api;

import com.fastcampus.getinline.constant.EventStatus;
import com.fastcampus.getinline.dto.ApiDataResponse;
import com.fastcampus.getinline.dto.EventRequest;
import com.fastcampus.getinline.dto.EventResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/api")
@RestController
public class ApiEventController {

    @GetMapping("/events")
    public ApiDataResponse<List<EventResponse>> getEvents() {
        return ApiDataResponse.of(List.of(EventResponse.of(
                1L,
                "오후 운동",
                EventStatus.OPENED,
                LocalDateTime.of(2021, 1, 1, 13, 0, 0),
                LocalDateTime.of(2021, 1, 1, 16, 0, 0),
                0,
                24,
                "마스크 꼭 착용하세요"
        )));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/events")
    public ApiDataResponse<Void> createEvent(@RequestBody EventRequest eventRequest) {
        return ApiDataResponse.empty();
    }

    @GetMapping("/events/{eventId}")
    public ApiDataResponse<EventResponse> getEvent(@PathVariable Long eventId) {
        if (eventId.equals(2L)) {
            return ApiDataResponse.empty();
        }

        return ApiDataResponse.of(EventResponse.of(
                1L,
                "오후 운동",
                EventStatus.OPENED,
                LocalDateTime.of(2021, 1, 1, 13, 0, 0),
                LocalDateTime.of(2021, 1, 1, 16, 0, 0),
                0,
                24,
                "마스크 꼭 착용하세요"
        ));
    }

    @PutMapping("/events/{eventId}")
    public ApiDataResponse<Void> modifyEvent(
            @PathVariable Long eventId,
            @RequestBody EventRequest eventRequest
    ) {
        return ApiDataResponse.empty();
    }

    @DeleteMapping("/events/{eventId}")
    public ApiDataResponse<Void> removeEvent(@PathVariable Long eventId) {
        return ApiDataResponse.empty();
    }
}
