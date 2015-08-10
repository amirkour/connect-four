
package org.amirk.games.connectfour.db;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import org.amirk.games.connectfour.entities.Game;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;

@Transactional // also needs @EnableTransactionManagement in a config, see ch 12 of the spring docs
@Repository    // also needs a BeanPostProcessor bean, see ch 15 of spring docs
public class DAOGame {
    
    public DAOGame(){}
    
    @Autowired
    protected SessionFactory sessionFactory;
    
    // use this ctor in tests - not gonna autowire the ctor because
    // the property is sufficient
    public DAOGame(SessionFactory sf){ 
        this.sessionFactory = sf; 
    }
    
    public Game getById(long id){
        return (Game)this.sessionFactory
                         .getCurrentSession()
                         .get(Game.class, id);
    }
    
    public List<Game> getList(){
        return this.sessionFactory
                   .getCurrentSession()
                   .createCriteria(Game.class)
                   .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                   .list();
    }
    
    public Game save(Game toSave){
        if(toSave == null){ throw new NullPointerException("Cannot save null objects"); }
        if(toSave.getId() > 0){ throw new IllegalArgumentException("Cannot save objects with positive id - did you mean to use 'update' instead?"); }
        
        this.sessionFactory
            .getCurrentSession()
            .save(toSave);
        
        return toSave;
    }
    
    public Game update(Game toUpdate){
        if(toUpdate == null){ throw new NullPointerException("Cannot update null objects"); }
        if(toUpdate.getId() <= 0){ throw new IllegalArgumentException("Cannot update objects with non-positive id - did you mean to use 'save' instead?"); }
        
        this.sessionFactory
            .getCurrentSession()
            .update(toUpdate);
        
        return toUpdate;
    }
    
    public Game delete(Game toDelete){
        if(toDelete == null){ throw new NullPointerException("Cannot update null objects"); }
        if(toDelete.getId() <= 0){ throw new IllegalArgumentException("Cannot delete objects with non-positive id"); }
        
        this.sessionFactory
            .getCurrentSession()
            .delete(toDelete);
        
        return toDelete;
    }
}
