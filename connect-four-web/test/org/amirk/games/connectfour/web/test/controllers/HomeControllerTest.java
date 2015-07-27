package org.amirk.games.connectfour.web.test.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.amirk.games.connectfour.web.controllers.HomeController;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.test.web.servlet.MockMvc;


public class HomeControllerTest {
    
    @Test
    public void homeControllerView() throws Exception{
        HomeController ctrl = new HomeController();
        MockMvc mvc = standaloneSetup(ctrl).build();
        
        mvc.perform(get("/"))
           .andExpect(view().name("home"));
    }
}
