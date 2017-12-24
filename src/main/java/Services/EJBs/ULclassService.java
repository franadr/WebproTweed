package Services.EJBs;

import Models.JPAentities.ChannelsEntity;
import Models.JPAentities.ClassesEntity;

import java.util.List;

public interface ULclassService {

    boolean removeClass(ClassesEntity c);
}
