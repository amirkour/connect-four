
package org.amirk.games.connectfour.entities.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.amirk.games.connectfour.entities.*;
import org.junit.*;

public class GameMoveTest{

    /*
     * GameMove objects should sort left-to-right, bottom-to-top, for a 2d board.
     *
     * decreasing <---
     * | o o o o o o
     * | o o o o o o
     * | o o o o o o
     * V
     * decreasing
     *
     * So that the smallest move is the bottom-left of the 2d array, and the largest
     * is the top-right.
     */
    @Test
    public void gameMovesShouldSort(){
        GameMove m1 = new GameMove(0,1);
        GameMove m2 = new GameMove(1,1);

        assertTrue("smaller row always sorts smaller", m1.compareTo(m2) < 0);

        m1.setRow(m2.getRow() + 1);
        assertTrue("larger row always sorts larger", m1.compareTo(m2) > 0);

        m1.setRow(m2.getRow());
        m1.setCol(m2.getCol() + 1);
        assertTrue("when rows are equal, larger col sorts smaller", m1.compareTo(m2) < 0);

        m1.setCol(m2.getCol() - 1);
        assertTrue("when rows are equal, smaller col sorts larger", m1.compareTo(m2) > 0);

        m1.setCol(m2.getCol());
        assertTrue("when row and col are equal, sort is equal", m1.compareTo(m2) == 0);
    }
}
