package com.learn.javaweb.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.learn.javaweb.dao.EmployeeDao;
import com.learn.javaweb.model.Employee;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GetEmployeesServletTest {

    @Test
    public void testGetEmployeesServlet() throws Exception {
        GetEmployeesServlet servlet = new GetEmployeesServlet();
        EmployeeDao employeeDao = mock(EmployeeDao.class);
        servlet.setEmployeeDao(employeeDao);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("John");
        employee.setLastName("Smith");
        employee.setJob("DEVELOPER");
        employee.setAge(20);
        ArrayList<Employee> fakeResults = new ArrayList<>(Arrays.asList(employee));

        when(request.getParameter("firstName")).thenReturn("Jane");
        when(request.getParameter("lastName")).thenReturn("Smith");
        //        when(employeeDao.getEmployees("John", "")).thenReturn(fakeResults);
        when(employeeDao.getEmployees(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(fakeResults);
        when(request.getRequestDispatcher("index.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute("employees", fakeResults);
        verify(request).getRequestDispatcher("index.jsp");
        verify(dispatcher).forward(request, response);
    }
}
