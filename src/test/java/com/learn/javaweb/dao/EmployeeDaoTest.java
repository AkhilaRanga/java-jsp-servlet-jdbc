package com.learn.javaweb.dao;

import com.learn.javaweb.model.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeDaoTest {
    private Connection conn;
    private EmployeeDao employeeDao;

    @BeforeEach
    public void setup() throws Exception {
        conn = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
        Statement stmt = conn.createStatement();
        stmt.execute("DROP TABLE IF EXISTS employees");
        stmt.execute(
                "CREATE TABLE employees ("
                        + "employee_id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "first_name VARCHAR(100),"
                        + "last_name VARCHAR(100),"
                        + "job ENUM('DEVELOPER', 'MANAGER', 'DIRECTOR'),"
                        + "age INT CHECK (age BETWEEN 18 AND 80))");
        stmt.execute(
                "INSERT INTO employees (first_name, last_name, job, age) VALUES "
                        + "('John', 'Smith', 'DEVELOPER', 25), "
                        + "('Jane', 'Doe', 'MANAGER', 36)");
        employeeDao = new EmployeeDao(conn);
    }

    @Test
    public void testGetEmployees() throws Exception {
        List<Employee> results = employeeDao.getEmployees("Jane", "");
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Jane", results.get(0).getFirstName());
    }

    @Test
    public void testAddEmployee() throws Exception {
        int result = employeeDao.addEmployee("Jude", "Brown", "DIRECTOR", 45);
        Assertions.assertEquals(1, result);
    }

    @AfterEach
    public void teardown() throws Exception {
        conn.close();
    }
}
