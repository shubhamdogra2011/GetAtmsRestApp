package com.mobiquity.atms.services;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.mobiquity.atms.entities.Atm;
import com.mobiquity.atms.exceptions.AtmNotFoundException;
import com.mobiquity.atms.exceptions.InternalException;
import com.mobiquity.atms.exceptions.InvalidInputException;

@Service
public class AtmsServiceImpl implements AtmsService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Gson gson;

	@Autowired
	private Type atmsListType;

	@Value("${baseuri}")
	String baseUri;

	@Override
	public List<Atm> getAtmsByLocation(String city) {
		
		try {
			
//			City must be valid alphabetical string
			if (!Pattern.matches("^[a-zA-Z]+$", city)) {
				throw new InvalidInputException();
			}
			
			ArrayList<Atm> listOfAtmsByCity = new ArrayList<Atm>();

			ArrayList<Atm> listOfAllAtms = fetchData();
			for (Atm atm : listOfAllAtms) {
				if (atm.getAddress().getCity().equalsIgnoreCase(city)) {
					listOfAtmsByCity.add(atm);
				}
			}

			if (listOfAtmsByCity.isEmpty()) {
				throw new AtmNotFoundException();
			}
			
			return listOfAtmsByCity;
			
		} catch (Exception e) {
			if (e.getClass().equals(AtmNotFoundException.class)) {
				throw new AtmNotFoundException();
			} else if (e.getClass().equals(InvalidInputException.class)) {
				throw new InvalidInputException();
			} else {
				throw new InternalException(e.getMessage());
			}
		}

	}

	@Override
	public List<Atm> getAllAtms() {

		return fetchData();

	}

	private ArrayList<Atm> fetchData() {
		
		try {
			String result = restTemplate.getForObject(baseUri, String.class);
			if (result.isEmpty()) {
				throw new AtmNotFoundException();
			}
			
//			 the ING api returns invalid json response. Has invalid characters )]}', at the start of the response 
//			 removing those invalid characters
			result = result.substring(6);
			
			ArrayList<Atm> list = gson.fromJson(result, atmsListType);

			return list;
		} catch (Exception e) {
			if (e.getClass().equals(AtmNotFoundException.class)) {
				throw new AtmNotFoundException();
			} else {
				throw new InternalException(e.getMessage());
			}

		}

	}

}
