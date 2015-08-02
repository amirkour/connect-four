<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="<c:url value="/css/main.css"/>" type="text/css" />
        <title>Edit User</title>
    </head>
    <body>
        <c:url var="put_url"   value="/users/update" />
        <h1>Editing user ${user.email} (${user.id})</h1>
        
        <c:if test="${error != null}">
            <div style="border:thin solid red;">${error}</div>
        </c:if>
        <c:if test="${info != null}">
            <div style="border:thin solid yellow;">${info}</div>
        </c:if>
        <c:if test="${success != null}">
            <div style="border:thin solid green;">${success}</div>
        </c:if>
        
        <form method="POST" action="${put_url}">
            <input type="hidden" name="id" value="${user.id}" />
            <table>
                <tr>
                    <td>ID</td>
                    <td>${user.id}</td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="text" name="email" value="${user.email}" /></td>
                </tr>
                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="firstName" value="${user.firstName}" /></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" name="lastName" value="${user.lastName}" /></td>
                </tr>
            </table>
            <input type="submit" value="Update" />
        </form>
        <hr/>
        <a href="<c:url value="/users" />">back to users</a><br/>
        <a href="<c:url value="/" />">home</a>
    </body>
</html>