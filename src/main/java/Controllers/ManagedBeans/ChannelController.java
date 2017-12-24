package Controllers.ManagedBeans;


import Models.JPAentities.ChannelsEntity;
import Services.EJBs.CRUDservice;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class ChannelController {

    private List<ChannelsEntity> channelsEntitiesList;
    private List<ChannelsEntity> selectedChannels;
    @EJB(name="CRUDserviceImpl")
    private CRUDservice crudService;

    public ChannelController() {
    }


    //TODO make returned channel list only the list where user is actually registered
    public List<ChannelsEntity> getChannelsEntitiesList() {
        return crudService.findAll(ChannelsEntity.class);
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
}
