package com.unnamedguy.resttest.repository;

import com.unnamedguy.resttest.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByName(String name);

    List<Car> findByEmail(String email);
}
