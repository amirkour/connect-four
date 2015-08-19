<%@ include file="/WEB-INF/views/header.jsp" %>
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
            <p>No players found!?</p>
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
                                <a href="<c:url value="/players/${player.id}" />">Edit</a>
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
            User ID: <input type="text" name="userId" value="" /> <a href="<c:url value="/users" />">see all users</a><br/>
            Player Color Id: <input type="text" name="playerColorId" value="" /><br/>
            Player Type Id: <input type="text" name="playerTypeId" value="" /><br/>

            <input type="submit" value="Save" />
        </form>
    </div>
    <hr />
    <a href="<c:url value="/" />">home</a>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>
