<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="<c:url value="/css/main.css"/>" type="text/css" />
        <title>Players</title>
    </head>
    <body>
        <c:url var="post_url"  value="/players/save" />
        <c:url var="delete_url"   value="/players/delete" />
        <div class="container">
            <h1 style="text-align:center;">Players!</h1>
            
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
                <c:when test="${playerList == null || playerList.size() <= 0}">
                    <p>No users found!?</p>
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                            <tr>
                                <th>id</th>
                                <th>color</th>
                                <th>type</th>
                                <th>user</th>
                                <th>action(s)</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="player" items="${playerList}">
                                <tr>
                                    <td>${player.id}</td>
                                    <td>${player.playerColor.id}</a></td>
                                    <td>${player.playerType.id}</td>
                                    <td>${player.user.id}</td>
                                    <td>
                                        TODO ACTIONS
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
            
            <hr/>
            <div>
                <h2>Create new Player</h2>
                <form method="POST" action="${post_url}" >
                    TODO FIELDS
                    <input type="submit" value="Save" />
                </form>
            </div>
            <hr />
            <a href="<c:url value="/" />">home</a>
        </div>
    </body>
</html>