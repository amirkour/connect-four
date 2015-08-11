<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="<c:url value="/css/main.css"/>" type="text/css" />
        <title>Edit Game</title>
    </head>
    <body>
        <c:url var="put_url"   value="/games/update" />
        <c:url var="add_player_url" value="/games/${game.id}/players" />
        <c:url var="create_board_url" value="/games/${game.id}/board" />
        <div class="container">
            <h1>Editing game ${game.id}</h1>

            <c:if test="${error != null}">
                <div style="border:thin solid red;">${error}</div>
                <hr/>
            </c:if>
            <c:if test="${info != null}">
                <div style="border:thin solid yellow;">${info}</div>
                <hr/>
            </c:if>
            <c:if test="${success != null}">
                <div style="border:thin solid green;">${success}</div>
                <hr/>
            </c:if>

            <form method="POST" action="${put_url}">
                <input type="hidden" name="id" value="${game.id}" />
                Number in row to win: <input type="text" value="${game.numberInRowToWin}" name="numberInRowToWin" /><br/>
                
                <input type="submit" value="Save" />
            </form>
            <hr/>
            
            <h2>Board stats</h2>
            <div>Outcome: ${game.outcomeDescription}</div>
            <div>Id of winning player: ${game.winningPlayerId}</div>
            ${boardHtml}
            
            <h4>Create/Reset board</h4>
            <p>Use this form to give this game a new board - note that any existing board will be overwritten!</p>
            <form method="POST" action="${create_board_url}">
                <input type="hidden" name="id" value="${game.id}" />
                Number of rows: <input type="text" name="rows" value="" /><br/>
                Number of cols: <input type="text" name="cols" value="" /><br/>
                <input type="submit" value="Create new board" />
            </form>
            <hr/>

            <h2>Players</h2>
            <div style="margin-bottom:20px">
                <c:choose>
                    <c:when test="${game.players == null || game.players.size() <= 0}">
                        no players currently assigned to this game
                    </c:when>
                    <c:otherwise>
                        <table>
                            <thead>
                                <tr>
                                    <th>player id</th>
                                    <th>user</th>
                                    <th>color</th>
                                    <th>type</th>
                                    <th>actions</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="player" items="${game.players}">
                                <tr>
                                    <td>${player.id}</td>
                                    <td><a href="<c:url value="/users/${player.user.id}" />">${player.user.email}</a></td>
                                    <td><a href="<c:url value="/playercolors" />">${player.playerColor.name}</a></td>
                                    <td><a href="<c:url value="/playertypes" />">${player.playerType.name}</a></td>
                                    <td><a href="<c:url value="/players/${player.id}" />">edit this player</a></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
                        
                <br/><a href="<c:url value="/players" />">view all available players (or create a new player)</a>
            </div>
            <form method="POST" action="${add_player_url}">
                ID of player to add to game: <input type="text" value="" name="newPlayerId" /><br/>
                <input type="submit" value="Add Player" />
            </form>
            <hr/>

            <a href="<c:url value="/games" />">back to games</a><br/>
            <a href="<c:url value="/" />">home</a>
        </div>
    </body>
</html>