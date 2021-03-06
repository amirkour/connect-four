
package org.amirk.games.connectfour.web.controllers;

import org.amirk.games.connectfour.db.DAOPlayerColor;
import org.amirk.games.connectfour.entities.PlayerColor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value="/playercolors")
public class PlayerColorController extends BaseController {
    
    @Autowired
    protected DAOPlayerColor dao;
    
    public PlayerColorController(){}
    
    /* use this ctor during testing, to get the dao in */
    public PlayerColorController(DAOPlayerColor dao){ this.dao = dao; }
    
    @RequestMapping(method=RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("playerColorList", dao.getList());
        return "playercolors/list";
    }
    
    @RequestMapping(method=RequestMethod.POST, value = "/save")
    public String save(PlayerColor toSave, RedirectAttributes flash){
        String redirectUrl = "/playercolors";
        
        if(toSave == null){ return flashErrorAndRedirect(redirectUrl, "Cannot save null player color object", flash); }
        if(toSave.getId() > 0){ return flashErrorAndRedirect(redirectUrl, "Cannot save object with positive id - did you mean to update?", flash); }
        if(StringUtils.isBlank(toSave.getName())){ return flashErrorAndRedirect(redirectUrl, "Cannot save without a name", flash); }
        
        try{
            this.dao.save(toSave);
        }catch(Exception e){
            // TODO - log this error
            return flashErrorAndRedirect(redirectUrl, "Encountered the following error while attempting to save: " + e.toString(), flash);
        }
        
        return flashSuccessAndRedirect(redirectUrl, "Successfully created new player color " + toSave.getName() + " (" + toSave.getId() + ")", flash);
    }
    
    @RequestMapping(method=RequestMethod.POST, value = "/update")
    public String update(PlayerColor toUpdate, RedirectAttributes flash){
        
        String redirectUrl = "/playercolors";
        
        if(toUpdate == null){ return flashErrorAndRedirect(redirectUrl, "Cannot update null player color object", flash); }
        if(toUpdate.getId() <= 0){ return flashErrorAndRedirect(redirectUrl, "Cannot update player color without a positive/valid id", flash); }
        if(StringUtils.isBlank(toUpdate.getName())){ return flashErrorAndRedirect(redirectUrl, "Cannot update player color to have a blank name", flash); }
        
        try{
            this.dao.update(toUpdate);
        }catch(Exception e){
            // TODO - log this fella
            return this.flashErrorAndRedirect(redirectUrl, "Encountered the following error: " + e.toString(), flash);
        }
        
        return this.flashSuccessAndRedirect(redirectUrl, "Successfully updated player color " + toUpdate.getName() + " (" + toUpdate.getId() + ")", flash);
    }
    
    @RequestMapping(method=RequestMethod.POST, value = "/delete")
    public String delete(PlayerColor toDelete, RedirectAttributes flash){
        
        String redirectUrl = "/playercolors";
        
        if(toDelete == null){ return flashErrorAndRedirect(redirectUrl, "Cannot delete null player color object", flash); }
        if(toDelete.getId() <= 0){ return flashErrorAndRedirect(redirectUrl, "Cannot delete player color without a positive/valid id", flash); }
        
        try{
            this.dao.delete(toDelete);
        }catch(Exception e){
            // TODO - log this fella
            return this.flashErrorAndRedirect(redirectUrl, "Encountered the following error: " + e.toString(), flash);
        }
        
        return this.flashSuccessAndRedirect(redirectUrl, "Successfully deleted player color " + toDelete.getName() + " (" + toDelete.getId() + ")", flash);
    }
}
