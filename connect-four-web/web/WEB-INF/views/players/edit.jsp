<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="<c:url value="/css/main.css"/>" type="text/css" />
        <title>Player ${player.id}</title>
    </head>
    <body>
        <c:url var="update_url"  value="/players/update" />
        <div class="container">
            <h1>Editing Player ${player.id}</h1>
            
            <c:if test="${error != null}">
                <div style="border:thin solid red;">${error}</div>
            </c:if>
            <c:if test="${info != null}">
                <div style="border:thin solid yellow;">${info}</div>
            </c:if>
            <c:if test="${success != null}">
                <div style="border:thin solid green;">${success}</div>
            </c:if>
            
            <div>User (not editable): ${player.user.email} (${player.user.id})</div>
            <hr/>
            <form method="POST" action="${update_url}">
                <input type="hidden" name="playerId" value="${player.id}" />
                <div>
                    <c:choose>
                        <c:when test="${colors == null || colors.size() <= 0}">
                            <p>No colors found!?  Cannot edit colors for this user!!!</p>
                        </c:when>
                        <c:otherwise>
                            <select name="newColorId">
                                <c:forEach var="color" items="${colors}">
                                    <c:choose>
                                        <c:when test="${color.id == player.playerColor.id}">
                                            <option selected="selected" value="${color.id}">${color.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${color.id}">${color.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div>
                    <c:choose>
                        <c:when test="${types == null || types.size() <= 0}">
                            <p>No player types found!?  Cannot edit player type for this user!!!</p>
                        </c:when>
                        <c:otherwise>
                            <select name="newTypeId">
                                <c:forEach var="type" items="${types}">
                                    <c:choose>
                                        <c:when test="${type.id == player.playerType.id}">
                                            <option selected="selected" value="${type.id}">${type.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${type.id}">${type.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </c:otherwise>
                    </c:choose>
                </div>
                <input type="submit" value="Update" />
            </form>
            <hr/>
            <a href="<c:url value="/players" />">Back to Players</a><br/>
            <a href="<c:url value="/" />">Home</a>
        </div>
    </body>
</html>
