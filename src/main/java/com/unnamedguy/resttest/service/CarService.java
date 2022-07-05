package com.unnamedguy.resttest.service;

import com.unnamedguy.resttest.model.Car;
import com.unnamedguy.resttest.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CarService implements ICarService {
    @Autowired
    private CarRepository repository;

    // Хранилище клиентов
    private static final Map<Integer, Car> CLIENT_REPOSITORY_MAP = new HashMap<>();

    // Переменная для генерации ID клиента
    private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();

    @Override
    public Car create(Car car) {
        try {
            return repository
                    .save(new Car(car.getBrand(), car.getModelName(), car.getColor(), car.getPlaces()));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Car> readAll() {
        return repository.findAll();
    }

    @Override
    public Car read(Long id) {
        return CLIENT_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Car car, int id) {
        if (CLIENT_REPOSITORY_MAP.containsKey(id)) {
            car.setId(id);
            CLIENT_REPOSITORY_MAP.put(id, car);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        return CLIENT_REPOSITORY_MAP.remove(id) != null;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}