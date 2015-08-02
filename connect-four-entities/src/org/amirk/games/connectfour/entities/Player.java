package org.amirk.games.connectfour.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
    
    @OneToOne
    @NotNull
    public PlayerType getPlayerType(){ return this.playerType; }
    public void setPlayerType(PlayerType pt){ this.playerType = pt; }
    
    @OneToOne
    @NotNull
    public PlayerColor getPlayerColor() { return playerColor; }
    public void setPlayerColor(PlayerColor playerColor) { this.playerColor = playerColor; }

    @OneToOne
    @NotNull
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    @Override
    public int hashCode(){
        return new HashCodeBuilder(13,17).append(this.id)
                                        .append(this.playerType)
                                        .append(this.playerColor)
                                        .append(this.user)
                                        .toHashCode();
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (!(obj instanceof Player)) { return false; }

        Player other = (Player) obj;
        return new EqualsBuilder().append(this.id, other.id)
                                  .append(this.playerType, other.playerType)
                                  .append(this.playerColor, other.playerColor)
                                  .append(this.user, other.user)
                                  .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", this.id)
                                        .append("playerType", this.playerType)
                                        .append("playerColor", this.playerColor)
                                        .append("user", this.user)
                                        .toString();
    }
}
