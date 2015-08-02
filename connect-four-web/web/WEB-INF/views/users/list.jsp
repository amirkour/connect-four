<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="<c:url value="/css/main.css"/>" type="text/css" />
        <title>Users</title>
    </head>
    <body>
        <c:url var="post_url"  value="/users/save" />
        <%--<c:url var="put_url"   value="/users/update" />--%>
        <c:url var="delete_url"   value="/users/delete" />
        <div class="container">
            <h1 style="text-align:center;">Users!</h1>
            
            <c:if test="${error != null}">
                <div style="border:thin solid red;">${error}</div>
            </c:if>
            <c:if test="${info != null}">
                <div style="border:thin solid yellow;">${info}</div>
            </c:if>
            <c:if test="${success != null}">
                <div style="border:thin solid green;">${success}</div>
            </c:if>
            
            <c:choose>
                <c:when test="${userList == null || userList.size() <= 0}">
                    <p>No users found!?</p>
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                            <tr>
                                <th>id</th>
                                <th>email</th>
                                <th>first name</th>
                                <th>last name</th>
                                <th>action(s)</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${userList}">
                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.email}</td>
                                    <td>${user.firstName}</td>
                                    <td>${user.lastName}</td>
                                    <td>
                                        <form method="POST" action="${delete_url}" style="display:inline-block;">
                                            <input type="hidden" name="firstName" value="${user.firstName}" />
                                            <input type="hidden" name="lastName" value="${user.lastName}" />
                                            <input type="hidden" name="email" value="${user.email}" />
                                            <input type="hidden" name="id" value="${user.id}" />
                                            <input type="submit" value="Delete" />
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
            
            <hr/>
            <div>
                <h2>Create new User</h2>
                <form method="POST" action="${post_url}" >
                    Email <input type="text" name="email" value="" /><br/>
                    First Name <input type="text" name="firstName" value="" /><br/>
                    Last Name <input type="text" name="lastName" value=""><br/>
                    <input type="submit" value="Save" />
                </form>
            </div>
            <hr />
            <a href="<c:url value="/" />">home</a>
        </div>
    </body>
</html>
