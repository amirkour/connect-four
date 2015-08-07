package org.amirk.games.connectfour.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name="games")
public class Game implements Serializable{
    
    protected long id;
    protected long winningPlayerId;
    protected String outcomeDescription;
    protected int numberInRowToWin;
    protected String boardMatrixJson;
    protected List<Player> players;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    @Column(name="winning_player_id")
    public long getWinningPlayerId(){ return this.winningPlayerId; }
    public void setWinningPlayerId(long i){ this.winningPlayerId = i; }
    
    @Column(name="outcome")
    public String getOutcomeDescription(){ return this.outcomeDescription; }
    public void setOutcomeDescription(String s){ this.outcomeDescription = s; }
    
    @Column(name="number_in_row_to_win")
    @NotNull
    public int getNumberInRowToWin(){ return this.numberInRowToWin; }
    public void setNumberInRowToWin(int i){ this.numberInRowToWin = i; }
    
    @Column(name="board_matrix_json")
    @NotNull
    public String getBoardMatrixJason(){ return this.boardMatrixJson; }
    public void setBoardMatrixJson(String s){ this.boardMatrixJson = s; }
    
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="game_players",
            joinColumns = @JoinColumn( name="game_id"),
            inverseJoinColumns = @JoinColumn( name="player_id")
    )
    @OrderColumn(name="list_index", nullable = false)
    public List<Player> getPlayers(){ return this.players; }
    public void setPlayers(List<Player> list){ this.players = list; }
    
    @Override
    public int hashCode(){
        return new HashCodeBuilder(11,17).append(this.id)
                                         .append(this.winningPlayerId)
                                         .append(this.outcomeDescription)
                                         .append(this.numberInRowToWin)
                                         .append(this.boardMatrixJson)
                                         .append(this.players)
                                         .toHashCode();
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null){ return false; }
        if(obj == this){ return true; }
        if(!(obj instanceof Game)){ return false; }
        
        Game other = (Game)obj;
        return new EqualsBuilder().append(this.id, other.id)
                                  .append(this.winningPlayerId, other.winningPlayerId)
                                  .append(this.outcomeDescription, other.outcomeDescription)
                                  .append(this.numberInRowToWin, other.numberInRowToWin)
                                  .append(this.boardMatrixJson, other.boardMatrixJson)
                                  .append(this.players, other.players)
                                  .isEquals();
    }
    
    @Override
    public String toString(){
        return new ToStringBuilder(this).append("id", this.id)
                                        .append("winningPlayerId", this.winningPlayerId)
                                        .append("outcomeDescription", this.outcomeDescription)
                                        .append("numberInRowToWing", this.numberInRowToWin)
                                        .append("boardMatrixJson", this.boardMatrixJson)
                                        .append("players", this.players)
                                        .toString();
    }
}
