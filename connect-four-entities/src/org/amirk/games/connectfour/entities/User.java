package org.amirk.games.connectfour.entities;

import java.io.Serializable;
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
@Table(name="users")
public class User implements Serializable {
    
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String email;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId(){return this.id;}
    public void setId(int i){this.id = i;}

    public String getFirstName(){return this.firstName;}
    public void setFirstName(String s){this.firstName = s;}

    public String getLastName(){return this.lastName;}
    public void setLastName(String s){this.lastName = s;}

    @NotNull
    public String getEmail(){return this.email;}
    public void setEmail(String s){this.email = s;}

    @Override
    public int hashCode(){
        return new HashCodeBuilder(3,17).append(this.id)
                                        .append(this.firstName)
                                        .append(this.lastName)
                                        .append(this.email)
                                        .toHashCode();
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (!(obj instanceof User)) { return false; }

        User other = (User) obj;
        return new EqualsBuilder().append(this.id, other.id)
                                  .append(this.firstName, other.firstName)
                                  .append(this.lastName, other.lastName)
                                  .append(this.email, other.email)
                                  .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", this.id)
                                        .append("firstName", this.firstName)
                                        .append("lastName", this.lastName)
                                        .append("email", this.email)
                                        .toString();
    }
}
