package com.example.city.storage;

import com.example.city.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


@Component
@Qualifier("cityDaoImpl")
public class CityDaoImpl implements CityDao {
    final JdbcTemplate jdbcTemplate;

    @Autowired
    public CityDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String query(String queryString) {
        switch (queryString) {
            case ("create_table"):
                String createTableQuery = "CREATE TABLE Cities " +
                        "(id varchar(50) primary key," +
                        "name varchar(50)," +
                        "countryCode varchar(50)," +
                        "district varchar(50)," +
                        "population varchar(50))";
                jdbcTemplate.update(connection -> {
                    PreparedStatement pst1 = connection.prepareStatement(createTableQuery);
                    return pst1;
                });
                return "Succesfully created";
            case ("select_data"):
                List<City> cities = new ArrayList<>();
                SqlRowSet selectData = jdbcTemplate.queryForRowSet("SELECT c.id, c.name, c.countryCode, c.district, c.population" +
                        "FROM cities as c");
                while (selectData.next()) {
                    City city = new City(
                            selectData.getString("id"),
                            selectData.getString("name"),
                            selectData.getString("countryCode"),
                            selectData.getString("district"),
                            selectData.getString("population")
                    );
                    cities.add(city);
                }
                return cities.toString();
            case ("insert_city"):
                String insertData = "INSERT INTO cities (id, name, countryCode, district, population)" +
                        "VALUES ('value1', 'value2', 'value3', 'value4', 'value5')";
                jdbcTemplate.update(connection -> {
                    PreparedStatement pst1 = connection.prepareStatement(insertData);
                    return pst1;
                });
                return "City added";
            case ("update_city"):
                String udateCity = "UPDATE cities set id=valueNew, name=newName, countryCode=newCode, " +
                        "district=newDistrict, population=newPopulation";
                        jdbcTemplate.update(connection -> {
                            PreparedStatement pst1 = connection.prepareStatement(udateCity);
                            return pst1;
                        });
                return "City updated";
            case ("delete_city"):
                String deleteCity = "DELETE FROM cities where queryString=?";
                jdbcTemplate.update(connection -> {
                    PreparedStatement pst2 = connection.prepareStatement(deleteCity);
                    return pst2;
                });
                return "City deleted";
        }
        return queryString;
    }
}
