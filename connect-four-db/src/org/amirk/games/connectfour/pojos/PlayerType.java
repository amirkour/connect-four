/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.amirk.games.connectfour.pojos;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name="player_types")
public class PlayerType implements Serializable {
    
    protected int id;
    protected String name;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder(3,5).append(this.id)
                                       .append(this.name)
                                       .toHashCode();
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (!(obj instanceof PlayerType)) { return false; }

        PlayerType other = (PlayerType) obj;
        return new EqualsBuilder().append(this.id, other.id)
                                  .append(this.name, other.name)
                                  .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", this.id)
                                        .append("name", this.name)
                                        .toString();
    }
}
