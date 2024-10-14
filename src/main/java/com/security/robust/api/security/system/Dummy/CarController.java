package com.security.robust.api.security.system.Dummy;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cars")
@Tag(name = "car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping
    ResponseEntity<Integer> addCar
            (@RequestBody CarRequest carRequest,
             Authentication authentication) {
        System.out.println("CONTROLLER CALLED");
        return ResponseEntity.ok().
                body(carService.createCar(authentication, carRequest));
    }

    @GetMapping
    ResponseEntity<List<CarResponse>> getAllCars(Authentication authentication) {
        System.out.println("CONTROLLER CALLED");
        return ResponseEntity.ok().
                body(carService.readAllCars(authentication));
    }
}
