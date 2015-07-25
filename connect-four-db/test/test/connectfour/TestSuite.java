/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.connectfour;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.amirk.games.connectfour.pojos.*;
import org.amirk.games.connectfour.db.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SpringConfig.class})
public class TestSuite {
    
    @Autowired
    protected DAOPlayerType dao;
    
    /*
     * WARNING - totally does not work.  You need to put the unit
     * tests in a separate library, where transactions are managed.
     */
    @Test
    public void doesStuff() throws Exception{
        List<PlayerType> list = this.dao.getList();
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }
}
