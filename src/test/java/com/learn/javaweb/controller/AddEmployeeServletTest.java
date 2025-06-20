package com.learn.javaweb.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.learn.javaweb.dao.EmployeeDao;
import com.learn.javaweb.util.EmployeeValidator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AddEmployeeServletTest {
    @Test
    public void testAddEmployeeServlet() throws Exception {
        AddEmployeeServlet servlet = new AddEmployeeServlet();
        EmployeeDao employeeDao = mock(EmployeeDao.class);
        servlet.setEmployeeDao(employeeDao);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        EmployeeValidator validator = mock(EmployeeValidator.class);

        when(request.getParameter("firstName")).thenReturn("Jane");
        when(request.getParameter("lastName")).thenReturn("Smith");
        when(request.getParameter("job")).thenReturn("DEVELOPER");
        when(request.getParameter("age")).thenReturn("25");

        when(validator.validateRequired(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(validator);
        when(validator.validateCharLength(
                        Mockito.anyString(), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(validator);
        when(validator.validateEmployeeJob(Mockito.anyString())).thenReturn(validator);
        when(validator.validateIntInRange(
                        Mockito.anyString(),
                        Mockito.anyInt(),
                        Mockito.anyInt(),
                        Mockito.anyString()))
                .thenReturn(validator);
        when(validator.hasErrors()).thenReturn(false);

        when(employeeDao.addEmployee(
                        Mockito.anyString(),
                        Mockito.anyString(),
                        Mockito.anyString(),
                        Mockito.anyInt()))
                .thenReturn(1);
        when(request.getRequestDispatcher("/employees_search.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(response).sendRedirect("employee_form.jsp?success=true");
    }
}
