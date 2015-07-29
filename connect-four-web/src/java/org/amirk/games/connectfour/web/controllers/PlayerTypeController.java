/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.amirk.games.connectfour.web.controllers;

import org.amirk.games.connectfour.db.DAOPlayerType;
import org.amirk.games.connectfour.entities.PlayerType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value="/playertypes")
public class PlayerTypeController {
    
    @Autowired
    protected DAOPlayerType dao;
    
    public enum FlashType {
        
        ERROR("error"),
        INFO("info"),
        SUCCESS("success");
        
        private String key;
        FlashType(String newkey){ this.key = newkey; }
        String getKey(){ return this.key; }
    }
    
    public PlayerTypeController(){}
    
    /* use this ctor during testing, to get the dao in */
    public PlayerTypeController(DAOPlayerType dao){ this.dao = dao; }
    
    @RequestMapping(method=RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("playerTypeList", dao.getList());
        return "playertypes/list";
    }
    
    @RequestMapping(method=RequestMethod.POST, value = "/save")
    public String save(PlayerType toSave, RedirectAttributes flash){
        String redirectUrl = "/playertypes";
        
        if(toSave == null){ return flashErrorAndRedirect(redirectUrl, "Cannot save null player type object", flash); }
        if(toSave.getId() > 0){ return flashErrorAndRedirect(redirectUrl, "Cannot save object with positive id - did you mean to update?", flash); }
        if(StringUtils.isBlank(toSave.getName())){ return flashErrorAndRedirect(redirectUrl, "Cannot save without a name", flash); }
        
        try{
            this.dao.save(toSave);
        }catch(Exception e){
            // TODO - log this error
            return flashErrorAndRedirect(redirectUrl, "Encountered the following error while attempting to save: " + e.toString(), flash);
        }
        
        return flashSuccessAndRedirect(redirectUrl, "Successfully created new player type " + toSave.getName() + "(" + toSave.getId() + ")", flash);
    }
    
    protected String redirect(String url){
        if(StringUtils.isBlank(url)){ throw new IllegalArgumentException("Cannot redirect to null or empty url"); }
        
        return (url.charAt(0) == '/') ? 
                    "redirect:" + url : 
                    "redirect:/" + url;
    }
    
    protected String flashErrorAndRedirect(String url, String error, RedirectAttributes flash){
        return flashFeedbackAndRedirect(FlashType.ERROR, url, error, flash);
    }
    
    protected String flashInfoAndRedirect(String url, String error, RedirectAttributes flash){
        return flashFeedbackAndRedirect(FlashType.INFO, url, error, flash);
    }
    
    protected String flashSuccessAndRedirect(String url, String error, RedirectAttributes flash){
        return flashFeedbackAndRedirect(FlashType.SUCCESS, url, error, flash);
    }
    
    protected String flashFeedbackAndRedirect(FlashType type, String url, String message, RedirectAttributes flash){
        flash.addFlashAttribute(type.getKey(), message);
        return redirect(url);
    }
}
