package com.learn.javaweb.controller;

import com.learn.javaweb.dao.EmployeeDao;
import com.learn.javaweb.model.Employee;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/** Servlet implementation class GetEmployeesServlet */
@WebServlet("/getEmployees")
public class GetEmployeesServlet extends HttpServlet {
    private static final long serialVersionUID = 4461370415906755996L;
    private EmployeeDao employeeDao;
    private volatile String initErrorMessage;

    @Override
    public void init() {
        try {
            employeeDao = new EmployeeDao();
        } catch (Exception e) {
            System.out.println("Failed to get employees due to DB connection error: " + e);
            initErrorMessage = "Failed to get employees due to DB connection error";
        }
    }

    // For test use
    public void setEmployeeDao(EmployeeDao dao) {
        this.employeeDao = dao;
        this.initErrorMessage = null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        try {
            if (initErrorMessage == null) {
                ArrayList<Employee> employees;
                employees = this.employeeDao.getEmployees(firstName, lastName);
                request.setAttribute("employees", employees);
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            } else {
                response.getWriter()
                        .println("<p class='text-center text-danger'>" + initErrorMessage + "</p>");
            }
        } catch (Exception e) {
            System.out.println("Exception while searching employees: " + e);
            response.getWriter()
                    .println(
                            "<p class='text-center text-danger'>Error while searching employees.</p>");
        }
    }
}
