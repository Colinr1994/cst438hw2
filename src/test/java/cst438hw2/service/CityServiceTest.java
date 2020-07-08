package cst438hw2.service;
 
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import cst438hw2.domain.*;
 
@SpringBootTest
public class CityServiceTest {

	@MockBean
	private WeatherService weatherService;
	
	@Autowired
	private CityService cityService;
	
	@MockBean
	private CityRepository cityRepository;
	
	@MockBean
	private CountryRepository countryRepository;

	
	@Test
	public void contextLoads() {
	}


	@Test
	public void testCityFound() throws Exception {
		Country country = new Country("TST", "TEST");
		City city = new City(1, "TestCity", "TST", "TestDistrict", 1000);
		
		List<City> cities = new ArrayList<City>();
		cities.add(city);
		
		TempAndTime testVal = new TempAndTime(300, 100000, 1000);
				
		given(weatherService.getTempAndTime("TestCity")).willReturn(testVal);
		given(cityRepository.findByName("TestCity")).willReturn(cities);
		given(countryRepository.findByCode("TST")).willReturn(country);
		
		CityInfo result = cityService.getCityInfo("TestCity");
		CityInfo expected = new CityInfo(1, "TestCity", "TST", "TestCountry", "TestDistrict", 1000, 80.33, "23:15");
		assertEquals(expected,result);
	}
	
	@Test 
	public void  testCityNotFound() {
		
		Country country = new Country("TST","TEST");
		City city = new City(1, "TestCity","TST","TestDistrict",1000);
		
		List<City> cities = new ArrayList<>();
		cities.add(city);

		given(weatherService.getTempAndTime("TestCity")).willReturn(new TempAndTime(300, 100000, 1000));
		given(cityRepository.findByName("TestCity")).willReturn(cities);
		given(countryRepository.findByCode("TST")).willReturn(country);

		CityInfo result = cityService.getCityInfo("Random Name of City");
		CityInfo expected = null;

		assertEquals(expected,result);
	}
	
	@Test 
	public void  testCityMultiple() {
		Country country1 = new Country("TST", "TEST1");
		Country country2 = new Country("TST", "TEST1");
		Country country3 = new Country("TST", "TEST1");
		
		City city1 = new City(1,"TestCity", "TST1", "TestDistrict1", 1001);
		City city2 = new City(1,"TestCity", "TST2", "TestDistrict2", 1002);
		City city3 = new City(1,"TestCity", "TST3", "TestDistrict3", 1003);
		
		List<City> cities = new ArrayList<City>();
		cities.add(city1);
		cities.add(city2);
		cities.add(city3);
		
		given(weatherService.getTempAndTime("TestCity")).willReturn(new TempAndTime(300, 100000, 1000));
		given(cityRepository.findByName("TestCity")).willReturn(cities);
		given(countryRepository.findByCode("TST1")).willReturn(country1);
		given(countryRepository.findByCode("TST2")).willReturn(country2);
		given(countryRepository.findByCode("TST3")).willReturn(country3);
		
		CityInfo result = cityService.getCityInfo("TestCity");
		CityInfo expected = new CityInfo(1,"TestCity", "TST", "TestCountry", "TestDistrict", 1000, 80.33, "23:15");
		
		assertEquals(expected,result);
	}
}
