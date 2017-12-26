package Controllers.ManagedBeans;


import Models.JPAentities.TweedsEntity;
import Services.EJBs.CRUDservice;
import Services.EJBs.TweedService;
import net.bootsfaces.utils.FacesMessages;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.Date;
import java.util.List;
import java.util.Set;

@ManagedBean
@ViewScoped
public class TweedController {

    @ManagedProperty(value = "#{loginController}")
    private
    LoginController loginController;

    @ManagedProperty(value = "#{channelController}")
    private
    ChannelController channelController;

    @EJB(name="TweedServiceImpl")
    private TweedService tweedService;

    @EJB(name="CRUDserviceImpl")
    private CRUDservice crudService;

    private TweedsEntity newTweed = new TweedsEntity();

    private List<TweedsEntity> tweedList;

    public void postTweed(){
        newTweed.setPublication_date(new Date(System.currentTimeMillis()));
        newTweed.setTweed_sender(loginController.getCurrentUser());
        if(crudService.saveUpdateEntity(newTweed))
            FacesMessages.info("Your tweed has been posted");
        else
            FacesMessages.warning("Tweed not posted, see logs");
    }

    public TweedsEntity getNewTweed() {
        return newTweed;
    }

    public void setNewTweed(TweedsEntity newTweed) {
        this.newTweed = newTweed;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public List<TweedsEntity> getTweedList() {
        tweedList = tweedService.getTweedByChannel(channelController.getChannelsEntitiesList());
        return tweedList;
    }

    public void setTweedList(List<TweedsEntity> tweedList) {
        this.tweedList = tweedList;
    }

    public ChannelController getChannelController() {
        return channelController;
    }

    public void setChannelController(ChannelController channelController) {
        this.channelController = channelController;
    }
}
