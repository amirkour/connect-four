package org.amirk.games.connectfour.agents;

import org.amirk.games.connectfour.entities.*;

/*
 * Implementations of ConnectFourGameAgent know how to evaluate
 * a Game and return moves for a given player.  These implementations
 * may employ various AI algorithms to determine which move to 
 * return (such as hill-climbing, minimax, simulated annealing, etc.)
 */
public interface ConnectFourGameAgent{
    public GameMove getMoveFor(Game game, Player player) throws Exception;    
}