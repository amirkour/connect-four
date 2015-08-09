
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value="/games")
public class GameController extends BaseController {
    
    @Autowired
    protected DAOGame dao;
    
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
}
