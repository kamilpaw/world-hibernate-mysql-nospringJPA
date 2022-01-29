package com.kpaw.world.service;

import java.util.List;

import com.kpaw.world.entity.CountryLanguage;

public interface CountryLanguageService {

	List<CountryLanguage> findAll();

	List<CountryLanguage> searchBy(String theLanguage, String theCountry);

	List<CountryLanguage> orderByName();

	List<CountryLanguage> orderByLanguage();

}
