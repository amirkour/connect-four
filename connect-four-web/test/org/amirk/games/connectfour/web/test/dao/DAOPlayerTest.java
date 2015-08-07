
package org.amirk.games.connectfour.web.test.dao;

import java.util.List;
import org.amirk.games.connectfour.db.*;
import org.amirk.games.connectfour.entities.*;
import org.amirk.games.connectfour.web.config.AppConfig;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@ActiveProfiles(profiles = {"dev"})
public class DAOPlayerTest {
    
    @Autowired
    protected Logger logger;
    
    @Autowired
    protected DAOPlayer daoPlayer;
    
    @Autowired
    protected DAOUser daoUser;
    
    @Autowired
    protected DAOPlayerColor daoPC;
    
    @Autowired
    protected DAOPlayerType daoPT;
    
    @Test
    public void createPlayer(){
        List<PlayerColor> colors = daoPC.getList();
        List<PlayerType> types = daoPT.getList();
        List<User> users = daoUser.getList();
        
        Player newPlayer = new Player();
        newPlayer.setPlayerColor(colors.get(0));
        newPlayer.setPlayerType(types.get(0));
        newPlayer.setUser(users.get(0));
        
        this.daoPlayer.save(newPlayer);
        assertTrue(newPlayer.getId() > 0);
    }
    
    @Test
    public void listStuff(){
        List<Player> list = this.daoPlayer.getList();
        assertNotNull(list);
        assertTrue(list.size() == 1);
        logger.info(list.get(0).toString());
    }
}
