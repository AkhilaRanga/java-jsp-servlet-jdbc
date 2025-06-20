<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.learn.javaweb.model.Employee" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
	    <h1 class="text-center mb-4">Employee management</h1>
		<form action="getEmployees" method="get" class="mb-4">
	        <div class="mb-3">
	            <label for="firstName" class="form-label">Search by first name:</label>
	            <input type="text" class="form-control" name="firstName" value="<%= request.getParameter("firstName") != null ? request.getParameter("firstName") : "" %>">
	        </div>
	        <div class="mb-3">
	            <label for="lastName" class="form-label">Search by last name:</label>
	            <input type="text" class="form-control" name="lastName" value="<%= request.getParameter("lastName") != null ? request.getParameter("lastName") : "" %>">
	        </div>
		    <button type="submit" class="btn btn-outline-dark">Search employees</button>
			<button type="button" class="btn btn-outline-primary" onclick="window.location.href='employee_form.jsp'">Add new employee</button>
		</form>

		<% 
		    List<Employee> employees = (List<Employee>) request.getAttribute("employees");
		    if (employees != null && !employees.isEmpty()) {
		%>
		    <h3 class="text-center h3">Results:</h3>
		    <table class="table table-striped">
		    	<thead>
		        	<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Job</th><th>Age</th></tr>
		        </thead>
		        <tbody>
			        <% for (Employee employee : employees) { %>
			            <tr>
			                <td><%= employee.getId() %></td>
			                <td><%= employee.getFirstName() %></td>
			                <td><%= employee.getLastName() %></td>
			                <td><%= employee.getJob() %></td>
			                <td><%= employee.getAge() %></td>
			            </tr>
			        <% } %>
		        </tbody>
		    </table>
		<% } else if (employees != null) { %>
		    <h3 class="text-center h3">Results:</h3>
		    <p class="text-center">No matching employees found</p>
		<% } %>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
