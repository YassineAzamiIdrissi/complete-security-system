package com.security.robust.api.security.system.Dummy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    @Query("""
                   SELECT car FROM Car car
                   WHERE car.owner != :userId
            """)
    List<Car> findCars(Integer userId);
}
