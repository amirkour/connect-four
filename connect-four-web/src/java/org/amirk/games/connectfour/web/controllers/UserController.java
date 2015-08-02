
package org.amirk.games.connectfour.web.controllers;

import org.amirk.games.connectfour.db.DAOUser;
import org.amirk.games.connectfour.entities.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}
