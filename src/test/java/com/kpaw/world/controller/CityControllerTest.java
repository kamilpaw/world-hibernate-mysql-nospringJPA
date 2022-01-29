package com.kpaw.world.controller;

import com.kpaw.world.entity.City;
import com.kpaw.world.entity.Country;
import com.kpaw.world.service.CityService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CityController.class)
class CityControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService service;

    @Mock
    Country country;

    City city1;
    City city2;
    List<City> cities;


    @BeforeEach
    void setUp() {
        city1 = new City(1, "city", country, "District", 1);
        city2 = new City(2, "city2", country, "District2", 2);
        cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);
    }

    @AfterEach
    void teardown() {
        reset(service);
    }

    @Test
    void testShowCities() throws Exception {
        mockMvc.perform(get("/cities/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("cities"))
                .andExpect(view().name("cities/list-cities"));
    }

    @Test
    void testAddCityForm() throws Exception {
        mockMvc.perform(get("/cities/addCity"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("city"))
                .andExpect(view().name("cities/city-form"));

    }

    @Test
    void testUpdateCityForm() throws Exception {
        given(service.findById(city1.getId())).willReturn(city1);
        mockMvc.perform(get("/cities/updateCity").param("cityId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteCityById() throws Exception {
        mockMvc.perform(get("/cities/deleteCity").param("cityId", "1"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testSearch() throws Exception {
        given(service.searchBy(city1.getName(), "countryName")).willReturn(cities);
        mockMvc.perform(get("/cities/search").param("name", city1.getName())
                        .param("country", "countryName"))
                .andExpect(model().attributeExists("cities"))
                .andExpect(status().isOk());
    }

    @Test
    void testSortByName() throws Exception {
        mockMvc.perform(get("/cities/orderByName"))
                .andExpect(model().attributeExists("cities"))
                .andExpect(status().isOk());
    }

    @Test
    void testSortByCountry() throws Exception {
        mockMvc.perform(get("/cities/orderByCountry"))
                .andExpect(model().attributeExists("cities"))
                .andExpect(status().isOk());
    }

    @Test
    void testSortByPopulation() throws Exception {
        mockMvc.perform(get("/cities/orderByPopulation"))
                .andExpect(model().attributeExists("cities"))
                .andExpect(status().isOk());

    }

    @Test
    void testSaveCitySuccess() throws Exception {
        mockMvc.perform(post("/cities/saveCity"))
                .andExpect(model().attributeExists("city"))
                .andExpect(status().isOk());
    }

    @Test
    void testSaveCityFailed() throws Exception {
        mockMvc.perform(post("/cities/saveCity"))
                .andExpect(model().attributeHasErrors("city"))
                .andExpect(status().isOk())
                .andExpect(view().name("cities/city-form"));
    }
}















