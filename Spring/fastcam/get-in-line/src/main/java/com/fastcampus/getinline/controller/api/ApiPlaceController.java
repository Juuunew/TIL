package com.fastcampus.getinline.controller.api;

import com.fastcampus.getinline.constant.PlaceType;
import com.fastcampus.getinline.dto.ApiDataResponse;
import com.fastcampus.getinline.dto.PlaceDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ApiPlaceController {

    @GetMapping("/places")
    public ApiDataResponse<List<PlaceDto>> getPlaces() {
        return ApiDataResponse.of(List.of(PlaceDto.of(
                PlaceType.COMMON,
                "랄라배드민턴장",
                "서울기 강남구 강남대로 1234",
                "010-1234-5678",
                30,
                "신장개업"
        )));
    }

    @PostMapping("/places")
    public Boolean createPlace() {
        return true;
    }

    @GetMapping("/places/{placeId}")
    public String getPlace(@PathVariable Integer placeId) {
        return "place" + placeId;
    }

    @PutMapping("/places/{placeId}")
    public Boolean modifyPlace(@PathVariable Integer placeId) {
        return true;
    }

    @DeleteMapping("/places/{placeId}")
    public Boolean removePlace(@PathVariable Integer placeId) {
        return true;
    }

}
