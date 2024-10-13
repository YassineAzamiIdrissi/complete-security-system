package com.security.robust.api.security.system.Dummy;

import org.springframework.stereotype.Service;

@Service
public class CarMapper {
    public Car requestToCar(CarRequest carRequest) {
        return Car.builder().
                id(carRequest.getId()).
                description(carRequest.getDescription()).
                name(carRequest.getName()).
                prodYear(carRequest.getYear()).
                build();
    }

    public CarResponse carToResponse(Car car) {
        return CarResponse.builder().
                car(car.getName()).
                description(car.getDescription()).
                ownerFullName(car.getOwner().generateFullName()).
                prodYear(car.getProdYear()).
                build();
    }
}
