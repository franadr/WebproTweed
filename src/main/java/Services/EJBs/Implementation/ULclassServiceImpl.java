package Services.EJBs.Implementation;

import Models.JPAentities.ChannelsEntity;
import Models.JPAentities.ClassesEntity;
import Services.EJBs.ULclassService;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@Remote(ULclassService.class)
@Stateless
public class ULclassServiceImpl implements ULclassService {

    Logger logger = Logger.getLogger("UlclassServiceImpl");
    @PersistenceContext(name = "itPU")
    EntityManager em;


    @Override
    public boolean removeClass(ClassesEntity clas) {
        try{
            ClassesEntity foundClass=em.find(ClassesEntity.class,clas.getClasse_id());
            for(ChannelsEntity chan : foundClass.getChannels()){
                chan.getConcerned_classes().remove(foundClass);
                em.merge(chan);
            }

            em.remove(em.contains(foundClass) ? foundClass : em.merge(foundClass));
            return true;
        }catch(Exception e){
            logger.info(e.getMessage());
            return false;
        }
    }
}
