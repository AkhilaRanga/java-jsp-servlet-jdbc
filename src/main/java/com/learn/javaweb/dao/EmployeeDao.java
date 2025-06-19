package com.learn.javaweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.learn.javaweb.model.Employee;
import com.learn.javaweb.util.DBUtils;

public class EmployeeDao {
    Connection conn = null;

    public void connect() throws Exception {
        try {
        	conn = DBUtils.getConnection();
        } catch (Exception e) {
            System.out.println("Exception connecting to MySQL DB: " + e);
            throw e;
        }
    }
    
	public ArrayList<Employee> getEmployees(String firstName, String lastName) throws Exception {
		ArrayList<Employee> employees = new ArrayList<>();
		connect();
		
        String sql;
        if ((firstName == null || firstName.isEmpty()) && (lastName == null || lastName.isEmpty())) {
            sql = "SELECT * FROM employees";
        } else {
            sql = "SELECT * FROM employees WHERE 1=0";
            if (firstName != null && !firstName.isEmpty()) {
                sql += " OR first_name LIKE ?";
            }
            if (lastName != null && !lastName.isEmpty()) {
                sql += " OR last_name LIKE ?";
            }
        }
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        int paramIndex = 1;
        if (firstName != null && !firstName.isEmpty()) {
            stmt.setString(paramIndex++, "%" + firstName.trim() + "%");
        }
        if (lastName != null && !lastName.isEmpty()) {
            stmt.setString(paramIndex++, "%" + lastName.trim() + "%");
        }
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Employee employee = new Employee();
            employee.setId(rs.getInt("employee_id"));
            employee.setFirstName(rs.getString("first_name"));
            employee.setLastName(rs.getString("last_name"));
            employee.setJob(rs.getString("job"));
            employee.setAge(rs.getInt("age"));
            employees.add(employee);
        }
        return employees;
	}

    public int addEmployee(String firstName, String lastName, String job, int age) {
        try {
    		connect();
            String sql = "INSERT INTO employees (first_name, last_name, job, age) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, job);
            stmt.setInt(4, age);
            return stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
}
