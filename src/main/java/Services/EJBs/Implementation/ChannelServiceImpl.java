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
//        for(ChannelsEntity chan : allChannels)
//           if(chan.getChannelMembers().contains(theUser)){
//               resultList.add(chan);
//           }

        for(ChannelsEntity chan : allChannels)
                for(UsersEntity user : chan.getChannelMembers()){
                    if(user.getUser_id().equals(u.getUser_id()))
                        resultList.add(chan);
            }


       return resultList;
    }

    @Override
    public boolean registerInChannel(UsersEntity u, ChannelsEntity c) {
        try{
            ChannelsEntity theChannel = em.find(ChannelsEntity.class,c.getChannel_id());
            UsersEntity theUser = em.find(UsersEntity.class,u.getUser_id());
            theChannel.getChannelMembers().add(theUser);
            logger.info("new channel list saved 1");
            em.merge(theChannel);
            return true;
        }catch(Exception e){
            logger.warning(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean unregisterFromChannel(UsersEntity u, ChannelsEntity c) {
        try{
            ChannelsEntity theChannel = em.find(ChannelsEntity.class,c.getChannel_id());
            UsersEntity theUser = em.find(UsersEntity.class,u.getUser_id());
            theChannel.getChannelMembers().remove(theUser);
            logger.info("new channel list saved 2");
            em.merge(theChannel);
            return true;
        }catch(Exception e){
            logger.warning(e.getMessage());
            return false;
        }
    }


}
