<%-- 
    Document   : output
    Created on : Feb 29, 2024, 11:32:54 PM
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
        <form action="att" method="POST">
            <input type="hidden" name="id" value="${param.id}">       
            <table border="1px">

                <td>NUMBER</td>
                <td>NAME</td>
                <td>PRESENT/ABSENT</td>
                <td>NOTE</td>
                <td>TIME</td>
                <c:forEach items="${requestScope.atts}" var="a">
                    <tr>
                        <td>
                            ${a.student.id}
                        </td>
                        <td>
                            ${a.student.name}
                        </td>
                        <td>
                            ${a.present}
                            <input type="radio" 
                                   ${!a.present ?"checked=\"checked\"":""}
                                   name="present${a.student.id}" value="no"  /> No
                            <input type="radio" 
                                   ${a.present ?"checked=\"checked\"":""}
                                   name="present${a.student.id}" value ="yes" /> Yes
                        </td>
                        <td><input name="description${a.student.id}" type="text" value="${a.description}"></td>
                        <td>${a.time}</td>
                    </tr>
                </c:forEach>

            </table>
            <input type="submit" value="confirm">
        </form>
    </body>
</html>
