package com.security.robust.api.security.system.Dummy;

import com.security.robust.api.security.system.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarMapper carMapper;
    private final CarRepository carRepo;

    List<CarResponse> readAllCars(Authentication auth) {
        System.out.println("SERVICE CALLED");
        User user = (User) auth.getPrincipal();
        List<Car> cars = carRepo.findCars(user.getId());
        return cars.stream().
                map(carMapper::carToResponse).
                toList();
    }

    Integer createCar(Authentication auth, CarRequest carRequest) {
        System.out.println("SERVICE CALLED");
        User user = (User) auth.getPrincipal();
        Car car = carMapper.requestToCar(carRequest);
        car.setOwner(user);
        return carRepo.save(car).getId();
    }
}
