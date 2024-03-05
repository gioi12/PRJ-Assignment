<%-- 
    Document   : input
    Created on : Feb 27, 2024, 10:21:06 AM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body>       
        <h1>Hello ${sessionScope.account.getDisplayname()}</h1> <br/>
        <form action="input" method="POST">
            <input type="text" name="lid"  id="lid">
                <select id="lectureSelect">
                    <c:forEach items="${requestScope.lecs}" var="lec">
                        <option  value="${lec.lid}">${lec.lname}</option>
                    </c:forEach>
                </select>    
            <br/>
            <input type="submit" value="FIND">
        </form>

    </body>
    <script>
        document.getElementById("lectureSelect").addEventListener("change", function () {
            var selectedOption = this.options[this.selectedIndex];
            document.getElementById("lid").value = selectedOption.value;
        });
    </script>
</html>
