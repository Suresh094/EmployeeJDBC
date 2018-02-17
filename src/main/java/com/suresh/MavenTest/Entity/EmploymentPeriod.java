package com.suresh.MavenTest.Entity;


import java.time.LocalDate;

public class EmploymentPeriod {
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean isActive;
	
	public EmploymentPeriod(LocalDate startDate) {
		this.startDate = startDate;
		this.endDate = null;
		isActive = true;
	}
	public EmploymentPeriod(LocalDate startDate, LocalDate endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		if(endDate.isBefore(LocalDate.now())){
			isActive = false;
		}
		else{
			isActive = true;
		}
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
		if(endDate.isBefore(LocalDate.now())){
			isActive = false;
		}
	}
	public boolean isActive() {
		return isActive;
	}
	@Override
	public String toString() {
		return "\tEmploymentPeriod \n\t\tStartDate: " + startDate + 
				"\n\t\tEndDate: " + endDate + "\n\t\tActivity Status: " + isActive() + "\n";
	}
	
}
