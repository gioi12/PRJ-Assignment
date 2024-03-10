<%-- 
    Document   : info
    Created on : Mar 10, 2024, 2:21:39 PM
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
        <form action="info" method="GET">         
            <input type="text" name="id" value="1">
            <input type="submit" value="VIEW" ><br/>
            From : <input type="date" name="from" value="${requestScope.from}" />  - 
            <input type="date" name="to" value="${requestScope.to}"/>   
            <table >                                     
                <c:forEach items="${requestScope.lessions}" var="les" varStatus="loop">    
                    <tr>
                        <td>${les.group.name} </td>
                        <td>  ${les.group.subject.name}</td> 
                        <td> ${requestScope.dates[loop.index]}</td>
                        </a>
                    </tr>
                </c:forEach>

            </table>
            <br/>
        </form>
    </body>
</html>
