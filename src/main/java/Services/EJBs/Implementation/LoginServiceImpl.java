package Services.EJBs.Implementation;


import Models.JPAentities.UsersEntity;
import Services.EJBs.LoginService;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Stateless
@Remote(LoginService.class)
public class LoginServiceImpl {
    Logger logger = Logger.getLogger("LoginService");
    @PersistenceContext(name = "itPU")
    EntityManager em;

    public UsersEntity verifyUser(UsersEntity usersEntity){
        UsersEntity result = (UsersEntity) em.createQuery("select u from UsersEntity u where u.email = :email").setParameter("email",usersEntity.getEmail()).getSingleResult();
        //TODO verify and store the hash
        if(result != null){
            if(result.getPassword().equals(usersEntity.getPassword()))
                return result;
        }
        return null;
    }

    public boolean saveUpdateUser(UsersEntity usersEntity){
        try{
            em.merge(usersEntity);
            return true;
        }catch (Exception e){
            logger.warning(e.getMessage());
            return false;
        }
    }

}
