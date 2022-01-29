package com.kpaw.world.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kpaw.world.entity.City;
import com.kpaw.world.service.CityService;

@Controller
@RequestMapping("/cities")
public class CityController {

	private final CityService cityService;

	public CityController(CityService theCityService) {
		this.cityService = theCityService;

	}

	@GetMapping("/list")
	public String showCities(Model theModel) {
		List<City> theCities = cityService.findAll();
		theModel.addAttribute("cities", theCities);
		return "cities/list-cities";
	}

	@GetMapping("/addCity")
	public String addCityForm(Model theModel) {
		City theCity = new City();
		theModel.addAttribute("city", theCity);
		return "cities/city-form";
	}

	@GetMapping("/updateCity")
	public String updateCityForm(@RequestParam("cityId") int theId, Model theModel) {
		City theCity = cityService.findById(theId);
		theModel.addAttribute("city", theCity);
		return "cities/city-form";
	}

	@GetMapping("/deleteCity")
	public String deleteCityById(@RequestParam("cityId") int theId) {
		cityService.deleteCityById(theId);
		return "redirect:/cities/list";
	}

	@GetMapping("/search")
	public String search(@RequestParam("name") String theName, @RequestParam("country") String theCountry,
			Model theModel) {
		if (theName.trim().isEmpty() && theCountry.isEmpty()) {
			return "redirect:/cities/list";
		} else {
			List<City> theCities = cityService.searchBy(theName, theCountry);
			theModel.addAttribute("cities", theCities);
			return "cities/list-cities";
		}
	}

	@GetMapping("/orderByName")
	public String sortByName(Model theModel) {
		List<City> theCities = cityService.orderByName();
		theModel.addAttribute("cities", theCities);
		return "cities/list-cities";
	}

	@GetMapping("/orderByCountry")
	public String sortByCountry(Model theModel) {
		List<City> theCities = cityService.orderByCountry();
		theModel.addAttribute("cities", theCities);
		return "cities/list-cities";
	}

	@GetMapping("/orderByPopulation")
	public String sortByPopulation(Model theModel) {
		List<City> theCities = cityService.orderByPopulation();
		theModel.addAttribute("cities", theCities);
		return "cities/list-cities";
	}

	@PostMapping("/saveCity")
	public String saveCity(@ModelAttribute("city") @Valid City theCity, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "cities/city-form";
		} else {
			try {
				cityService.save(theCity);
				return "redirect:/cities/list";
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		return "cities/city-form";

	}
}
