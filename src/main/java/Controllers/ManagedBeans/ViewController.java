package Controllers.ManagedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class ViewController implements Serializable {

    boolean modPass = false;
    boolean createChannel = false;
    boolean joinChannel = false;
    boolean addUlClass = false;
    boolean modULClass = false;
    boolean delULClass = false;
    boolean disableUser =false;

    public void choseViewProfile(boolean modpass, boolean createChannel, boolean joinChannel){
        this.modPass = modpass;
        this.createChannel = createChannel;
        this.joinChannel = joinChannel;
    }

    public void choseViewAdmin(boolean addUlClass,boolean modULClass, boolean delULClass,boolean disableUser){
        this.addUlClass=addUlClass;
        this.modULClass=modULClass;
        this.delULClass=delULClass;
        this.disableUser=disableUser;
    }

    public boolean isModPass() {
        return modPass;
    }

    public void setModPass(boolean modPass) {
        this.modPass = modPass;
    }

    public boolean isCreateChannel() {
        return createChannel;
    }

    public void setCreateChannel(boolean createChannel) {
        this.createChannel = createChannel;
    }

    public boolean isJoinChannel() {
        return joinChannel;
    }

    public void setJoinChannel(boolean joinChannel) {
        this.joinChannel = joinChannel;
    }

    public boolean isAddUlClass() {
        return addUlClass;
    }

    public void setAddUlClass(boolean addUlClass) {
        this.addUlClass = addUlClass;
    }

    public boolean isModULClass() {
        return modULClass;
    }

    public void setModULClass(boolean modULClass) {
        this.modULClass = modULClass;
    }

    public boolean isDelULClass() {
        return delULClass;
    }

    public void setDelULClass(boolean delULClass) {
        this.delULClass = delULClass;
    }

    public boolean isDisableUser() {
        return disableUser;
    }

    public void setDisableUser(boolean disableUser) {
        this.disableUser = disableUser;
    }
}
