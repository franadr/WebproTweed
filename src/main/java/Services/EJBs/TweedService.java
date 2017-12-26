package Services.EJBs;

import Models.JPAentities.ChannelsEntity;
import Models.JPAentities.TweedsEntity;

import java.util.List;
import java.util.Set;

public interface TweedService {

    List<TweedsEntity> getTweedByChannel(List<ChannelsEntity> c);
}
