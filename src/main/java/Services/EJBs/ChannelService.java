package Services.EJBs;

import Models.JPAentities.ChannelsEntity;
import Models.JPAentities.UsersEntity;

import java.util.List;

public interface ChannelService {
    List<ChannelsEntity> findByUser(UsersEntity u);

}
