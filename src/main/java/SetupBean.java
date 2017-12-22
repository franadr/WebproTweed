import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Startup
public class SetupBean {

    Logger logger = Logger.getLogger("SetupBean");

    @PersistenceContext(name = "itPU")
    EntityManager em;
    @PostConstruct
    public void init(){

        logger.info("   ------Inserting sample into the database-------");



        logger.info("   ------Insertion completed-------");

    }
}
