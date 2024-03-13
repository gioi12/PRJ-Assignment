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
        <form action="view" method="GET">         
            <input type="text" name="id" value="1">
            <input type="submit" value="VIEW" >
            <table   <table border="1px">          
                <tr>  <td>From : <input type="date" name="from" value="${requestScope.from}" /> <br/>
                        <input type="date" name="to" value="${requestScope.to}"/>   </td>
                        <c:forEach items="${requestScope.days}" var="day" varStatus="loop">
                        <td>
                            ${day}<br/>
                            ${requestScope.dates[loop.index]}
                        </td>
                    </c:forEach>
                </tr>
                <c:forEach items="${requestScope.slots}" var="slot">                 
                    <tr>                      
                        <td>${slot.name}</td>
                        <c:forEach items="${requestScope.dates}" var="d">
                            <td> 
                                <c:forEach items="${requestScope.lessions}" var="les">                               
                                    <c:if test="${(les.date eq d) and (les.slot.id eq slot.id)}">
                                        ${les.group.name} - ${les.group.subject.name}
                                        <a href="att?id=${les.id}"> 
                                            <c:if test="${les.attended}">
                                                Edit
                                            </c:if>
                                            <c:if test="${!les.attended}">
                                                Take
                                            </c:if>
                                        </a>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </c:forEach>
                    </tr> 

                </c:forEach>      
            </table>
            <br/>
        </form>
    </body>

</html>
