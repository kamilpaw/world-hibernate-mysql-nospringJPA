package com.kpaw.world.service;

import com.kpaw.world.dao.CityRepository;
import com.kpaw.world.entity.City;
import com.kpaw.world.entity.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CityServiceImpTest {

    @Mock
    CityRepository cityRepository;

    private City city1, city2;
    private List<City> cities;

    @Mock
    Country country;


    @InjectMocks
    CityServiceImp service;

    @BeforeEach
    void setUp() {
        city1 = new City(1, "name", country, "District", 1);
        city2 = new City(2, "name2", country, "District", 2);
        cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);
    }

    @Test
    void findAll() {
        given(cityRepository.findAll()).willReturn(cities);
        service.findAll();
        then(cityRepository).should().findAll();
        assertEquals(2, cityRepository.findAll().size());
        assertEquals(2, service.findAll().size());
    }

    @Test
    void searchBy() {
        given(cityRepository.findByNameAndCountry("city", "country")).willReturn(cities);
        service.searchBy("city", "country");
        then(cityRepository).should().findByNameAndCountry("city", "country");
    }

    @Test
    void orderByName() {
        service.orderByName();
        then(cityRepository).should().sortByNameAsc();

    }

    @Test
    void orderByCountry() {
        service.orderByCountry();
        then(cityRepository).should().sortByCountryNameAsc();
    }

    @Test
    void orderByPopulation() {
        service.orderByPopulation();
        then(cityRepository).should().sortByPopulationAsc();
    }

    @Test
    void save() {
        service.save(city1);
        then(cityRepository).should().save(city1);
    }

    @Test
    void deleteCityById() {
        service.deleteCityById(1);
        verify(cityRepository, times(1)).deleteCityById(1);
    }

    @Test
    void findById() {
        City city = new City();
        given(cityRepository.findById(1)).willReturn(city);
        City foundCity = service.findById(1);
        then(cityRepository).should().findById(1);
        assertThat(foundCity).isNotNull();
    }

}