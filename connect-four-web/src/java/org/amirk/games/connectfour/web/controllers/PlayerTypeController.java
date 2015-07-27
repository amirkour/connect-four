/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.amirk.games.connectfour.web.controllers;

import org.amirk.games.connectfour.db.DAOPlayerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/playertypes")
public class PlayerTypeController {
    
    @Autowired
    protected DAOPlayerType dao;
    
    /* use this ctor during testing, to get the dao in */
    public PlayerTypeController(DAOPlayerType dao){ this.dao = dao; }
    
    @RequestMapping(method=RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("playerTypeList", dao.getList());
        return "playertypes/list";
    }
}
