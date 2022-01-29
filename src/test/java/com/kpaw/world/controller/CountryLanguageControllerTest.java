package com.kpaw.world.controller;

import com.kpaw.world.entity.Country;
import com.kpaw.world.entity.CountryLanguage;
import com.kpaw.world.entity.IsOfficial;
import com.kpaw.world.service.CountryLanguageService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(CountryLanguageController.class)
class CountryLanguageControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CountryLanguageService service;

    @Mock
    Country country;

    CountryLanguage validLanguage;
    CountryLanguage validLanguage2;
    List<CountryLanguage> allValidLanguages;

    @BeforeEach
    void setUp() {
        validLanguage = new CountryLanguage(country, "language", IsOfficial.T, 95.0);
        validLanguage2 = new CountryLanguage(country, "language2", IsOfficial.F, 5.0);
        allValidLanguages = new ArrayList<>();
        allValidLanguages.add(validLanguage);
        allValidLanguages.add(validLanguage2);
    }

    @AfterEach
    void tearDown(){
        reset(service);
    }

    @Test
    void testShowCountryLanguages() throws Exception {
        mockMvc.perform(get("/languages/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("languages"))
                .andExpect(view().name("/languages/list-languages"));
    }

    @Test
    void testSearch() throws Exception {
        given(service.searchBy("language", "countryName")).willReturn(allValidLanguages);
        mockMvc.perform(get("/languages/search").param("language", "language")
                .param("country" ,"countryName"))
                .andExpect(model().attributeExists("languages"))
                .andExpect(status().isOk())
                .andExpect(view().name("/languages/list-languages"));
    }

    @Test
    void testEmptySearch() throws Exception {
        mockMvc.perform(get("/languages/search").param("language", "")
                        .param("country" ,""))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/languages/list"));
    }

    @Test
    void testSortByCountry() throws Exception {
        mockMvc.perform(get("/languages/orderByCountry"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("languages"))
                .andExpect(view().name("/languages/list-languages"));
    }

    @Test
    void testSortByLanguage() throws Exception {
        mockMvc.perform(get("/languages/orderByLanguage"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("languages"))
                .andExpect(view().name("/languages/list-languages"));
    }
}