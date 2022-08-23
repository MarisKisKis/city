package com.example.city.controller;

import com.example.city.model.City;
import com.example.city.storage.CityDaoImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/cities")
public class CityController {
    private final CityDaoImpl cityDaoImpl;

    @Autowired
    public CityController(CityDaoImpl cityDaoImpl) {
        this.cityDaoImpl = cityDaoImpl;
    }

    @GetMapping
    public String getCities(@RequestBody String queryString) {
        return cityDaoImpl.query(queryString);
    }

    @PostMapping
    public void addCity(@RequestBody String queryString) {
        cityDaoImpl.query(queryString);
    }

    @DeleteMapping
    public void deleteCity(@RequestBody String queryString) {
        cityDaoImpl.query(queryString);
    }
}
