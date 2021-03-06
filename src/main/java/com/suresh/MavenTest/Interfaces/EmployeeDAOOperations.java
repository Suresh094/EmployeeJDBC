package com.suresh.MavenTest.Interfaces;

import java.sql.SQLException;
import java.util.List;

import com.suresh.MavenTest.Entity.Employee;
import com.suresh.MavenTest.Exceptions.EmployeeNotFoundException;
import com.suresh.MavenTest.Exceptions.InvalidSalaryException;

public interface EmployeeDAOOperations {

	boolean addEmployee(List<Employee> empl) throws InvalidSalaryException, SQLException;

	boolean deleteEmployee(int empID) throws EmployeeNotFoundException, SQLException;

	Employee updateEmployeeInfo(Employee empl) throws EmployeeNotFoundException, InvalidSalaryException, SQLException;

	List<Employee> getEmployees() throws SQLException;

	public Employee getEmployeeById(int empID) throws EmployeeNotFoundException, SQLException;

	List<Employee> getHighestSalaryEmployee(double salary) throws SQLException;

	public List<Employee> sortEmployees(String str) throws SQLException;

	int getEmployeeSalary(int id) throws SQLException;

}
