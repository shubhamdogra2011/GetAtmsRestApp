package com.mobiquity.atms.entities;

import java.util.List;

public class OpeningHours {
	private List<Hours> hours;
    private String dayOfWeek;
    
	public OpeningHours(List<Hours> hours, String dayOfWeek) {
		super();
		this.hours = hours;
		this.dayOfWeek = dayOfWeek;
	}

	public List<Hours> getHours() {
		return hours;
	}

	public void setHours(List<Hours> hours) {
		this.hours = hours;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	@Override
	public String toString() {
		return "OpeningHours [hours=" + hours + ", dayOfWeek=" + dayOfWeek + "]";
	}
    
	
    
}
