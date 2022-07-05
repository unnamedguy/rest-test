package com.unnamedguy.resttest.controller;

import com.unnamedguy.resttest.model.Car;
import com.unnamedguy.resttest.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CarController {
    @Autowired
    CarRepository repository;

    // POST запрос по адресу /cars
    @PostMapping("/cars")
    public ResponseEntity<?> create(@RequestBody Car car) {
        try {
            Car _car = repository
                    .save(new Car(car.getBrand(), car.getModelName(), car.getColor(), car.getPlaces()));
            return new ResponseEntity<>(_car, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET запрос по адресу /cars
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> read() {
        try {
            List<Car> cars = new ArrayList<>(repository.findAll());

            if (cars.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cars, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET запрос по адресу /cars/{id}
    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> read(@PathVariable(name = "id") Long id) {
        Optional<Car> carData = repository.findById(id);
        if (carData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(carData.get(), HttpStatus.OK);
    }

    // PUT запрос по адресу /cars/{id}
    @PutMapping("/cars/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody Car car) {
        Optional<Car> carData = repository.findById(id);
        if (carData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        Car _car = carData.get();
        _car.setBrand(car.getBrand());
        _car.setModelName(car.getModelName());
        _car.setColor(car.getColor());
        _car.setPlaces(car.getPlaces());
        return new ResponseEntity<>(repository.save(_car), HttpStatus.OK);
    }

    // DELETE запрос по адресу /cars/{id}
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
