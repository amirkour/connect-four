<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Player Types</title>
    </head>
    <body>
        <div style="width:1000px; margin:auto;">
            <h1 style="text-align:center;">Player Types!?</h1>
            
            <c:choose>
                <c:when test="${playerTypeList == null || playerTypeList.size() <= 0}">
                    <p>No player types found!?</p>
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                            <tr>
                                <th>id</th>
                                <th>name</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="type" items="${playerTypeList}">
                                <tr>
                                    <td>${type.id}</td>
                                    <td>${type.name}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
            
            <a href="<c:url value="/" />">home</a>
        </div>
    </body>
</html>
