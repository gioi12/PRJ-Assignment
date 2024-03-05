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
        <script>
        function showTable() {
            var table = document.getElementById("lessonTable");
            table.style.display = "table";
        }
    </script>
    </head>
    <body>       
        <h1>Hello ${sessionScope.account.getDisplayname()}</h1> <br/>
        <form action="input" method="POST"> 
            <input type="text" name="lid" id="lid" >
             From : <input type="date" name="from" value="${requestScope.from}" /> -
            <input type="date" name="to" value="${requestScope.to}"/>
            <input type="submit" value="VIEW" >
        </form>
            <c:if test="">
        <table id="lessonTable" style="display: none;" <table border="1px">            
             <tr>
                <td></td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td>${d}</td>
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
        </c:if>
    </body>

</html>
