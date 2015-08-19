
package org.amirk.games.connectfour.db.app;

import java.util.List;
import org.amirk.games.connectfour.db.DAOPlayerType;
import org.amirk.games.connectfour.entities.PlayerType;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicationDAOPlayerType extends DAOPlayerType {
    
    @Override
    public List<PlayerType> getList(){
        List<PlayerType> list = null;
        Transaction tx = null;
        
        try{
            tx = this.sessionFactory.getCurrentSession().beginTransaction();
            list = super.getList();
            tx.commit();
        }catch(Exception e){
            tx.rollback();
            throw e;
        }
        
        return list;
    }
}
