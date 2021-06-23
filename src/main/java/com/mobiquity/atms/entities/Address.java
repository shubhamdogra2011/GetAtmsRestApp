package com.mobiquity.atms.entities;

public class Address {
	private String housenumber;
	private String city;
	private GeoLocation geoLocation;
	private String street;
	private String postalcode;
	
	public Address(String housenumber, String city, GeoLocation geoLocation, String street, String postalcode) {
		super();
		this.housenumber = housenumber;
		this.city = city;
		this.geoLocation = geoLocation;
		this.street = street;
		this.postalcode = postalcode;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getHousenumber() {
		return housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	@Override
	public String toString() {
		return "Address [housenumber=" + housenumber + ", city=" + city + ", street=" + street + ", postalcode="
				+ postalcode + "]";
	}
	
	
}
