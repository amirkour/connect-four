<%@ include file="/WEB-INF/views/header.jsp" %>

<c:url var="post_url"  value="/users/save" />
<c:url var="delete_url"   value="/users/delete" />
<c:url var="create_test_user_url" value="/users/save/test" />
<c:url var="create_player_url" value="/players/save" />
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
                            <td><a href="<c:url value="/users/${user.id}" />">${user.email}</a></td>
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
                                <form method="POST" action="${create_player_url}" style="display:inline-block;">
                                    <input type="hidden" name="userId" value="${user.id}" />
                                    <input type="hidden" name="playerColorId" value="1" />
                                    <input type="hidden" name="playerTypeId" value="1" />
                                    <input type="submit" value="Generate Player For This User" />
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
        <p>
            <form style="display:inline-block;" method="POST" action="${create_test_user_url}">
                <input type="submit" value="create random test user" />
            </form>
        </p>
    </div>
    <hr />
    <a href="<c:url value="/" />">home</a>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
