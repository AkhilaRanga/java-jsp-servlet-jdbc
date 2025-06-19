<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add employee</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
	    <h1 class="text-center mb-4">Add employee</h1>
			<form action="addEmployee" method="post" class="mb-4">
		        <div class="mb-3">
		            <label for="firstName" class="form-label">First name:</label>
		            <input type="text" class="form-control" name="firstName" required>
		        </div>
		        <div class="mb-3">
		            <label for="lastName" class="form-label">Last name:</label>
		            <input type="text" class="form-control" name="lastName" required>
		        </div>
		        <div class="mb-3">
		            <label for="job" class="form-label">Job:</label>
				    <select class="form-control" name="job" required>
				        <option value="DEVELOPER">Developer</option>
				        <option value="MANAGER">Manager</option>
				        <option value="DIRECTOR">Director</option>
				    </select>
		        </div>
		        <div class="mb-3">
		            <label for="age" class="form-label">Age:</label>
		            <input type="number" class="form-control" name="age" min="18" max="80" required>
		        </div>
			    <button type="submit" class="btn btn-outline-primary">Add employee</button>
			    <button type="button" class="btn btn-outline-dark" onclick="window.location.href='index.jsp'">Back to search</button>
			</form>

			<% if ("true".equals(request.getParameter("success"))) { %>
			    <p class="text-center text-success">Employee added successfully</p>
			<% } else if ("true".equals(request.getParameter("error"))) { %>
			    <p class="text-center text-danger">Error adding employee. Please try again</p>
			<% } %>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
