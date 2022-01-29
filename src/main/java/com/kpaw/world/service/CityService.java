package com.kpaw.world.service;

import java.util.List;

import com.kpaw.world.entity.City;

public interface CityService {

	List<City> findAll();

	List<City> searchBy(String theName, String theCountry);

	List<City> orderByName();

	List<City> orderByCountry();

	List<City> orderByPopulation();

	void save(City theCity);

	void deleteCityById(int theId);

	City findById(int theId);
}
