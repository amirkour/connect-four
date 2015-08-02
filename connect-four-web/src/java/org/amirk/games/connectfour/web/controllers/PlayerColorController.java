
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
}
