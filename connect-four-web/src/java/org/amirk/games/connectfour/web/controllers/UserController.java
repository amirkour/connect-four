
package org.amirk.games.connectfour.web.controllers;

import java.util.List;
import org.amirk.games.connectfour.db.*;
import org.amirk.games.connectfour.entities.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value="/users")
public class UserController extends BaseController{
    
    @Autowired
    protected DAOUser dao;
    
    public UserController(){}
    
    /* use this ctor during testing, to get the dao in */
    public UserController(DAOUser dao){ this.dao = dao; }
    
    @RequestMapping(method=RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("userList", dao.getList());
        return "users/list";
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/{id}")
    public String edit(@PathVariable("id") long id, RedirectAttributes flash, Model model){
        User toEdit = this.dao.getById(id);
        if(toEdit == null){ return this.flashErrorAndRedirect("/users", "Could not find user with id " + id, flash); }
        
        model.addAttribute("user", toEdit);
        return "users/edit";
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/update")
    public String update(User toUpdate, RedirectAttributes flash){
        if(toUpdate == null){ throw new IllegalArgumentException("Cannot update without a user model"); }
        if(toUpdate.getId() <= 0){ return flashErrorAndRedirect("/users", "Cannot update without a positive id", flash); }
        if(StringUtils.isBlank(toUpdate.getEmail())){ return flashErrorAndRedirect("/users/" + toUpdate.getId(), "Cannot update without an email address", flash); }
        
        // TODO validate email format
        try{
            this.dao.update(toUpdate);
        }catch(Exception e){
            // TODO - log error?
            return this.flashErrorAndRedirect("/users/" + toUpdate.getId(), "Failed to update with the following error: " + e.toString(), flash);
        }
        
        return this.flashSuccessAndRedirect("/users/" + toUpdate.getId(), "Update successful", flash);
    }
    
    @RequestMapping(method=RequestMethod.POST, value = "/save")
    public String save(User toSave, RedirectAttributes flash){
        String redirectUrl = "/users";
        
        if(toSave == null){ return flashErrorAndRedirect(redirectUrl, "Cannot save null user object", flash); }
        if(toSave.getId() > 0){ return flashErrorAndRedirect(redirectUrl, "Cannot save object with positive id - did you mean to update?", flash); }
        if(StringUtils.isBlank(toSave.getEmail())){ return flashErrorAndRedirect(redirectUrl, "Cannot save without an email", flash); }
        
        // TODO - validate email format
        
        try{
            this.dao.save(toSave);
        }catch(Exception e){
            // TODO - log this error
            return flashErrorAndRedirect(redirectUrl, "Encountered the following error while attempting to save: " + e.toString(), flash);
        }
        
        return flashSuccessAndRedirect(redirectUrl, "Successfully created new user " + toSave.getEmail() + " (" + toSave.getId() + ")", flash);
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/delete")
    public String delete(User toDelete, RedirectAttributes flash){
        String redirectUrl = "/users";
        
        if(toDelete == null){ return flashErrorAndRedirect(redirectUrl, "Cannot delete null user", flash); }
        if(toDelete.getId() <= 0){ return flashErrorAndRedirect(redirectUrl, "Cannot delete user without positive id", flash); }
        
        try{
            this.dao.delete(toDelete);
        }catch(Exception e){
            // TODO - log error
            return flashErrorAndRedirect(redirectUrl, "Failed to delete with the following error: " + e.toString(), flash);
        }
        
        return flashSuccessAndRedirect(redirectUrl, "Successfully deleted user " + toDelete.getEmail(), flash);
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/save/test")
    public String createTestUser(RedirectAttributes flash){
        User newUser = new User();
        String fname = RandomStringUtils.random(5,true,true);
        String lname = RandomStringUtils.random(5,true,true);
        String domain = RandomStringUtils.random(5,true,false);
        newUser.setEmail(fname + "." + lname + "@" + domain + ".com");
        newUser.setFirstName(fname);
        newUser.setLastName(lname);
        this.dao.save(newUser);
        return this.flashSuccessAndRedirect("/users", "Successfully created new dummy user with id " + newUser.getId(), flash);
    }
}
