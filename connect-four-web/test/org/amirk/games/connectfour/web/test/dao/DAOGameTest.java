
package org.amirk.games.connectfour.web.test.dao;

import java.util.List;
import org.amirk.games.connectfour.db.*;
import org.amirk.games.connectfour.entities.*;
import org.amirk.games.connectfour.web.config.AppConfig;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@ActiveProfiles(profiles = {"dev"})
public class DAOGameTest {
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
    
    @Autowired
    protected DAOGame daoGame;
    
    @Test
    public void createGame(){
        Game newGame = new Game();
        newGame.setNumberInRowToWin(2);
        newGame.setBoardMatrixJson("hi there");
        this.daoGame.save(newGame);
        
        List<Game> games = this.daoGame.getList();
        assertNotNull(games);
        assertTrue(games.size() > 0);
    }
}
