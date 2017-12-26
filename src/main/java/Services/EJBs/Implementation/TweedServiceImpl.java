package Services.EJBs.Implementation;

import Models.JPAentities.ChannelsEntity;
import Models.JPAentities.TweedsEntity;
import Services.EJBs.TweedService;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.logging.Logger;

@Stateless
@Remote(TweedService.class)
public class TweedServiceImpl implements TweedService {
    Logger logger = Logger.getLogger("TweedServiceImpl");
    @PersistenceContext(name = "itPU")
    EntityManager em;


    @Override
    public List<TweedsEntity> getTweedByChannel(List<ChannelsEntity> c) {

        try{
            List<TweedsEntity> resultList = new ArrayList<>();
            List<TweedsEntity> tweedList = em.createQuery("select t from TweedsEntity t").getResultList();

            c.forEach(channel -> {
                for(TweedsEntity tweed : tweedList) {
                    ChannelsEntity theChannel = em.find(ChannelsEntity.class, channel.getChannel_id());
                    if (tweed.getRelated_channel().equals(theChannel))
                        resultList.add(tweed);
                }
            });

            return resultList;
        }catch(Exception e){
            logger.info(e.getMessage());
            return null;
        }

    }
}
