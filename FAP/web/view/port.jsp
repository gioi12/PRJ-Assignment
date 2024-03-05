<%-- 
    Document   : port
    Created on : Feb 27, 2024, 10:20:50 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello ${requestScope.account.getDisplayname()}</h1> <br/>
        1.<a href="http://localhost:9999/FAP/attendence/input">check attendence</a>
    </body>
</html>
