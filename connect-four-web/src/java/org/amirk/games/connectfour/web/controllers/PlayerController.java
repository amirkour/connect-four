
package org.amirk.games.connectfour.web.controllers;

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
import org.apache.commons.lang3.math.NumberUtils;

@Controller
@RequestMapping(value="/players")
public class PlayerController extends BaseController {
    
    @Autowired
    protected DAOPlayer dao;
    
    @Autowired 
    protected DAOPlayerColor daoPlayerColor;
    
    @Autowired
    protected DAOPlayerType daoPlayerType;
    
    @Autowired
    protected DAOUser daoUser;
    
    public PlayerController(){}
    public PlayerController(DAOPlayer dao){ this.dao = dao; }
    
    @RequestMapping(method=RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("playerList", dao.getList());
        return "players/list";
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/save")
    public String save(@RequestParam(value="userId") String strUserId, 
                       @RequestParam(value="playerColorId") String strPlayerColorId,
                       @RequestParam(value="playerTypeId") String strPlayerTypeId,
                       RedirectAttributes flash){
        
        int userId = NumberUtils.toInt(strUserId);
        if(userId <= 0){ return flashErrorAndRedirect("/players", "Cannot create a new player without a positive user id", flash); }
        
        int playerColorId = NumberUtils.toInt(strPlayerColorId);
        if(playerColorId <= 0){ return flashErrorAndRedirect("/players", "Cannot create a new player without a positive color id", flash); }
        
        int playerTypeId = NumberUtils.toInt(strPlayerTypeId);
        if(playerTypeId <= 0){ return flashErrorAndRedirect("/players", "Cannot create a new player without a positive type id", flash); }
        
        User user = this.daoUser.getById(userId);
        if(user == null){ return flashErrorAndRedirect("/players", "Encountered nonexisting user for id " + userId, flash); }
        
        PlayerColor playerColor = this.daoPlayerColor.getById(playerColorId);
        if(playerColor == null){ return flashErrorAndRedirect("/players", "Encountered nonexisting player color for id " + playerColorId, flash); }
        
        PlayerType playerType = this.daoPlayerType.getById(playerTypeId);
        if(playerType == null){ return flashErrorAndRedirect("/players", "Encountered nonexisting player type for id " + playerTypeId, flash); }
        
        Player newPlayer = new Player();
        newPlayer.setUser(user);
        newPlayer.setPlayerColor(playerColor);
        newPlayer.setPlayerType(playerType);
        try{
            this.dao.save(newPlayer);
        }catch(Exception e){
            // TODO - log error
            return this.flashErrorAndRedirect("/players", "Failed to create new player with the following error: " + e.toString(), flash);
        }
        
        return flashSuccessAndRedirect("/players", "Successfully created new player (" + newPlayer.getId() + ")", flash);
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/delete")
    public String delete(@RequestParam(value="playerId") String strPlayerId, RedirectAttributes flash){
        String redirectUrl = "/players";
        
        int playerId = NumberUtils.toInt(strPlayerId);
        if(playerId <= 0){ return flashErrorAndRedirect(redirectUrl, "Cannot delete players without a positive id", flash); }
        
        Player toDelete = this.dao.getById(playerId);
        if(toDelete == null){ return this.flashErrorAndRedirect(redirectUrl, "Cannot delete nonexisting player with id " + playerId, flash); }
        
        try{
            this.dao.delete(toDelete);
        }catch(Exception e){
            // TODO - log error
            return flashErrorAndRedirect(redirectUrl, "Failed to delete with the following error: " + e.toString(), flash);
        }
        
        return flashSuccessAndRedirect(redirectUrl, "Successfully deleted player " + toDelete.getId(), flash);
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/{playerId}")
    public String edit(@PathVariable("playerId") int playerId, RedirectAttributes flash, Model model){
        Player toEdit = this.dao.getById(playerId);
        if(toEdit == null){ return this.flashErrorAndRedirect("/players", "Could not find player with id " + playerId, flash); }
        
        model.addAttribute("player", toEdit);
        model.addAttribute("colors", this.daoPlayerColor.getList());
        model.addAttribute("types", this.daoPlayerType.getList());
        
        return "players/edit";
    }
}
