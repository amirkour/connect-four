package org.amirk.games.connectfour.agents;

import org.amirk.games.connectfour.entities.*;
import java.util.SortedSet;

/*
 * This implementation of ConnectFourGameAgent just picks the left-most legal
 * move of a connect four game every time.  Suitable for use in scenarios where
 * you just need an agent to do something (and you don't care what that something
 * is.)
 */
public class NPCLeftToRightDummyAgent implements ConnectFourGameAgent{

    /*
     * Simply returns the left-most legal move of the given game, every time!
     */
    @Override
    public GameMove getMoveFor(Game game, Player player) throws Exception{
        if(game == null){ throw new Exception("Cannot generate game moves for a null game"); }

        SortedSet<GameMove> legalMoves = game.getLegalMoves();
        if(legalMoves == null || legalMoves.size() <= 0){ throw new Exception("Encountered null or 0-size list of legal game moves - cannot generate a new game move for this game!"); }

        GameMove leftMostMove = null;
        for(GameMove nextLegalMove : legalMoves){
            if(leftMostMove == null ||
               nextLegalMove.getRow() < leftMostMove.getRow()){
                leftMostMove = nextLegalMove;
            }
        }

        return leftMostMove;
    }
}
