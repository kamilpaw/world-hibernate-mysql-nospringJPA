package com.kpaw.world.controller;

import com.kpaw.world.entity.City;
import com.kpaw.world.entity.Country;
import com.kpaw.world.service.CountryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CountryController.class)
class CountryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CountryService service;

    @Mock
    City city;

    Country validCountry;
    Country validCountry2;
    List<Country> allValidCountries;

    @BeforeEach
    void setUp() {
        validCountry = new Country("code","name", "region", 10.0, (short) 100, 10, 100.0, 100.0, 100.0, "localName", "governmentForm", "headOfState", city, "codeB");
        validCountry2 = new Country("code2","name2", "region2", 10.0, (short) 100, 10, 100.0, 100.0, 100.0, "localName2", "governmentForm", "headOfState", city, "codeB2");
        allValidCountries = new ArrayList<>();
        allValidCountries.add(validCountry);
        allValidCountries.add(validCountry2);
    }

    @AfterEach
    void teardown(){
        reset(service);
    }

    @Test
    void testShowCountries() throws Exception {
        mockMvc.perform(get("/countries/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("countries"));
    }

    @Test
    void testSearch() throws Exception {
        given(service.searchBy("code", "name", "region")).willReturn(allValidCountries);
        mockMvc.perform(get("/countries/search").param("code", "code")
                .param("name", "name").param("region", "region"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("countries"))
                .andExpect(view().name("countries/list-countries"));
    }

    @Test
    void testEmptySearch() throws Exception {
                mockMvc.perform(get("/countries/search").param("code", "")
                        .param("name", "").param("region", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/countries/list"));
    }

    @Test
    void testSortByRegion() throws Exception {
        mockMvc.perform(get("/countries/sortByRegion"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("countries"))
                .andExpect(view().name("countries/list-countries"));
    }

    @Test
    void testSortByName() throws Exception {
        mockMvc.perform(get("/countries/sortByName"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("countries"))
                .andExpect(view().name("countries/list-countries"));
    }

    @Test
    void testSortBySurface() throws Exception {
        mockMvc.perform(get("/countries/sortBySurface"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("countries"))
                .andExpect(view().name("countries/list-countries"));
    }

    @Test
    void testSortByCode() throws Exception {
        mockMvc.perform(get("/countries/sortByCode"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("countries"))
                .andExpect(view().name("countries/list-countries"));
    }

}