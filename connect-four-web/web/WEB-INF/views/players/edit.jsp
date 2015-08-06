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
        <div class="container">
            <h1>Editing Player ${player.id}</h1>
            
            <div>User (not editable): ${player.user.email} (${player.user.id})</div>
            <div>
                Color:
                
            </div>
        </div>
    </body>
</html>
