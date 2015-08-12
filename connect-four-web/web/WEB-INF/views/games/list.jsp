<%@ include file="/WEB-INF/views/header.jsp" %>
<c:url var="post_url"  value="/games/save" />
<c:url var="put_url"   value="/games/update" />
<c:url var="delete_url"   value="/games/delete" />
<div class="container">
    <h1 style="text-align:center;">Games!?</h1>

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
        <c:when test="${games == null || games.size() <= 0}">
            <p>No games found!?</p>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                    <tr>
                        <th>id</th>
                        <th>players</th>
                        <th>num in row to win</th>
                        <th>winning player id</th>
                        <th>outcome</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="game" items="${games}">
                        <tr>
                            <td>${game.id}</td>
                            <td>${game.players.size()}</td>
                            <td>${game.numberInRowToWin}</td>
                            <td>${game.winningPlayerId}</td>
                            <td>${game.outcomeDescription}</td>
                            <td>
                                <form method="POST" action="${delete_url}" style="display:inline-block;">
                                    <input type="hidden" name="id" value="${game.id}" />
                                    <input type="submit" value="Delete" />
                                </form>
                                <a href="<c:url value="/games/${game.id}" />">edit</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
    <hr/>

    <h2>Create new game</h2>
    <form method="POST" action="${post_url}">
        Number in row to win <input type="text" name="numberInRowToWin" value="" />

        <br/>
        <input type="submit" value="Save" />
    </form>
    <hr/>

    <a href="<c:url value="/" />">home</a>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>
