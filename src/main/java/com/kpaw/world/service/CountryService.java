package com.kpaw.world.service;

import java.util.List;

import com.kpaw.world.entity.Country;

public interface CountryService {

	List<Country> findAll();

	List<Country> searchBy(String theCode, String theName, String theRegion);

	List<Country> orderByRegion();

	List<Country> orderByName();

	List<Country> orderBySurface();

	List<Country> orderByCode();

	Country findById(String theCountryCode);
}
