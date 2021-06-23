package com.mobiquity.atms.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mobiquity.atms.entities.Atm;
import com.mobiquity.atms.exceptions.AtmNotFoundException;
import com.mobiquity.atms.exceptions.InternalException;
import com.mobiquity.atms.exceptions.InvalidInputException;
import com.mobiquity.atms.services.AtmsService;


@RestController
@RequestMapping("/atms")
public class AtmsController {

	@Autowired
	private AtmsService atmsService;
	
	public static final Logger logger = LoggerFactory.getLogger(AtmsController.class);

	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<Atm>> getAllAtms() {
		List<Atm> listOfAtms = atmsService.getAllAtms();
		return new ResponseEntity<List<Atm>>(listOfAtms, HttpStatus.OK);
	}

	@GetMapping("/{city}")
	@ResponseBody
	public ResponseEntity<List<Atm>> getAtmsByLocation(@PathVariable("city") String city) {
		List<Atm> listOfAtms = atmsService.getAtmsByLocation(city);
		return new ResponseEntity<List<Atm>>(listOfAtms, HttpStatus.OK);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private void atmNotFoundHandler(AtmNotFoundException ex) {
		logger.error("Atm Not Found");
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	private void invalidCityHandler(InvalidInputException ex) {
		logger.error("Must provide valid city name.");
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	private void internalErrorHandler(InternalException ex) {
		logger.error("Internal Error: " + ex.getMessage());
	}

}