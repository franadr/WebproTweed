package Controllers.ManagedBeans;


import Models.JPAentities.ChannelsEntity;
import Models.JPAentities.ClassesEntity;
import Models.JPAentities.UsersEntity;
import Services.EJBs.CRUDservice;
import Services.EJBs.ChannelService;
import net.bootsfaces.utils.FacesMessages;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

@ManagedBean
@ViewScoped
public class ChannelController implements Serializable {
    Logger logger = Logger.getLogger("ChannelController");
    private List<ChannelsEntity> channelsEntitiesList;
    private List<ChannelsEntity> selectedChannels = new ArrayList<>();
    private List<ChannelsEntity> allchannelsEntitiesList;

    //TODO make the channelSelection collection holding the boolean Value of showing or not
    private HashMap<ChannelsEntity,Boolean> channelSelection = new HashMap<>();

    private List<ClassesEntity> selectedClasses = new ArrayList<>();


    @EJB(name="CRUDserviceImpl")
    private CRUDservice crudService;

    @EJB(name="ChannelServiceImpl")
    private ChannelService channelService;

    @ManagedProperty(value = "#{loginController}")
    private
    LoginController loginController;

    private String[] classSelection ;
    private ChannelsEntity newChannel = new ChannelsEntity();

    public ChannelController() {
//        List<ClassesEntity> classes =  crudService.findAll(ClassesEntity.class);
//        classes.forEach(c -> courseSelection.put(c,false));
    }

    public void unregister(ChannelsEntity c){
            if(channelService.unregisterFromChannel(loginController.getCurrentUser(),c))
                FacesMessages.info("Unregistered from channel");
            else
                FacesMessages.warning("Not unregister, see logs");
    }
    public void register(ChannelsEntity c){
        logger.info("Subscribing to "+c.getChannel_name());
        if(channelService.registerInChannel(loginController.getCurrentUser(),c))
            FacesMessages.info("Registered to channel");
        else
            FacesMessages.warning("Not registered see logs");
    }

    public void saveChannel(){
        newChannel.setChannel_creator(loginController.getCurrentUser());
        newChannel.setConcerned_classes(selectedClasses);
        newChannel.setCreation_date(new Date(System.currentTimeMillis()));
        if(crudService.saveUpdateEntity(newChannel)){
            FacesMessages.info("Channel successfully created");
            logger.info("New channel : "+newChannel.getChannel_name()+" created on "+newChannel.getCreation_date().toString() + " by "+newChannel.getChannel_creator().getEmail());
        }
        else
            FacesMessages.warning("Channel not created, see logs");
    }

    //TODO make returned channel list only the list where user is actually registered
    /**
     * @return return the list of channel to which the connected user is registered
     */
    public List<ChannelsEntity> getChannelsEntitiesList() {

        return channelService.findByUser(loginController.getCurrentUser());
    }

    public List<ChannelsEntity> getAllchannelsEntitiesList() {
        loginController.setAllChannels(crudService.findAll(ChannelsEntity.class));
        return crudService.findAll(ChannelsEntity.class);
    }

    public void setAllchannelsEntitiesList(List<ChannelsEntity> allchannelsEntitiesList) {
        this.allchannelsEntitiesList = allchannelsEntitiesList;
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

    public ChannelsEntity getNewChannel() {
        return newChannel;
    }

    public void setNewChannel(ChannelsEntity newChannel) {
        this.newChannel = newChannel;
    }

    public boolean newContain(List<ChannelsEntity> l ,ChannelsEntity o){
        for(ChannelsEntity c : l)
            if(c.getChannel_id() == o.getChannel_id())
                return true;
        return false;
    }

    public List<ClassesEntity> getSelectedClasses() {
            List<ClassesEntity> result = new ArrayList<>();
            List<ClassesEntity> allClasses = crudService.findAll(ClassesEntity.class);
            if(classSelection == null || classSelection.length < 1){
                return new ArrayList<>();
            }

            else{

                for(String s : classSelection)
                    for (ClassesEntity c : allClasses){
                        if(c.getTitle().equals(s))
                            result.add(c);
                    }

                logger.info("Result size : "+result.size());

                selectedClasses = result;
                return selectedClasses;
        }


    }

    public String[] getClassSelection() {
        return classSelection;
    }

    public void setClassSelection(String[] classSelection) {
        this.classSelection = classSelection;
    }
}
