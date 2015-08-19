<%@ include file="/WEB-INF/views/header.jsp" %>
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
<%@ include file="/WEB-INF/views/footer.jsp" %>
