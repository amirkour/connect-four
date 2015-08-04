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
                                    <td>${player.playerColor.name}</a></td>
                                    <td>${player.playerType.name}</td>
                                    <td>${player.user.email}</td>
                                    <td>
                                        <form method="POST" action="${delete_url}" style="display:inline-block;">
                                            <input type="hidden" value="${player.id}" name="playerId" />
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
                <h2>Create new Player</h2>
                <form method="POST" action="${post_url}" >
                    User ID: <input type="text" name="userId" value="" /><br/>
                    Player Color Id: <input type="text" name="playerColorId" value="" /><br/>
                    Player Type Id: <input type="text" name="playerTypeId" value="" /><br/>
                    
                    <input type="submit" value="Save" />
                </form>
            </div>
            <hr />
            <a href="<c:url value="/" />">home</a>
        </div>
    </body>
</html>
