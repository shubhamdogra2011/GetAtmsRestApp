package com.mobiquity.atms.entities;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Atm {

	private Address address;
	private String distance;
	private List<OpeningHours> openingHours;
	private String functionality;
	private String type;

	public Atm(Address address, String distance, List<OpeningHours> openingHours, String functionality, String type) {
		super();
		this.address = address;
		this.distance = distance;
		this.openingHours = openingHours;
		this.functionality = functionality;
		this.type = type;
	}

	public Atm() {
		super();
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public List<OpeningHours> getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(List<OpeningHours> openingHours) {
		this.openingHours = openingHours;
	}

	public String getFunctionality() {
		return functionality;
	}

	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Atm [address=" + address + ", distance=" + distance + ", openingHours=" + openingHours
				+ ", functionality=" + functionality + ", type=" + type + "]";
	}

}
