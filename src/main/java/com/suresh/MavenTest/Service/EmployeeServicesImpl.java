package com.suresh.MavenTest.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.suresh.MavenTest.DAO.EmployeeJdbcDAOImpl;
import com.suresh.MavenTest.Entity.Employee;
import com.suresh.MavenTest.Exceptions.EmployeeNotFoundException;
import com.suresh.MavenTest.Exceptions.InvalidSalaryException;
import com.suresh.MavenTest.Interfaces.EmployeeDAOOperations;
import com.suresh.MavenTest.Interfaces.EmployeeServices;

public class EmployeeServicesImpl implements EmployeeServices {

	EmployeeDAOOperations dao = null;

	public EmployeeServicesImpl() {
		dao = new EmployeeJdbcDAOImpl();
	}

	public boolean addEmployee(Employee e) throws InvalidSalaryException, SQLException {
		List<Employee> list = new ArrayList<>();
		if (e.getSalary() < 5000.0)
			throw new InvalidSalaryException("Invalid Salary Details, Salary should be greater than 5000");
		list.add(e);
		dao.addEmployee(list);
		return true;
	}

	@Override
	public boolean deleteEmployee(int empID) throws EmployeeNotFoundException, SQLException {
		return dao.deleteEmployee(empID);
	}

	@Override
	public Employee updateEmployeeInfo(Employee empl)
			throws EmployeeNotFoundException, InvalidSalaryException, SQLException {
		if (empl.getSalary() < 5000)
			throw new InvalidSalaryException("Invalid Salary Details ");
		return dao.updateEmployeeInfo(empl);

	}

	@Override
	public List<Employee> getEmployees() throws SQLException {

		return dao.getEmployees();
	}

	@Override
	public Employee getEmployeeById(int empID) throws EmployeeNotFoundException, SQLException {

		return dao.getEmployeeById(empID);

	}

	@Override
	public List<Employee> getHighestSalaryEmployee(double salary) throws SQLException {

		return dao.getHighestSalaryEmployee(salary);
	}

	@Override
	public List<Employee> sortEmployees(String str) throws SQLException {

		return dao.sortEmployees(str);
	}

	@Override
	public double getEmployeeHRA(int id) {
		try {
			return dao.getEmployeeSalary(id) * 0.2;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0.0;
	}
		
		@Override
		public double getEmployeeGrossSalary(int id) throws EmployeeNotFoundException {
			try {
				double grossSalary = 0;
				Employee e = dao.getEmployeeById(id);
				
				if ((e != null) && id == e.getId()) {
					if (e.getSalary() < 10000) {
						double hra = this.getEmployeeHRA(1);
						double allowence = 0.08 * e.getSalary();
						hra = 0.15 * e.getSalary();
						//e.setDailyAllowence(allowence);
						//e.setHra(hra);
						grossSalary = hra + allowence + e.getSalary();
						//e.setGrossSalary(grossSalary);

					return grossSalary;

					} else if (e.getSalary() < 20000) {
						double allowence = 0.10 * e.getSalary();
						double hra = 0.20 * e.getSalary();
						grossSalary = hra + allowence + e.getSalary();
						//e.setGrossSalary(grossSalary);

					return grossSalary;

					} else if (e.getSalary() < 30000 && e.getAge() >= 40) {
						double allowence = 0.15 * e.getSalary();
						double hra = 0.27 * e.getSalary();

						grossSalary = hra + allowence + e.getSalary();
						//e.setGrossSalary(grossSalary);

						return grossSalary;

					} else if (e.getSalary() < 30000 && e.getAge() < 40) {
						double allowence = 0.15 * e.getSalary();
						double hra = 0.27 * e.getSalary();
						
						grossSalary = hra + allowence + e.getSalary();
						//e.setGrossSalary(grossSalary);

						return grossSalary;

					} else {
						double allowence = 0.17 * e.getSalary();
						double hra = 0.30 * e.getSalary();
						
						grossSalary = hra + allowence + e.getSalary();
						//e.setGrossSalary(grossSalary);

						return grossSalary;

					}
				
			} 
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return 0.0;


		}
}
