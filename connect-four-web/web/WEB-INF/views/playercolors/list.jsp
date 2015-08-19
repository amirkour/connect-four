<%@ include file="/WEB-INF/views/header.jsp" %>
<c:url var="post_url"  value="/playercolors/save" />
<c:url var="put_url"   value="/playercolors/update" />
<c:url var="delete_url"   value="/playercolors/delete" />
<div class="container">
    <h1 style="text-align:center;">Player Colors!?</h1>

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
        <c:when test="${playerColorList == null || playerColorList.size() <= 0}">
            <p>No player colors found!?</p>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                    <tr>
                        <th>id/name/actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="color" items="${playerColorList}">
                        <tr>
                            <td>
                                <form method="POST" action="${put_url}" style="display:inline-block;">
                                    <span>(${color.id})</span>
                                    <input type="text" name="name" value="${color.name}" />

                                    <input type="hidden" name="id" value="${color.id}" />
                                    <input type="submit" value="Update" />
                                </form>
                                <form method="POST" action="${delete_url}" style="display:inline-block;">
                                    <input type="hidden" name="name" value="${color.name}" />
                                    <input type="hidden" name="id" value="${color.id}" />
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
        <h2>Create new Player Color</h2>
        <form method="POST" action="${post_url}" >
            Name <input type="text" name="name" value="" />
            <input type="submit" value="Save" />
        </form>
    </div>
    <hr />
    <a href="<c:url value="/" />">home</a>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>