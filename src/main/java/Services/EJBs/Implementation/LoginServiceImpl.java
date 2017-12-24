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
public class LoginServiceImpl implements LoginService {
    Logger logger = Logger.getLogger("LoginService");
    @PersistenceContext(name = "itPU")
    EntityManager em;

    @Override
    public UsersEntity verifyUser(UsersEntity usersEntity){
        try{
            UsersEntity result = (UsersEntity) em.createQuery("select u from UsersEntity u where u.email = :email")
                    .setParameter("email",usersEntity.getEmail())
                    .getSingleResult();
            if(result.getPassword().equals(usersEntity.getPassword()))
                //TODO verify and store the hash
                return result;
            else
                throw new Exception("Invalid password");


        }catch(Exception e){
            logger.warning(e.getMessage());
            return null;
        }




    }


}
