
package org.amirk.games.connectfour.web.controllers;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
    @RequestMapping(method=RequestMethod.GET, value="/{id}")
    public String edit(@PathVariable("id") long id, RedirectAttributes flash, Model model){
        Game toEdit = this.dao.getById(id);
        if(toEdit == null){ return this.flashErrorAndRedirect("/games", "Could not find game with id " + id, flash); }
        
        model.addAttribute("game", toEdit);
        return "games/edit";
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/update")
    public String update(@RequestParam("id") long id,
                         @RequestParam("numberInRowToWin") int numberInRowToWin, 
                         RedirectAttributes flash){
        Game gameToUpdate = this.dao.getById(id);
        if(gameToUpdate == null){ return this.flashErrorAndRedirect("/games", "Could not find game with id " + id, flash); }
        
        gameToUpdate.setNumberInRowToWin(numberInRowToWin);
        this.dao.update(gameToUpdate);
        
        return this.flashInfoAndRedirect("/games/" + gameToUpdate.getId(), "Update Successful", flash);
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
}
