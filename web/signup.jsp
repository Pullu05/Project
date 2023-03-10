<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Hugo 0.104.2">
        <title>Sign in - Employee Management</title>

        <link href="css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

        <meta name="theme-color" content="#712cf9">


        <!-- Custom styles for this template -->
        <link href="css/signin.css" rel="stylesheet">

    </head>

    <body class="text-center">
        <script src="https://code.jquery.com/jquery-3.6.3.js"
                integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" 
                crossorigin="anonymous">

        </script>

        <script>
            function reSubmit() {
                var countryId = document.getElementById("countryId").value;
                submitForm.submit();
            }

//            function fetchContent(selectedId, targetId) {
//                alert("'#" + selectedId.name + "'");
//                if (selectedId.name === "countryId") {
//                     $("#" + selectedId.name).change(function () {
//                             $.ajax({
//                                 url: 'PreProssing',
//                            data: {
//                                countryId: $("#" + selectedId.name).val()
//                            },
//                            success: function (responseText) {
//                                //alert(responseText);
//                                $("#" + targetId.name).html(responseText);
//                              }
//                                        });
//                                });
//                } else if (selectedId.name === "stateId") {
//                     $("#" + selectedId.name).change(function () {
//                             $.ajax({
//                                 url: 'PreProssing',
//                            data: {
//                                stateId: $("#" + selectedId.name).val()
//                            },
//                            success: function (responseText) {
//                                //alert(responseText);
//                                $("#" + targetId.name).html(responseText);
//                              }
//                                        });
//                                });
//                }
//            }
            function fetchContent(selectedId, targetId) {
                $.ajax({
                    url: 'PreProssing',
                    data: {
                        [selectedId]: $("#" + selectedId).val()
                    },
                    success: function (responseText) {
                        $("#" + targetId).html(responseText);
                    }
                });
            }
        </script>


        <main class="form-signin w-100 m-auto">
            <form action="PreProssing" id="submitForm" method="post">
                <img class="mb-4" src="images/flower-logo.jpg" alt="" width="200" height="200">
                <h1 class="h3 mb-3 fw-normal">Please provide below information</h1>

                <c:set var="msg" value="${ErrorMsg1}"/>                   
                <c:if test="${msg!=null}">
                    <div class="alert alert-danger msg_style" role="alert">
                        <c:out value="${msg}"/>
                    </div>
                </c:if>

                <div class="form-floating">
                    <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" name="email" value="${User.getEmail()}" required="required">
                    <label for="floatingInput">Email address</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password" value="${User.getPassword()}" required="required">
                    <label for="floatingPassword">Password</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="firstName" placeholder="first name" name="firstName" value="${User.getFirstName()}"  required="required">
                    <label for="firstName">First Name</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="lastName" placeholder="last name" name="lastName" value="${User.getLastName()}" required="required">
                    <label for="firstName">Last Name</label>
                </div>

                <div class="form-floating">

                    <select name="countryId" class="form-select" id="countryId" onchange="fetchContent('countryId', 'stateId')">
                        <option value="0" >Select Country </option>                       
                        <c:forEach items="${CountryList}" var="country">   
                            <option value="${country.getCountryId()}" <c:if test="${country.getCountryId()==(User.getCountryId())}"> selected </c:if>> 
                                <c:out value="${country.getCountryName()}"/>  
                            </option>
                        </c:forEach>
                    </select>
                    <label for="floatingInput">Country</label>
                </div>

                <div class="form-floating">
                    <select name="stateId" class="form-select" id="stateId" onchange="fetchContent('stateId', 'districtId')"  >
                        <option value="0">Select State </option>      
                    </select>
                    <label for="floatingInput">State</label>
                </div>

                <div class="form-floating">
                    <select name="districtId" class="form-select" id="districtId">
                        <option value="0">Select district </option>
                    </select>
                    <label for="floatingInput">District</label>
                </div>

                <button class="w-100 btn btn-lg btn-primary" type="submit" >Sign Up</button>


                <a href="landingPage.jsp">
                    <button type="button" class="w-100 btn btn-lg btn-warning">Cancel</button>
                </a>
                <p class="mt-5 mb-3 text-muted">&copy; 2017?2022</p>
            </form>
        </main>



    </body>
</html>
