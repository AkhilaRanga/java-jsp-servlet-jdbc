package com.learn.javaweb.controller;

import com.learn.javaweb.dao.EmployeeDao;
import com.learn.javaweb.util.EmployeeValidator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Servlet implementation class AddEmployeeServlet */
@WebServlet("/addEmployee")
public class AddEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = -1702450857190071820L;
    private EmployeeDao employeeDao;
    private volatile String initErrorMessage;

    @Override
    public void init() {
        try {
            employeeDao = new EmployeeDao();
        } catch (Exception e) {
            System.out.println("Failed to add employee due to DB connection error: " + e);
            initErrorMessage = "Failed to add employee due to DB connection error";
        }
    }

    // For test use
    public void setEmployeeDao(EmployeeDao dao) {
        this.employeeDao = dao;
        this.initErrorMessage = null;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String job = request.getParameter("job");
        String ageString = request.getParameter("age");

        EmployeeValidator validator = new EmployeeValidator();
        validator
                .validateRequired(firstName, "First name")
                .validateCharLength(firstName, 100, "First name")
                .validateRequired(lastName, "Last name")
                .validateCharLength(lastName, 100, "Last name")
                .validateEmployeeJob(job)
                .validateIntInRange(ageString, 18, 80, "Age");

        if (validator.hasErrors()) {
            request.setAttribute("errors", validator.getErrors());
            request.getRequestDispatcher("employee_form.jsp").forward(request, response);
            return;
        }

        if (initErrorMessage == null) {
            int addEmployeeStatus =
                    this.employeeDao.addEmployee(
                            firstName, lastName, job, Integer.parseInt(ageString));

            if (addEmployeeStatus > 0) {
                response.sendRedirect("employee_form.jsp?success=true");
            } else {
                request.setAttribute("success", false);
                RequestDispatcher dispatcher = request.getRequestDispatcher("employee_form.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            response.getWriter()
                    .println("<p class='text-center text-danger'>" + initErrorMessage + "</p>");
        }
    }
}
