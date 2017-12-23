package Controllers.ManagedBeans;


import Models.JPAentities.UsersEntity;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

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
