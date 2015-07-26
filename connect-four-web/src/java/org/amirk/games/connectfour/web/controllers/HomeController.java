package org.amirk.games.connectfour.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.amirk.games.connectfour.db.DAOPlayerType;
import org.springframework.ui.Model;

@Controller
@RequestMapping(value="/")
public class HomeController {
    
    @Autowired
    protected DAOPlayerType dao;
    
    @RequestMapping(method=RequestMethod.GET)
    public String home(Model model){
        
        model.addAttribute("playerTypeList", dao.getList());
        return "home";
    }
}
