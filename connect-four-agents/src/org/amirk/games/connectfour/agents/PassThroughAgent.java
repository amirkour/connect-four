
package org.amirk.games.connectfour.agents;

import org.amirk.games.connectfour.entities.Game;
import org.amirk.games.connectfour.entities.GameMove;
import org.amirk.games.connectfour.entities.Player;

/*
 * This agent just passes a row/col tuple through - suitable for human players
 * interacting with a web interface.
 */
public class PassThroughAgent implements ConnectFourGameAgent {
    protected int row;
    protected int col;
    
    private PassThroughAgent(){}
    public PassThroughAgent(int row, int col){ this.row = row; this.col = col; }
    
    @Override
    public GameMove getMoveFor(Game game, Player player) throws Exception{
        return new GameMove(this.row, this.col);
    }
}
