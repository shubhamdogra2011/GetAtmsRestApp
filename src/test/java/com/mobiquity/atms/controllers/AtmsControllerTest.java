package com.mobiquity.atms.controllers;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.mobiquity.atms.entities.Atm;
import com.mobiquity.atms.exceptions.AtmNotFoundException;
import com.mobiquity.atms.exceptions.InternalException;
import com.mobiquity.atms.exceptions.InvalidInputException;
import com.mobiquity.atms.services.AtmsService;


@WebMvcTest(AtmsController.class)
public class AtmsControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AtmsService atmsService;
	
	@MockBean
	private RestTemplate restTemplate;
	
	@Autowired
	private Gson gson;
	
	@Autowired
	Type atmsListType;
	
	@Autowired
	Atm atm;
	
	@Value("${test.mockalljsonfile}")
	String mockAllJsonFile;
	
	@Value("${test.mockcityjsonfile}")
	String mockCityJsonFile;
	
	private static final String city = "amsterdam" ;
	
	@Test
	public void shouldReturnAllAtmsList() throws Exception {
		
		ArrayList<Atm> mockList = initMockList(mockAllJsonFile);
		
		when(atmsService.getAllAtms()).thenReturn(mockList);
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/atms/all"))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$").isNotEmpty())
		;
		
	}
	
	@Test
	public void shouldReturnAtmsByLocationList() throws Exception {
		
		ArrayList<Atm> mockList = initMockList(mockCityJsonFile);
		
		when(atmsService.getAtmsByLocation(city)).thenReturn(mockList);
		int iterator = 0;
		while (iterator < mockList.size()) {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/atms/"+city))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.["+iterator+"].address.city", containsStringIgnoringCase(city)))
			;
			iterator++;
		}
		
	}
	

	@Test
	public void atmNotFound() throws Exception {
		when(atmsService.getAtmsByLocation(city)).thenThrow(new AtmNotFoundException());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/atms/"+city))
				.andExpect(status().isNotFound());

	}
	
	@Test
	public void invalidCity() throws Exception {
		when(atmsService.getAtmsByLocation(city)).thenThrow(new InvalidInputException());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/atms/"+city))
				.andExpect(status().isNotAcceptable());

	}
	
	@Test
	public void internalError() throws Exception {
		when(atmsService.getAtmsByLocation(city)).thenThrow(new InternalException("Internal Error"));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/atms/"+city))
				.andExpect(status().isInternalServerError());

	}
	
	private ArrayList<Atm> initMockList(String mockFile) throws IOException {
		File file = new File(mockFile);
		String mockJson = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		ArrayList<Atm> mockList = gson.fromJson(mockJson, atmsListType);
		return mockList;
	}
	

}
