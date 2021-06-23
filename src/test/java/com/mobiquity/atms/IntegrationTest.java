package com.mobiquity.atms;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.mobiquity.atms.entities.Atm;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private Gson gson;

	@Autowired
	private Type atmsListType;
	
	private static final String city = "amsterdam" ;

	@Test
	public void testGetAtmsByLocationApi() {
		ResponseEntity<String> response = restTemplate.getForEntity("/atms/" + city, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotEmpty();

		String responseBody = response.getBody();
		ArrayList<Atm> list = gson.fromJson(responseBody, atmsListType);
		int iterator = 0;
		while (iterator < list.size()) {
			assertThat(list.get(iterator).getAddress().getCity()).isEqualToIgnoringCase(city);
			iterator++;
		}

	}


	@Test
	public void testGetAllAtmsApi() throws JSONException {
		ResponseEntity<String> response = restTemplate.getForEntity("/atms/all", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotEmpty();

	}

}
