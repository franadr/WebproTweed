package Controllers.ManagedBeans;


import Models.JPAentities.ChannelsEntity;
import Models.JPAentities.UsersEntity;
import Services.EJBs.CRUDservice;
import Services.EJBs.ChannelService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean
@ViewScoped
public class ChannelController {
    Logger logger = Logger.getLogger("ChannelController");
    private List<ChannelsEntity> channelsEntitiesList;
    private List<ChannelsEntity> selectedChannels;
    @EJB(name="CRUDserviceImpl")
    private CRUDservice crudService;

    @EJB(name="ChannelServiceImpl")
    private ChannelService channelService;

    @ManagedProperty(value = "#{loginController}")
    LoginController loginController;

    public ChannelController() { }

    //TODO make returned channel list only the list where user is actually registered
    /**
     *
     * @return return the list of channel to which the connected user is registered
     */
    public List<ChannelsEntity> getChannelsEntitiesList() {
        return channelService.findByUser(loginController.getCurrentUser());
    }

    public void setChannelsEntitiesList(List<ChannelsEntity> channelsEntitiesList) {
        this.channelsEntitiesList = channelsEntitiesList;
    }

    public ChannelController(List<ChannelsEntity> selectedChannels) {
        this.selectedChannels = selectedChannels;
    }

    public List<ChannelsEntity> getSelectedChannels() {
        return selectedChannels;
    }

    public void setSelectedChannels(List<ChannelsEntity> selectedChannels) {
        this.selectedChannels = selectedChannels;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
