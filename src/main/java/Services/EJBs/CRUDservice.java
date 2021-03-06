package Services.EJBs;

import java.util.List;

public interface CRUDservice {

    <T> boolean saveUpdateEntity(T e);
    <T> List findAll(Class<T> entity);
    <T> boolean removeOne(T entity, long id);
    <T> T findOne(Class<T> entity, long id);
}
