package com.example.city.storage;

import org.springframework.stereotype.Component;

@Component
public interface CityDao {
    public String query(String queryString);
}
