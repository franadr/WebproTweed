package Services.EJBs.Implementation;


import Services.EJBs.CRUDservice;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@Stateless
@Remote(CRUDservice.class)
public class CRUDserviceImpl implements CRUDservice {
    Logger logger = Logger.getLogger("CRUDserviceImpl");
    @PersistenceContext(name = "itPU")
    EntityManager em;

    @Override
    public <T> boolean saveUpdateEntity(T entity) {
        try{
            em.merge(entity);
            return true;
        }catch (Exception e){
            logger.warning(e.getMessage());
            return false;
        }
    }

    @Override
    public <T> List findAll(Class<T> entity) {
        try{
            return em.createQuery("select entity from "+entity.getName()+" entity").getResultList();
        }catch (Exception e){
            logger.warning(e.getMessage());
            return null;
        }
    }

    @Override
    public <T> boolean removeOne(T entity, long id) {
        return false;
    }

    @Override
    public <T> T findOne(Class<T> entity, long id) {
        try{
           return  em.find(entity,id);
        }catch(Exception e){
            logger.info(e.getMessage());
            return null;
        }
    }
}

