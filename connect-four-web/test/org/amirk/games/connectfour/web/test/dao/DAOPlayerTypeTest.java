/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.amirk.games.connectfour.web.test.dao;

import org.amirk.games.connectfour.db.DAOPlayerType;
import org.amirk.games.connectfour.entities.PlayerType;
import org.amirk.games.connectfour.web.config.AppConfig;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@ActiveProfiles(profiles = {"dev"})
public class DAOPlayerTypeTest {
    
    @Autowired
    protected Logger logger;
    
    @Autowired
    protected DAOPlayerType daoPT;
    
    @Test
    public void getByNameShouldReturnUniqueResult(){
        PlayerType type = this.daoPT.getByName("pc");
        assertNotNull(type);
        assertEquals(type.getName(), "pc");
    }
}
