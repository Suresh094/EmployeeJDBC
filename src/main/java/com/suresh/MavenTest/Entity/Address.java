package com.suresh.MavenTest.Entity;


import java.io.Serializable;

public class Address  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String city;
	private String country;
	private String postalCode;
	
	
	public Address(String city, String country, String postalCode) {
		this.city = city;
		this.country = country;
		this.postalCode = postalCode;
	}
	

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	@Override
	public String toString() {
		return "Address: \n\tCity: " + city + "\n\tCountry: " + country + 
				"\n\tPostalCode: " + postalCode + "\n";
	}
	
	
}
