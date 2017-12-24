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
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Date;
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
                FacesMessages.error("Unable to login","Your account has been disabled");
                return "";
            }

        }
        else{
            FacesMessages.error("Unable to login","Wrong credentials");
            return "";
        }

    }

    public String logout(){

        this.mainBean.getConnectedUsers().remove(currentUser);
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
                .getSession(true)).invalidate();
        return "/login.xhtml?faces-redirect=true";
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
