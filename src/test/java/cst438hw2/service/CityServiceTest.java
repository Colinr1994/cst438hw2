package cst438hw2.service;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.anyString;

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
		CityInfo expected = new CityInfo(1, "TestCity", "TST", "TEST", "TestDistrict", 1000, 80.33, "04:03 AM");
		assertEquals(expected,result);
	}
	
	@Test 
	public void  testCityNotFound() {
		//Country country new Country("TST","TEST");
	}
	
	@Test 
	public void  testCityMultiple() {
		// TODO 
		
	}

}
