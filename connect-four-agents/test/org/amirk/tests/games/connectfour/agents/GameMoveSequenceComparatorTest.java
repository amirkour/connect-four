
package org.amirk.tests.games.connectfour.agents;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;
import org.amirk.games.connectfour.entities.*;
import org.amirk.games.connectfour.agents.*;
import java.lang.*;
import java.util.*;

public class GameMoveSequenceComparatorTest extends TestParent{

    @Test
    public void nullGameMoveSequenceSortsToTheBack(){
        GameMoveSequenceComparator comparator = new GameMoveSequenceComparator();
        GameMoveSequence one = null;
        GameMoveSequence two = new GameMoveSequence();

        SortedSet<GameMoveSequence> set = new TreeSet<GameMoveSequence>(comparator);
        set.add(one);
        set.add(two);

        assertEquals(two, set.first());
    }

    @Test
    public void shorterGameMoveSequencesSortToTheBack(){
        GameMoveSequenceComparator comparator = new GameMoveSequenceComparator();
        GameMoveSequence one = new GameMoveSequence();
        GameMoveSequence two = new GameMoveSequence();

        one.add(new GameMove());
        two.add(new GameMove());
        two.add(new GameMove());

        SortedSet<GameMoveSequence> set = new TreeSet<GameMoveSequence>(comparator);
        set.add(one);
        set.add(two);

        assertEquals(two, set.first());
    }

    @Test
    public void equalLengthSequencesSortBasedOnOpenAdjacentSpaces(){
        GameMoveSequenceComparator comparator = new GameMoveSequenceComparator();
        GameMoveSequence one = new GameMoveSequence();
        GameMoveSequence two = new GameMoveSequence();

        one.add(new GameMove());
        two.add(new GameMove());
        one.setOpenAdjacentSpots(1);
        two.setOpenAdjacentSpots(2);

        SortedSet<GameMoveSequence> set = new TreeSet<GameMoveSequence>(comparator);
        set.add(one);
        set.add(two);

        assertEquals(two, set.first());
    }
}