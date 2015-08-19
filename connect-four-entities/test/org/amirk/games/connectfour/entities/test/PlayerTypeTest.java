
package org.amirk.games.connectfour.entities.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.amirk.games.connectfour.entities.*;

public class PlayerTypeTest {
    
    @Test
    public void equalityShouldWork(){
        PlayerType one = new PlayerType();
        one.setId(1);
        one.setName("hi");

        PlayerType two = new PlayerType();
        two.setId(1);
        two.setName("hi");

        assertEquals(one,two);

        two.setId(2);
        assertNotEquals(one,two);

        two.setId(1);
        two.setName("foo");

        assertNotEquals(one,two);

        two.setName(null);
        assertNotEquals(one,two);
    }

    @Test
    public void hashCodeShouldWork(){
        PlayerType one = new PlayerType();
        one.setId(1);
        one.setName("hi");

        PlayerType two = new PlayerType();
        two.setId(1);
        two.setName("hi");

        assertEquals(one.hashCode(),two.hashCode());

        two.setId(2);
        assertNotEquals(one.hashCode(),two.hashCode());

        two.setId(1);
        two.setName("foo");

        assertNotEquals(one.hashCode(), two.hashCode());

        two.setName(null);
        assertNotEquals(one.hashCode(), two.hashCode());
    }
}
