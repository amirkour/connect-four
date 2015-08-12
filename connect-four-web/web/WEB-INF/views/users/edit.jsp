<%@ include file="/WEB-INF/views/header.jsp" %>
<c:url var="put_url"   value="/users/update" />
<div class="container">
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
</div>
    
<%@ include file="/WEB-INF/views/footer.jsp" %>
