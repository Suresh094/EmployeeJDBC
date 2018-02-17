package com.suresh.MavenTest.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import com.suresh.MavenTest.MyDataSourceFactory;
import com.suresh.MavenTest.Entity.Employee;
import com.suresh.MavenTest.Exceptions.EmployeeNotFoundException;
import com.suresh.MavenTest.Exceptions.InvalidSalaryException;
import com.suresh.MavenTest.Interfaces.EmployeeDAOOperations;

public class EmployeeJdbcDAOImpl implements EmployeeDAOOperations {

	private DataSource ds = MyDataSourceFactory.getMySQLDataSource();

	@Override
	public boolean addEmployee(List<Employee> list) throws InvalidSalaryException, SQLException {
		PreparedStatement pstmt = null;
		try (Connection con = ds.getConnection()) {
			for (Employee e : list) {
				con.setAutoCommit(false);
				pstmt = con.prepareStatement(
						"insert into employee (name,salary, departmentNumber, age) values ( ?, ?, ?, ?)");
				pstmt.setString(1, e.getName());
				pstmt.setDouble(2, e.getSalary());
				pstmt.setInt(3, e.getDeptNumber());
				pstmt.setInt(4, e.getAge());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			con.commit();
		}
		return true;
	}

	public boolean deleteEmployee(int empID) throws EmployeeNotFoundException, SQLException {
		if (getEmployeeById(empID) != null) {
			ResultSet rs = null;
			try (Connection con = ds.getConnection()) {
				PreparedStatement pstmt = con.prepareStatement("delete from employee where id=?");
				pstmt.setInt(1, empID);
				pstmt.setInt(1, empID);
				int s = pstmt.executeUpdate();
			}
		}
		return true;

	}

	public Employee updateEmployeeInfo(Employee empl)
			throws EmployeeNotFoundException, InvalidSalaryException, SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = empl.getName();
		try (Connection con = ds.getConnection()) {
			pstmt = con
					.prepareStatement("update employee set name=?, age=?, salary=?,departmentNumber=?  where id = ?");
			pstmt.setString(1, empl.getName());
			pstmt.setInt(2, empl.getAge());
			pstmt.setDouble(3, empl.getSalary());
			pstmt.setInt(4, empl.getDeptNumber());
			pstmt.setInt(5, empl.getId());
			int recordsUpdated = pstmt.executeUpdate();
		}
		return empl;
	}

	public Employee getEmployeeById(int empID) throws EmployeeNotFoundException, SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try (Connection con1 = ds.getConnection()) {
			pstmt = con1.prepareStatement("select * from employee where id = ?");
			pstmt.setInt(1, empID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setSalary(rs.getDouble("salary"));
				e.setDeptNumber(rs.getInt("departmentNumber"));
				e.setAge(rs.getInt("age"));
				return e;
			} else {
				throw new EmployeeNotFoundException("There is no Employee in the database with the given Employee Id");
			}
		}

	}

	public List<Employee> getEmployees() throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		List<Employee> employeesList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from employee");
			while (rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setSalary(rs.getDouble("salary"));
				e.setDeptNumber(rs.getInt("departmentNumber"));
				e.setAge(rs.getInt("age"));
				employeesList.add(e);
			}
		}
		return employeesList;
	}

	public List<Employee> sortEmployees(String str) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Employee> employeesList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			String sqlQuery = null;
			if (str.equalsIgnoreCase("id")) {
				sqlQuery = "select * from employee order by id";
			} else if (str.equalsIgnoreCase("salary")) {
				sqlQuery = "select * from employee order by salary";
			} else if (str.equalsIgnoreCase("name and salary")) {
				sqlQuery = "select * from employee order by name and salary";
			} else if (str.equalsIgnoreCase("departmentNumber")) {
				sqlQuery = "select * from employee order by departmentNumber";
			} else if (str.equalsIgnoreCase("age")) {
				sqlQuery = "select * from employee order by age";
			}
			pstmt = con.prepareStatement(sqlQuery);
			rs = pstmt.executeQuery();
			employeesList.clear();
			while (rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setSalary(rs.getDouble("salary"));
				e.setDeptNumber(rs.getInt("departmentNumber"));
				e.setAge(rs.getInt("age"));
				employeesList.add(e);
			}
		}
		return employeesList;

	}

	@Override
	public List<Employee> getHighestSalaryEmployee(double salary) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Employee> employeesList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			pstmt = con.prepareStatement("select * from employee where salary>?");
			pstmt.setDouble(1, salary);
			rs = pstmt.executeQuery();
			employeesList.clear();
			while (rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setSalary(rs.getDouble("salary"));
				e.setDeptNumber(rs.getInt("departmentNumber"));
				e.setAge(rs.getInt("age"));
				employeesList.add(e);
			}
		}
		return employeesList;
	}

	@Override
	public int getEmployeeSalary(int id) throws SQLException {
		ResultSet resultSet = null;
		int salary = 0;
		try (Connection con = ds.getConnection();
				PreparedStatement statement = con
						.prepareStatement("select salary from imcs.employee where empid = ? ")) {
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				salary = resultSet.getInt("salary");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (resultSet != null)
				resultSet.close();
		}
		return salary;
	}

}
