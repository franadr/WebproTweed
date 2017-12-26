import Models.JPAentities.ChannelsEntity;
import Models.JPAentities.ClassesEntity;
import Models.JPAentities.TweedsEntity;
import Models.JPAentities.UsersEntity;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Logger;

@Singleton
@Startup
public class SetupBean {

    private Logger logger = Logger.getLogger("SetupBean");

    @PersistenceContext(name = "itPU")
    private
    EntityManager em;


    @PostConstruct
    public void init(){

        logger.info("   ------Inserting sample into the database-------");


        logger.info("Inserting some users");
        UsersEntity u1 = em.merge(new UsersEntity("adrfranci@gmail.com","adri17","standard",false));
        UsersEntity u2 = em.merge(new UsersEntity("a@f.com","adri17","admin",false));

        logger.info("inserting some ul classes");
        ClassesEntity c1 = em.merge(new ClassesEntity("BINFO","Web Programming","V. Muller"));
        ClassesEntity c2 = em.merge(new ClassesEntity("BINFO","J2E","V. Muller"));
        ClassesEntity c3 = em.merge(new ClassesEntity("BSEG","Microeconomy I","L. Bertinelli"));
        ClassesEntity c4 = em.merge(new ClassesEntity("BSEG","Macroeconomy I","A. Bourgain"));


        logger.info("adding some channels");
        ChannelsEntity chan1 = em.merge(new ChannelsEntity(new Date(System.currentTimeMillis()),
                "The binfo channel",
                Arrays.asList(u1,u2),
                Arrays.asList(c1,c2),
                u1));

        ChannelsEntity chan2 = em.merge(new ChannelsEntity(new Date(System.currentTimeMillis()),
                "The bseg channel",
                Arrays.asList(u1),
                Arrays.asList(c3,c4),
                u1));

        logger.info("adding some sample tweeds");
        TweedsEntity t1 = em.merge(new TweedsEntity("this is the first tweed",new Date(System.currentTimeMillis()),chan1,u1));
        TweedsEntity t2 = em.merge(new TweedsEntity("This is the second tweed on bseg channel",new Date(System.currentTimeMillis()),chan2,u2));

        logger.info("   ---------------Insertion completed--------------");

    }
}
