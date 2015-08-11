
package org.amirk.games.connectfour.entities;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/*
 * Just a convenience pojo that models a 'move' in the connect-four game.
 */
public class GameMove implements Comparable<GameMove>{
    protected int row;
    protected int col;

    public GameMove(){}
    public GameMove(int row, int col){ this.row=row; this.col=col; }

    public int getRow(){return this.row;}
    public void setRow(int i){this.row=i;}
    public int getCol(){return this.col;}
    public void setCol(int i){this.col=i;}

    /*
     * GameMove objects should sort left-to-right, bottom-to-top, for a 2d board.
     *
     * decreasing <---
     *     0 1 2 3 4 5
     * | 0 . . . . . .
     * | 1 . . . . . .
     * | 2 . . . . . .
     * |
     * V
     * decreasing
     *
     * So that the smallest move is the bottom-left of the 2d array, and the largest
     * is the top-right.
     */
    @Override
    public int compareTo(GameMove other){
        if(other == null){ return -1; }

        if(this.row < other.row){ return -1; }
        if(this.row > other.row){ return 1; }
        if(this.col > other.col){ return -1; }
        if(this.col < other.col){ return 1; }

        return 0;
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder(13,7).append(this.row)
                                        .append(this.col)
                                        .toHashCode();
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (!(obj instanceof GameMove)) { return false; }

        GameMove other = (GameMove) obj;

        return new EqualsBuilder().append(this.row, other.row)
                                  .append(this.col, other.col)
                                  .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("row", this.row)
                                        .append("col", this.col)
                                        .toString();
    }
}