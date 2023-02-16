<%-- 
    Document   : add_employee
    Created on : 26-Jan-2023, 8:05:18 pm
    Author     : RISHAV DUTTA
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.exavalu.services.RoleService"%>
<%@page import="com.exavalu.services.DepartmentService"%>
<%@page import="com.exavalu.models.Role"%>
<%@page import="com.exavalu.models.Department"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.exavalu.models.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Employee</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/menu_css.css" rel="stylesheet">
        <link href="css/sign-in.css" rel="stylesheet">
        <style>
            .form-floating{
                margin-bottom: 10px;
            }
        </style>

    </head>

    <body class="text-center">
        <script>
            // Get the form and submit button elements
            var form = document.getElementById("myForm");
            var submitBtn = document.getElementById("submitBtn");
            var responseDiv = document.getElementById("responseDiv");

            // Add a click event listener to the submit button
            submitBtn.addEventListener("click", function (event) {
                document.getElementById("form_body").style.display = 'none';
                // Prevent the default form submission behavior
                event.preventDefault();

                // Create a new XMLHttpRequest object
                var xhr = new XMLHttpRequest();

                // Set the request method and URL
                xhr.open("POST", "AddEmployee", true);

                // Set the request headers (if needed)
//                xhr.setRequestHeader("Content-Type", "application/json");

                // Set the callback function to handle the response
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        // Do something with the response (if needed)
                        console.log(xhr.responseText);
                        responseDiv.innerHTML = xhr.responseText;
//                        document.body.innerHTML = xhr.responseText;
                    }
                };

                // Get the form data and send the request
                var formData = new FormData(form);
                xhr.send(formData);
            });
        </script>
        <div id="form_body">
            <jsp:include page="menu.jsp"></jsp:include>
                <main class="form-signin w-25 m-auto">

                    <form action="AddEmployee" method="POST" id="myForm">
                        <h1 class="h3 mb-3 fw-normal">Please Add Employee Details</h1>

                    <c:set var="msg" value="${ErrorMsg2}"/>
                    <c:if test="${msg!=null}">
                        <div class="alert alert-danger" role="alert">
                            <c:out value="${msg}"/>
                        </div>
                    </c:if>


                    <div class="form-floating">
                        <input type="text" pattern="[A-Za-z]{1,}" title="Only Alphabets are allowed" class="form-control" id="floatingInput" placeholder="firstName" name="firstName" required="required" >
                        <label for="floatingInput">First Name</label>
                    </div>

                    <div class="form-floating">
                        <input type="text" pattern="[A-Za-z]{1,}" title="Only Alphabets are allowed" class="form-control" id="floatingInput" placeholder="lastName" name="lastName" required="required">
                        <label for="floatingInput">Last Name</label>
                    </div>

                    <div class="form-floating">
                        <input type="text" class="form-control" id="floatingInput" placeholder="phone" name="phoneNumber" required="required">
                        <label for="floatingInput">Phone</label>
                    </div>

                    <div class="form-floating">
                        <!--                    <input type="text" class="form-control" id="floatingInput" placeholder="gender" name="gender" required="required">-->

                        <select name="gender" class="form-select" id="gender">
                            <option value= >Select Gender</option>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                        </select>
                        <label for="floatingInput">Gender</label>
                    </div>

                    <div class="form-floating">
                        <input type="number" class="form-control" id="floatingInput" placeholder="age" name="age" required="required">
                        <label for="floatingInput">Age</label>
                    </div>

                    <div class="form-floating">                    

                        <select name="departmentId" class="form-select" id="departmentId">
                            <option value="0" required="required">Select Dept</option>
                            <c:forEach items="${DepartmentService.getAllDepartment()}" var="dept">
                                <option value="${dept.getDepartmentId()}"> <c:out value="${dept.getDepartmentName()}"/>  </option>
                            </c:forEach>
                        </select>
                        <label for="floatingInput">Department Name</label>
                    </div>

                    <div class="form-floating">                  
                        <select name="roleId" class="form-select" id="roleId">
                            <option value="0" required="required">Select Role</option>
                            <c:forEach items="${RoleService.getAllRole()}" var="role">
                                <option value="${role.getRoleId()}"> <c:out value="${role.getRoleName()}"/>  </option>
                            </c:forEach>
                        </select>
                        <label for="floatingInput">Role Name</label>
                    </div>

                    <div class="form-floating">
                        <input type="number" class="form-control" id="floatingInput" placeholder="salary" name="basicSalary" required="required">
                        <label for="floatingInput">Salary</label>
                    </div>

                    <div class="form-floating">
                        <input type="number" class="form-control" id="floatingInput" placeholder="specialAllowance" name="specialAllowance" required="required">
                        <label for="floatingInput">Special Allowance</label>
                    </div>
                    <div class="form-floating">
                        <input type="number" class="form-control" id="floatingInput" placeholder="carAllowance" name="carAllowance" required="required">
                        <label for="floatingInput">Car Allowance</label>
                    </div>

                    <button class="w-100 btn btn-lg btn-primary" type="submit" id="submitBtn">ADD DATA</button>
                </form>
            </main>
        </div>

        <div id="responseDiv">

        </div>
    </body>
</html>
