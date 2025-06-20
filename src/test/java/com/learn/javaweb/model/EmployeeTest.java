package com.learn.javaweb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeTest {
    @Test
    void testGettersAndSetters() {
        Employee employee = new Employee();
        employee.setId(2);
        employee.setFirstName("Jane");
        employee.setLastName("Smith");
        employee.setJob("MANAGER");
        employee.setAge(40);

        Assertions.assertEquals(2, employee.getId());
        Assertions.assertEquals("Jane", employee.getFirstName());
        Assertions.assertEquals("Smith", employee.getLastName());
        Assertions.assertEquals("MANAGER", employee.getJob());
        Assertions.assertEquals(40, employee.getAge());
    }
}
