package Controllers.ManagedBeans;


import Models.JPAentities.UsersEntity;
import Services.EJBs.Implementation.LoginServiceImpl;
import Services.EJBs.LoginService;
import net.bootsfaces.utils.FacesMessages;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Date;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class LoginController {

    @ManagedProperty(value="#{mainBean}")
    private MainBean mainBean;
    private static Logger logger = Logger.getLogger("LoginController");

    @EJB(name= "LoginServiceImpl")
    private LoginService loginService;

    private UsersEntity currentUser = new UsersEntity();

    public String validateUser(){
        UsersEntity userResult = loginService.verifyUser(currentUser);

        if(userResult != null) {
            if(!userResult.isDisabled()){
                currentUser = userResult;
                currentUser.setLastLogin(new Date(System.currentTimeMillis()));
                loginService.saveUpdateUser(currentUser);
                mainBean.getConnectedUsers().add(currentUser);
                return "/index.xhtml?faces-redirect=true";
            }else{
                FacesMessages.error("loginForm","","Your account has been disabled");
                return "/login.xhtml?faces-redirect=true";
            }

        }
        else{
            logger.warning("userResult is null");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Message","Error Message"));
            logger.warning("userResult is null message sent");
            return "/login.xhtml?faces-redirect=true";
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
}
