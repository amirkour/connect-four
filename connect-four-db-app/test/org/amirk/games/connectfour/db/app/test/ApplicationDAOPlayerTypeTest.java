
package org.amirk.games.connectfour.db.app.test;

import java.util.List;
import org.amirk.games.connectfour.db.DAOPlayerType;
import org.amirk.games.connectfour.db.app.ApplicationDAOPlayerType;
import org.amirk.games.connectfour.entities.PlayerType;
import org.hibernate.SessionFactory;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringTestConfig.class })
public class ApplicationDAOPlayerTypeTest {
    
    protected static Logger logger = LoggerFactory.getLogger(ApplicationDAOPlayerTypeTest.class);
    
    @Autowired
    SessionFactory sf;
    
    @Autowired
    ApplicationDAOPlayerType dao;
    
    @Test
    public void shouldGetList(){
        
        List<PlayerType> playerTypes = this.dao.getList();
        assertNotNull(playerTypes);
        assertTrue("expected player types in test db to be prepopulated", playerTypes.size() > 0);
    }
}
