<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="<c:url value="/css/main.css"/>" type="text/css" />
        <title>connect four web</title>
    </head>
    <body>
        <div class="container">
            <h1 style="text-align:center;">Connect Four Web</h1>
            
            <a href="<c:url value="/playertypes" />">Player Types</a><br/>
            <a href="<c:url value="/playercolors" />">Player Colors</a><br/>
            <a href="<c:url value="/users" />">Users</a><br/>
            <a href="<c:url value="/players" />">Players</a><br/>
        </div>
    </body>
</html>
