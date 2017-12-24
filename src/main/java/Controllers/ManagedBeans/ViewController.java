package Controllers.ManagedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ViewController {

    boolean modPass = false;
    boolean createChannel = false;
    boolean joinChannel = false;

    public void choseView(boolean modpass,boolean createChannel, boolean joinChannel){
        this.modPass = modpass;
        this.createChannel = createChannel;
        this.joinChannel = joinChannel;
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
}
