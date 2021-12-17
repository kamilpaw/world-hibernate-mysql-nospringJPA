package com.kpaw.world.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "country")
public class Country {

	@Id
	@Column(name = "Code")
	@Pattern(regexp = "^[A-Z]{3}", message = "only 3 capital letters")
	private String code;

	@Column(name = "Name")
	private String name;

	@Column(name = "Region")
	private String region;

	@Column(name = "SurfaceArea")
	private Double surfaceArea;

	@Column(name = "IndepYear")
	private Short indepYear;

	@Column(name = "Population")
	private Integer population;

	@Column(name = "LifeExpectancy")
	private Double lifeExpectancy;

	@Column(name = "GNP")
	private Double gNP;

	@Column(name = "GNPOld")
	private Double gNPOld;

	@Column(name = "LocalName")
	private String localName;

	@Column(name = "GovernmentForm")
	private String governmentForm;

	@Column(name = "HeadOfState")
	private String headOfState;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "Capital")
	private City capital;

	@Column(name = "Code2")
	private String code2;

	@OneToMany(mappedBy = "country", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	private List<City> cities;

	@OneToMany(mappedBy = "countryCode", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	private List<CountryLanguage> countryLanguages;

	public Country() {

	}

	public Country(String code, String name, String region, Double surfaceArea, Short indepYear, Integer population,
			Double lifeExpectancy, Double gNP, Double gNPOld, String localName, String governmentForm,
			String headOfState, City capital, String code2, List<City> cities, List<CountryLanguage> countryLanguages) {
		super();
		this.code = code;
		this.name = name;
		this.region = region;
		this.surfaceArea = surfaceArea;
		this.indepYear = indepYear;
		this.population = population;
		this.lifeExpectancy = lifeExpectancy;
		this.gNP = gNP;
		this.gNPOld = gNPOld;
		this.localName = localName;
		this.governmentForm = governmentForm;
		this.headOfState = headOfState;
		this.capital = capital;
		this.code2 = code2;
		this.cities = cities;
		this.countryLanguages = countryLanguages;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getRegion() {
		return region;
	}

	public Double getSurfaceArea() {
		return surfaceArea;
	}

	public Short getIndepYear() {
		return indepYear;
	}

	public Integer getPopulation() {
		return population;
	}

	public Double getLifeExpectancy() {
		return lifeExpectancy;
	}

	public Double getgNP() {
		return gNP;
	}

	public Double getgNPOld() {
		return gNPOld;
	}

	public String getLocalName() {
		return localName;
	}

	public String getGovernmentForm() {
		return governmentForm;
	}

	public String getHeadOfState() {
		return headOfState;
	}

	public City getCapital() {
		return capital;
	}

	public String getCode2() {
		return code2;
	}

	public List<City> getCities() {
		return cities;
	}

	public List<CountryLanguage> getCountryLanguages() {
		return countryLanguages;
	}

}
