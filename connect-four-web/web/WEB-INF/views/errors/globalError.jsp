<%-- 
    Document   : globalError
    Created on : Aug 1, 2015, 12:39:35 PM
    Author     : AmirKouretchian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="<c:url value="/css/main.css"/>" type="text/css" />
        <title>An Error Occurred</title>
        <link rel="stylesheet" href="/css/main.css" type="text/css" />
    </head>
    <body>
        <div class="container">
            <h2>An Error Has Occurred!</h2>
            <h2>${message}</h2>
            <div>${stack}</div>
        </div>
    </body>
</html>
