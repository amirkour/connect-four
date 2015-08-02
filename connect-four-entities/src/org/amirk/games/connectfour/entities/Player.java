package org.amirk.games.connectfour.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name="players")
public class Player implements Serializable{
    
    protected long id;
    protected PlayerType playerType;
    protected PlayerColor playerColor;
    protected User user;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getId(){return this.id;}
    public void setId(long i){this.id = i;}
    
    public PlayerType getPlayerType(){ return this.playerType; }
    public void setPlayerType(PlayerType pt){ this.playerType = pt; }
    
    public PlayerColor getPlayerColor() { return playerColor; }
    public void setPlayerColor(PlayerColor playerColor) { this.playerColor = playerColor; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
