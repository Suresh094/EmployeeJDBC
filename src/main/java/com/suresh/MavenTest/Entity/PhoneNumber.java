package com.suresh.MavenTest.Entity;


public class PhoneNumber {
	private String areaCode;
	private String number;
	private Employee owner;
	
	
	public PhoneNumber(String areaCode, String number) {
		this.areaCode = areaCode;
		this.number = number;
		owner = null;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Employee getOwner() {
		return owner;
	}
	public void setOwner(Employee owner) {
		this.owner = owner;
	}
	@Override
	public String toString() {
		return "\n\t\tPhoneNumber: " + areaCode + 
				"-" + number + "\n\t\tOwner name: " 
				+ owner.getName() + "\n";
	}

}
