
package org.amirk.games.connectfour.db;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import org.amirk.games.connectfour.entities.User;
import org.hibernate.criterion.Restrictions;


@Transactional // also needs @EnableTransactionManagement in a config, see ch 12 of the spring docs
@Repository    // also needs a BeanPostProcessor bean, see ch 15 of spring docs
public class DAOUser {
    
    public DAOUser(){}
    
    @Autowired
    protected SessionFactory sessionFactory;
    
    // use this ctor in tests - not gonna autowire the ctor because
    // the property is sufficient
    public DAOUser(SessionFactory sf){ 
        this.sessionFactory = sf; 
    }
    
    public User getById(long id){
        return (User)this.sessionFactory
                         .getCurrentSession()
                         .get(User.class, id);
    }
    
    public User getByEmail(String email){
        return (User)this.sessionFactory
                         .getCurrentSession()
                         .createCriteria(User.class)
                         .add(Restrictions.eq("email", email))
                         .uniqueResult();
    }
    
    public List<User> getList(){
        return this.sessionFactory
                   .getCurrentSession()
                   .createCriteria(User.class)
                   .list();
    }
    
    public User save(User toSave){
        if(toSave == null){ throw new NullPointerException("Cannot save null objects"); }
        if(toSave.getId() > 0){ throw new IllegalArgumentException("Cannot save objects with positive id - did you mean to use 'update' instead?"); }
        
        this.sessionFactory
            .getCurrentSession()
            .save(toSave);
        
        return toSave;
    }
    
    public User update(User toUpdate){
        if(toUpdate == null){ throw new NullPointerException("Cannot update null objects"); }
        if(toUpdate.getId() <= 0){ throw new IllegalArgumentException("Cannot update objects with non-positive id - did you mean to use 'save' instead?"); }
        
        this.sessionFactory
            .getCurrentSession()
            .update(toUpdate);
        
        return toUpdate;
    }
    
    public User delete(User toDelete){
        if(toDelete == null){ throw new NullPointerException("Cannot update null objects"); }
        if(toDelete.getId() <= 0){ throw new IllegalArgumentException("Cannot delete objects with non-positive id"); }
        
        this.sessionFactory
            .getCurrentSession()
            .delete(toDelete);
        
        return toDelete;
    }
}
