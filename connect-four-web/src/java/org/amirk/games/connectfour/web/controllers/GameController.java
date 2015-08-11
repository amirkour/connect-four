
package org.amirk.games.connectfour.web.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.amirk.games.connectfour.db.*;
import org.amirk.games.connectfour.entities.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping(value="/games")
public class GameController extends BaseController {
    
    @Autowired
    protected DAOGame dao;
    
    @Autowired
    protected DAOPlayer daoPlayer;
    
    @RequestMapping(method =  RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("games", this.dao.getList());
        return "games/list";
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/save")
    public String save(Game toSave, RedirectAttributes flash){
        String redirectUrl = "/games";
        System.out.println("Num in row to win is " + toSave.getNumberInRowToWin());
        
        toSave.setBoardMatrixJson("hi there");
        this.dao.save(toSave);
        return this.flashSuccessAndRedirect(redirectUrl, "Successfully created new game (" + toSave.getId() + ")", flash);
    }
    
    /*
     * Helper that returns true if the given list of players has exactly 2
     * players and those players have different colors associated to them,
     * false otherwise.
     */
    protected Boolean playersHaveDifferentColors(List<Player> players){
        if(players == null || players.size() != 2){ return false; }
        
        Player p1 = players.get(0);
        Player p2 = players.get(1);
        
        PlayerColor p1Color = p1.getPlayerColor();
        PlayerColor p2Color = p2.getPlayerColor();
        
        return p1Color != null &&
               p2Color != null &&
               p1Color.getId() != p2Color.getId();
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/{id}")
    public String edit(@PathVariable("id") long id, RedirectAttributes flash, Model model, HttpServletRequest request){
        Game toEdit = this.dao.getById(id);
        if(toEdit == null){ return this.flashErrorAndRedirect("/games", "Could not find game with id " + id, flash); }
        
        model.addAttribute("game", toEdit);
        
        // build some html that will render an interactable board, but
        // only if this game has 2 players w/ opposing colors
        long[][] board = toEdit.getBoardMatrix();
        System.out.println("The board json is " + toEdit.getBoardMatrixJson());
        List<Player> players = toEdit.getPlayers();
        if(board == null || board.length <= 0){
            model.addAttribute("boardHtml", "<div>Board editing disabled - there's no board available for this game!?</div>");
        }else if(players == null || players.size() != 2 || !playersHaveDifferentColors(players)){
            model.addAttribute("boardHtml", "<div>Board editing disabled - a game must have exactly 2 players w/ opposing colors in order for board editing to be enabled</div>");
        }else{
            StringBuilder boardHtmlBuilder = new StringBuilder();
            boardHtmlBuilder.append("<table>");
            for(int j = 0; j < board[0].length; j++){
                boardHtmlBuilder.append("<tr>");
                for(int i = 0; i < board.length; i++){
                    boardHtmlBuilder.append("<td>");
                    
                    // if the current row/col tuple is occupied, display who occupies it and give the user
                    // the option to clear that spot.
                    // otherwise, give the user the ability to occupy the spot by either player
                    if(toEdit.isOccupied(i,j)){
                        boardHtmlBuilder.append("<div>" + board[i][j] + "</div>");
                        
                        UriComponents uriBuilder = ServletUriComponentsBuilder.fromContextPath(request)
                                                                              .path("/games/" + id + "/board/clear")
                                                                              .build();
                        boardHtmlBuilder.append("<form method=\"POST\" action=\"" + uriBuilder.toUriString() + "\" >");
                        boardHtmlBuilder.append("<input type=\"hidden\" name=\"row\" value=\"" + i + "\" />");
                        boardHtmlBuilder.append("<input type=\"hidden\" name=\"col\" value=\"" + j + "\" />");
                        boardHtmlBuilder.append("<input type=\"submit\" value=\"Clear\" />");
                        boardHtmlBuilder.append("</form>");
                    }else{
                        for(Player p : toEdit.getPlayers()){
                            UriComponents uriBuilder = ServletUriComponentsBuilder.fromContextPath(request)
                                                                              .path("/games/" + id + "/board/add")
                                                                              .build();
                            boardHtmlBuilder.append("<form method=\"POST\" action=\"" + uriBuilder.encode().toString() + "\" >");
                            boardHtmlBuilder.append("<input type=\"hidden\" name=\"playerId\" value=\"" + p.getId() + "\" />");
                            boardHtmlBuilder.append("<input type=\"hidden\" name=\"row\" value=\"" + i + "\" />");
                            boardHtmlBuilder.append("<input type=\"hidden\" name=\"col\" value=\"" + j + "\" />");
                            boardHtmlBuilder.append("<input type=\"submit\" value=\"Player " + p.getId() + "\" />");
                            boardHtmlBuilder.append("</form>");
                        }
                    }
                    
                    boardHtmlBuilder.append("</td>");
                }
                boardHtmlBuilder.append("</tr>");
            }
            boardHtmlBuilder.append("</table>");
            model.addAttribute("boardHtml", boardHtmlBuilder.toString());
        }
        
        return "games/edit";
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/{id}/board/add")
    public String addPieceToBoard(@PathVariable("id") long gameId,
                                  @RequestParam("playerId") long playerId,
                                  @RequestParam("row") int row,
                                  @RequestParam("col") int col,
                                  RedirectAttributes flash){
        Game gameToEdit = this.dao.getById(gameId);
        if(gameToEdit == null){ return this.flashErrorAndRedirect("/games", "Could not find game with id " + gameId, flash); }
        
        long[][] board = gameToEdit.getBoardMatrix();
        if(board == null){ return this.flashErrorAndRedirect("/games/" + gameId, "Cannot add piece to game " + gameId + " - there's no board!", flash); }
        
        Player playerToOccupy = gameToEdit.getPlayer(playerId);
        if(playerToOccupy == null){ return this.flashErrorAndRedirect("/games/" + gameId, "Cannot add piece to game " + gameId + " - player " + playerId + " is not a member of this game!", flash); }
        
        try{
            gameToEdit.occupySpot(playerToOccupy, row, col);
        }catch(Exception e){
            // TODO - log this?
            return this.flashErrorAndRedirect("/games/" + gameId, "Encountered the following error while attempting to occupy " + row + ", " + col + ": " + e.toString(), flash);
        }
        
        this.dao.update(gameToEdit);
        return this.flashSuccessAndRedirect("/games/" + gameId, "Successfully occupied " + row + ", " + col, flash);
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/{id}/board/clear")
    public String clearPieceFromBoard(@PathVariable("id") long gameId,
                                      @RequestParam("row") int row,
                                      @RequestParam("col") int col,
                                      RedirectAttributes flash){
        Game gameToEdit = this.dao.getById(gameId);
        if(gameToEdit == null){ return this.flashErrorAndRedirect("/games", "Could not find game with id " + gameId, flash); }
        
        long[][] board = gameToEdit.getBoardMatrix();
        if(board == null){ return this.flashErrorAndRedirect("/games/" + gameId, "Cannot clear pieces from game " + gameId + " - there's no board!", flash); }
        if(!gameToEdit.isOccupied(row, col)){ return this.flashInfoAndRedirect("/games/" + gameId, row + ", " + col + " is already clear - nothing to do!", flash); }
        
        try{
            gameToEdit.clearSpot(row, col);
        }catch(Exception e){
            // TODO - log this?
            return this.flashErrorAndRedirect("/games/" + gameId, "Encountered the following error while attempting to clear " + row + ", " + col + ": " + e.toString(), flash);
        }
        
        this.dao.update(gameToEdit);
        return this.flashSuccessAndRedirect("/games/" + gameId, "Successfully cleared " + row + ", " + col, flash);
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/{id}/board")
    public String addBoard(@PathVariable("id") long id,
                           @RequestParam("rows") int rows,
                           @RequestParam("cols") int cols,
                           RedirectAttributes flash){
        Game gameToEdit = this.dao.getById(id);
        if(gameToEdit == null){ return this.flashErrorAndRedirect("/games", "Could not find game with id " + id, flash); }
        
        long[][] board = gameToEdit.getBoardMatrix();
        if(rows <= 0 || cols <= 0){ return this.flashErrorAndRedirect("/games/" + id, "The number of rows and cols for a new board must be positive", flash); }
        
        gameToEdit.setBoardMatrix(new long[rows][cols]);
        this.dao.update(gameToEdit);
        return this.flashSuccessAndRedirect("/games/" + id, "Successfully added a board to the game", flash);
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/update")
    public String update(@RequestParam("id") long id,
                         @RequestParam("numberInRowToWin") short numberInRowToWin, 
                         RedirectAttributes flash){
        Game gameToUpdate = this.dao.getById(id);
        if(gameToUpdate == null){ return this.flashErrorAndRedirect("/games", "Could not find game with id " + id, flash); }
        if(numberInRowToWin <= 0){ return this.flashErrorAndRedirect("/games/" + id, "numberInRowToWin cannot be negative", flash); }
        if(numberInRowToWin >= 128){ return this.flashErrorAndRedirect("/games/" + id, "numberInRowToWin cannot exceed 127", flash); }
        
        gameToUpdate.setNumberInRowToWin(numberInRowToWin);
        this.dao.update(gameToUpdate);
        
        return this.flashSuccessAndRedirect("/games/" + gameToUpdate.getId(), "Update Successful", flash);
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/{id}/players")
    public String addPlayer(@PathVariable("id") long id, 
                            @RequestParam("newPlayerId") long playerId,
                            RedirectAttributes flash){
        
        Game gameToEdit = this.dao.getById(id);
        if(gameToEdit == null){ return this.flashErrorAndRedirect("/games", "Could not find game with id " + id, flash); }
        
        Player newPlayer = this.daoPlayer.getById(playerId);
        if(newPlayer == null){ return this.flashErrorAndRedirect("/games/" + id, "Could not find player with id " + playerId, flash); }
        
        List<Player> players = gameToEdit.getPlayers();
        if(players == null){
            players = new ArrayList<Player>();
            gameToEdit.setPlayers(players);
        }
        
        players.add(newPlayer);
        this.dao.update(gameToEdit);
        
        return this.flashSuccessAndRedirect("/games/" + id, "Player " + playerId + " successfully added", flash);
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/delete")
    public String delete(@RequestParam("id") long id, RedirectAttributes flash){
        Game gameToDelete = this.dao.getById(id);
        if(gameToDelete == null){ return this.flashErrorAndRedirect("/games", "Couldn't find game with id " + id, flash); }

        this.dao.delete(gameToDelete);
        return this.flashSuccessAndRedirect("/games", "Successfully deleted game " + id, flash);
    }
}
