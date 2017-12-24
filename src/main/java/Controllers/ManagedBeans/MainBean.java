package Controllers.ManagedBeans;


import Models.JPAentities.UsersEntity;

import javax.ejb.Singleton;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@Singleton
@ManagedBean
@ApplicationScoped
public class MainBean {
    private List<UsersEntity> connectedUsers;

    public MainBean(){
        connectedUsers = new ArrayList<>();
    }


    public List<UsersEntity> getConnectedUsers() {
        return connectedUsers;
    }

    public void setConnectedUsers(List<UsersEntity> connectedUsers) {
        this.connectedUsers = connectedUsers;
    }
}
