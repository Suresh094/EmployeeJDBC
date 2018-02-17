package com.suresh.MavenTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.suresh.MavenTest.Entity.Employee;
import com.suresh.MavenTest.Exceptions.EmployeeNotFoundException;
import com.suresh.MavenTest.Exceptions.InvalidSalaryException;
import com.suresh.MavenTest.Interfaces.EmployeeServices;
import com.suresh.MavenTest.Service.EmployeeServicesImpl;

public class EmployeeApplication {
	static EmployeeServices emp = new EmployeeServicesImpl();

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in);) {
			System.out.println("Welcome to Employee Management App!! ");
			while (true) {
				System.out.println("=================================");
				System.out.println("|   EMPLOYEE MENU SELECTION     |");
				System.out.println("=================================");
				System.out.println("| Options:                      |");
				System.out.println("1 create Employee               |");
				System.out.println("2 Read All Employees Details    |");
				System.out.println("3 Update Employee   Details     |");
				System.out.println("4 Delete Employee               |");
				System.out.println("5 Display Employee By Id        |");
				System.out.println("6 SortEmployees                 |");
				System.out.println("7 getHighestSalaryEmployee      |");
				System.out.println("8 Get Employee Gross Salary     |");
				System.out.println("9 EXIT                          |");
				System.out.println("=================================");
				int operation;
				try {
					operation = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println(
							"You pressed an invalid Key!! " + "\nTry Again Running the application. We are quiting!!!");
					break;
				}
				switch (operation) {
				case 1:
					System.out.println("\nYou are now in the Employee Creation Module.");
					createEmployee(sc);
					break;
				case 2:
					readEmployeeDetails();
					break;
				case 3:
					updateEmployee(sc);
					break;
				case 4:
					deleteEmployee(sc);
					break;
				case 5:
					displayEmployeeById(sc);
					break;
				case 6:
					sortEmployees(sc);
					break;
				case 7:
					getHighestSalaryEmployee(sc);
					break;
				case 8:
					getEmployeeGrossSalary(sc);
					break;
				case 9:
					System.exit(0);
					break;
				}
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private static void getEmployeeGrossSalary(Scanner sc) {
		System.out.println("Enter Employee ID : ");
		int id = sc.nextInt();
		double salary = 0;
		try {
			salary = emp.getEmployeeGrossSalary(id);
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
		}
		if (salary == 0)
			System.out.println("Provided Employee Id not valid");
		else
			System.out.println("Employee id : " + id + " , Gross Salary : " + salary);

	}

	private static void sortEmployees(Scanner sc) {
		try {
			System.out.println("=================================");
			System.out.println("|   Sorting MENU SELECTION      |");
			System.out.println("=================================");
			System.out.println("| Options:                      |");
			System.out.println("1 SortEmployeesById             |");
			System.out.println("2 SortEmployeeBySalary          |");
			System.out.println("3 SortEmployeeByNameAndSalary   |");
			System.out.println("4 SortEmployeeByDepartment      |");
			System.out.println("5 SortEmployeeByAge             |");
			System.out.println("6 EXIT                          |");
			System.out.println("=================================");
			int something = 0;
			try {
				something = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println(
						"You pressed an invalid Key!! " + "\nTry Again Running the application. We are quiting!!!");
			}
			switch (something) {

			case 1:
				System.out.println(emp.sortEmployees("id"));
				break;
			case 2:
				System.out.println(emp.sortEmployees("salary"));
				break;
			case 3:
				System.out.println(emp.sortEmployees("name and salary"));
				break;
			case 4:
				System.out.println(emp.sortEmployees("departmentNumber"));
				break;
			case 5:
				System.out.println(emp.sortEmployees("age"));
				break;
			case 6:
				break;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void displayEmployeeById(Scanner sc) {
		System.out.println("welcome to Display All Employee Details Module");
		System.out.println("Please provide Employee id in integer number. " + "Example: 100");
		int employeeId = sc.nextInt();
		try {
			System.out.println(emp.getEmployeeById(employeeId));
		} catch (EmployeeNotFoundException | SQLException e) {
			System.out.println(e.getMessage());

		}
	}

	private static void deleteEmployee(Scanner sc) {
		System.out.println("welcome to Delete Employee Details Module");
		System.out.println("Please provide Employee id in integer number. " + "Example: 100");
		int employeeId = sc.nextInt();
		try {
			emp.deleteEmployee(employeeId);
			System.out.println("employee sucessfully deleted");
		} catch (EmployeeNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (InvalidSalaryException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void updateEmployee(Scanner sc) {
		Employee e = null;
		System.out.println("welcome to Update Employee Details Module");
		System.out.println("Please provide Employee id in integer number. " + "Example: 100");
		int employeeId = sc.nextInt();
		System.out.println("Please provide Full Employee Name. " + "Example: John Smith");
		String employeeName = sc.next();
		System.out.println("Please provide Employee Salary in Double Format. " + "Example: 100.00");
		Double salary = sc.nextDouble();
		System.out.println("Please provide Department number. " + "1000");
		int deprtNumber = sc.nextInt();
		System.out.println("Please provide Age. " + "25");
		int age = sc.nextInt();
		e = new Employee(employeeName, salary, deprtNumber, age);
		try {
			System.out.println(emp.updateEmployeeInfo(e));
			System.out.println("Successfully updated");
		} catch (InvalidSalaryException e1) {
			e1.printStackTrace();
		} catch (EmployeeNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	private static void readEmployeeDetails() {
		System.out.println("======================================================");
		System.out.println("All the Employees");
		try {
			System.out.println(emp.getEmployees());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("======================================================");

	}

	private static void createEmployee(Scanner sc) {
		Employee e = null;
		System.out.println("Please provide Full Employee Name. " + "Example: John Smith");
		String employeeName = sc.next();
		System.out.println("Please provide Employee Salary in Double Format. " + "Example: 100.00");
		Double salary = sc.nextDouble();
		System.out.println("Please provide Department number. " + "1000");
		int deprtNumber = sc.nextInt();
		System.out.println("Please provide Age. " + "25");
		int age = sc.nextInt();
		e = new Employee(employeeName, salary, deprtNumber, age);
		// list.add(e);
		try {
			// emp.addEmployee(list);
			emp.addEmployee(e);
			System.out.println("You successfully Added Employee " + employeeName + " to the database");
			System.out.println("======================================================");
		} catch (InvalidSalaryException e1) {
			System.out.println(e1.getMessage());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private static void getHighestSalaryEmployee(Scanner sc) {
		System.out.println("Please provide Employee Salary in Double Format. " + "Example: 100.00");
		Double salary = sc.nextDouble();
		try {
			System.out.println(emp.getHighestSalaryEmployee(salary));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
