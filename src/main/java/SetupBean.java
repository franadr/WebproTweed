import Models.JPAentities.UsersEntity;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Singleton
@Startup
public class SetupBean {

    Logger logger = Logger.getLogger("SetupBean");

    @PersistenceContext(name = "itPU")
    EntityManager em;


    @PostConstruct
    public void init(){

        logger.info("   ------Inserting sample into the database-------");


        UsersEntity u1 = new UsersEntity("adrfranci@gmail.com","adri17","standard",false);
        em.merge(u1);

        logger.info("   ------Insertion completed-------");

    }
}
