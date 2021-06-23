package com.mobiquity.atms.entities;

public class Hours {
	private String hourFrom;
	private String hourTo;

	public Hours(String hourFrom, String hourTo) {
		super();
		this.hourFrom = hourFrom;
		this.hourTo = hourTo;
	}

	public String getHourFrom() {
		return hourFrom;
	}

	public void setHourFrom(String hourFrom) {
		this.hourFrom = hourFrom;
	}

	public String getHourTo() {
		return hourTo;
	}

	public void setHourTo(String hourTo) {
		this.hourTo = hourTo;
	}

	@Override
	public String toString() {
		return "Hours [hourFrom=" + hourFrom + ", hourTo=" + hourTo + "]";
	}

}
