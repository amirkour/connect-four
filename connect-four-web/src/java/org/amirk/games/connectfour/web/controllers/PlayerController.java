
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
@RequestMapping(value="/players")
public class PlayerController extends BaseController {
    
    @Autowired
    protected DAOPlayer dao;
    
    public PlayerController(){}
    public PlayerController(DAOPlayer dao){ this.dao = dao; }
    
    @RequestMapping(method=RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("playerList", dao.getList());
        return "players/list";
    }
}
