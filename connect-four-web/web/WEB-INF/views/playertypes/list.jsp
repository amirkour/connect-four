<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Player Types</title>
    </head>
    <body>
        <c:url var="post_url"  value="/playertypes/save" />
        <c:url var="put_url"   value="/playertypes/update" />
        <c:url var="delete_url"   value="/playertypes/delete" />
        <div style="width:1000px; margin:auto;">
            <h1 style="text-align:center;">Player Types!?</h1>
            
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
                <c:when test="${playerTypeList == null || playerTypeList.size() <= 0}">
                    <p>No player types found!?</p>
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                            <tr>
                                <th>id/name/actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="type" items="${playerTypeList}">
                                <tr>
                                    <td>
                                        <form method="POST" action="${put_url}" style="display:inline-block;">
                                            <span>(${type.id})</span>
                                            <input type="text" name="name" value="${type.name}" />
                                            
                                            <input type="hidden" name="id" value="${type.id}" />
                                            <input type="submit" value="Update" />
                                        </form>
                                        <form method="POST" action="${delete_url}" style="display:inline-block;">
                                            <input type="hidden" name="name" value="${type.name}" />
                                            <input type="hidden" name="id" value="${type.id}" />
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
                <h2>Create new PlayerType</h2>
                <form method="POST" action="${post_url}" >
                    Name <input type="text" name="name" value="" />
                    <input type="submit" value="Save" />
                </form>
            </div>
            <hr />
            <a href="<c:url value="/" />">home</a>
        </div>
    </body>
</html>
