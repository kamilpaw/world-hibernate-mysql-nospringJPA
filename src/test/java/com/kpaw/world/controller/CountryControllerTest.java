package com.kpaw.world.controller;

import com.kpaw.world.service.CountryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CountryController.class)
class CountryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CountryService service;

    @BeforeEach
    void setup(){

    }

    @AfterEach
    void teardown(){

    }


    @Test
    void testShowCountries() {
    }

    @Test
    void testSearch() {
    }

    @Test
    void testSortByRegion() {
    }

    @Test
    void testSortByName() {
    }

    @Test
    void testSortBySurface() {
    }

    @Test
    void testSortByCode() {
    }

    @Test
    void advSearchSite() {
    }
}