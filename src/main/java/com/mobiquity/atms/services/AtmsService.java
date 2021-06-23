package com.mobiquity.atms.services;

import java.util.List;

import com.mobiquity.atms.entities.Atm;

public interface AtmsService {

	public List<Atm> getAllAtms();

	public List<Atm> getAtmsByLocation(String city);

}
