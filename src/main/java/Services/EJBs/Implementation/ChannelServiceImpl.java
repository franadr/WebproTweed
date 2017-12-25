package Services.EJBs.Implementation;

import Models.JPAentities.ChannelsEntity;
import Models.JPAentities.UsersEntity;
import Services.EJBs.ChannelService;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Stateless
@Remote(ChannelService.class)
public class ChannelServiceImpl implements ChannelService{

    Logger logger = Logger.getLogger("ChannelServiceImpl");

    @PersistenceContext(name = "itPU")
    private
    EntityManager em;

    @Override
    public List<ChannelsEntity> findByUser(UsersEntity u) {
        List<ChannelsEntity> resultList = new ArrayList<>();
        List<ChannelsEntity> allChannels =  em.createQuery("select c from ChannelsEntity c").getResultList();
        UsersEntity theUser = em.find(UsersEntity.class,u.getUser_id());
        for(ChannelsEntity chan : allChannels)
           if(chan.getChannelMembers().contains(theUser)){
               resultList.add(chan);
           }


       return resultList;
    }
}
