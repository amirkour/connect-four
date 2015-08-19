
package org.amirk.games.connectfour.agents;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.util.List;
import java.util.LinkedList;
import org.amirk.games.connectfour.entities.*;

/*
 * A sequence of game moves is just a contiguous line of GameMoves in a connect-four
 * game board - the sequence can be horizontal, vertical, or diagonal, and
 * will have properties such as length, and whether or not there are open spots
 * adjacent to the sequence, etc.
 */
public class GameMoveSequence{
    List<GameMove> moveSequence;
    int openAdjacentSpots;

    public GameMoveSequence(){
        this.openAdjacentSpots = 0;
        this.moveSequence = new LinkedList<GameMove>();
    }

    public List<GameMove> getMoveSequence(){return this.moveSequence;}
    public void setMoveSequence(List<GameMove> l){this.moveSequence = l;}

    public int getOpenAdjacentSpots(){return this.openAdjacentSpots;}
    public void setOpenAdjacentSpots(int i){this.openAdjacentSpots = i;}

    public int getLength(){
        return this.moveSequence == null ? 0 : this.moveSequence.size();
    }

    public GameMoveSequence add(GameMove move){
        if(move == null){ return this; }
        if(this.moveSequence == null){ this.moveSequence = new LinkedList<GameMove>(); }
        this.moveSequence.add(move);
        return this;
    }

    public boolean contains(GameMove move){
        if(move == null){ return false; }
        if(this.moveSequence == null || this.moveSequence.size() <= 0){ return false; }
        for(GameMove m : this.moveSequence){
            if(m.equals(move)){ return true; }
        }

        return false;
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder(13,23).append(this.moveSequence)
                                         .append(this.openAdjacentSpots)
                                         .toHashCode();
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (!(obj instanceof GameMoveSequence)) { return false; }

        GameMoveSequence other = (GameMoveSequence) obj;

        return new EqualsBuilder().append(this.moveSequence, other.moveSequence)
                                  .append(this.openAdjacentSpots, other.openAdjacentSpots)
                                  .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("moveSequence", this.moveSequence)
                                        .append("openAdjacentSpots", this.openAdjacentSpots)
                                        .toString();
    }
}