
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringTestConfig.class })
public class ApplicationDAOPlayerTypeTest {
    
    @Autowired
    SessionFactory sf;
    
    @Test
    public void shouldGetList(){
        DAOPlayerType dao = new ApplicationDAOPlayerType(this.sf);
        List<PlayerType> playerTypes = dao.getList();
        assertTrue(playerTypes.size() > 0);
    }
}
