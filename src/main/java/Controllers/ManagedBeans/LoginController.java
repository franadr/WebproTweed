package Controllers.ManagedBeans;


import Models.JPAentities.ChannelsEntity;
import Models.JPAentities.UsersEntity;
import Services.EJBs.CRUDservice;
import Services.EJBs.Implementation.LoginServiceImpl;
import Services.EJBs.LoginService;
import net.bootsfaces.utils.FacesMessages;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value="#{mainBean}")
    private MainBean mainBean;
    private static Logger logger = Logger.getLogger("LoginController");

    @EJB(name= "LoginServiceImpl")
    private LoginService loginService;

    @EJB(name="CRUDserviceImpl")
    private CRUDservice crudService;

    private UsersEntity currentUser = new UsersEntity();
    private boolean loggedIn = false;
    private List<ChannelsEntity> allChannels;
    private String newPassword;
    private String oldPassword;
    private String confirmPassword;

    public String validateUser(){
        UsersEntity userResult = loginService.verifyUser(currentUser);

        if(userResult != null) {
            if(!userResult.isDisabled()){
                currentUser = userResult;
                currentUser.setLastLogin(new Date(System.currentTimeMillis()));
                crudService.saveUpdateEntity(currentUser);
                mainBean.getConnectedUsers().add(currentUser);
                loggedIn = true;
                return "/index.xhtml?faces-redirect=true";

            }else{
                FacesMessages.error("Unable to login","Your account has been disabled please contact admin");
                return "";
            }

        }
        else{
            FacesMessages.error("Unable to login","Wrong credentials");
            return "";
        }

    }

    public void changePassword(){
        if(!oldPassword.equals(currentUser.getPassword())){
            FacesMessages.warning("Password not changed, old password don't match");
        }else{
            if(newPassword.equals(confirmPassword)){
                currentUser.setPassword(newPassword);
                if(crudService.saveUpdateEntity(currentUser))
                    FacesMessages.info("Password changed");
                else
                    FacesMessages.warning("Password not changed, see logs");
            }else{
                FacesMessages.info("confirmPassword","","Password do not match");
            }


        }
    }
    public String logout(){

        this.mainBean.getConnectedUsers().remove(currentUser);
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
                .getSession(true)).invalidate();
        return "/login.xhtml?faces-redirect=true";
    }

    public void register(){
        if(loginService.findByEmail(currentUser.getEmail()) != null )
            FacesMessages.warning("User already exists with this email please chose another one");
        else{
            if(crudService.saveUpdateEntity(currentUser))
                FacesMessages.info("You have been registered, please login with your credentials");

        }

    }

    public UsersEntity getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UsersEntity currentUser) {
        this.currentUser = currentUser;
    }


    public MainBean getMainBean() {
        return mainBean;
    }

    public void setMainBean(MainBean mainBean) {
        this.mainBean = mainBean;
    }

    public void isLoggedIn() {
        if(!loggedIn){
            FacesMessages.error("Access denied ");
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getApplication().getNavigationHandler().handleNavigation(fc, null, "login");
        }

    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public List<ChannelsEntity> getAllChannels() {
        return allChannels;
    }

    public void setAllChannels(List<ChannelsEntity> allChannels) {
        this.allChannels = allChannels;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
