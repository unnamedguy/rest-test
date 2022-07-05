package com.unnamedguy.resttest.service;

import com.unnamedguy.resttest.model.Car;

import java.util.List;

public interface ICarService {

    Car create(Car car);

    List<Car> readAll();

    Car read(Long id);

    boolean update(Car car, int id);

    boolean delete(int id);

    boolean deleteAll();
}