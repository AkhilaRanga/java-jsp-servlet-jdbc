package com.learn.javaweb.controller;

import java.io.IOException;

import com.learn.javaweb.dao.EmployeeDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddEmployeeServlet
 */
@WebServlet("/addEmployee")
public class AddEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = -1702450857190071820L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String firstName = request.getParameter("firstName");
	        String lastName = request.getParameter("lastName");
            String job = request.getParameter("job");
            int age = Integer.parseInt(request.getParameter("age"));
			
			EmployeeDao employeeDao = new EmployeeDao();
			int addEmployeeStatus = employeeDao.addEmployee(firstName, lastName, job, age);

            response.sendRedirect(addEmployeeStatus > 0 ? "employee_form.jsp?success=true" : "employee_form.jsp?error=true");
	}
}
