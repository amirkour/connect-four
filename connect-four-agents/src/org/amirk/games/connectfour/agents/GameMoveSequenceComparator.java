
package org.amirk.games.connectfour.agents;

import java.util.Comparator;

/*
 * Comparator implementation that sorts GameMoveSequence objects according
 * to their relative, offensive utility.
 *
 * While attempting to calculate the utility of a connect-four board, agents often
 * need to know the longest available sequence of pieces on the board belonging to
 * a given player - the following implementation will sort long GameMoveSequence
 * objects to the front of sorted lists so that agents can have quick access to
 * them - after all, a longer sequence means a player is closer to connecting four!
 *
 * Additionally, if two sequences have the same length, this implementation considers
 * those sequences with MORE open spaces adjacent to them to be SMALLER, so they
 * sort ahead of those sequences with fewer open adjacent spots.  Rightfully so:
 * in a game of connect-four, having more open adjacent spaces typically gives you more
 * attack moves, and so the offensive utility of such a sequence should be considered 
 * higher.
 */
public class GameMoveSequenceComparator implements Comparator<GameMoveSequence>{

    /*
     * See class notes for rationale of sorting.
     */
    public int compare(GameMoveSequence one, GameMoveSequence two){
        if(one == null && two == null){ return 0; }
        if(one == null && two != null){ return 1; }
        if(one != null && two == null){ return -1; }

        int lengthOne = one.getLength();
        int lengthTwo = two.getLength();
        int oneOpenSpaces = one.getOpenAdjacentSpots();
        int twoOpenSpaces = two.getOpenAdjacentSpots();
        if(lengthOne < lengthTwo){
            return 1;
        }else if(lengthOne > lengthTwo){
            return -1;
        }else if(oneOpenSpaces < twoOpenSpaces){
            return 1;
        }else if(oneOpenSpaces > twoOpenSpaces){
            return -1;
        }else{
            return 0;
        }
    }
}
