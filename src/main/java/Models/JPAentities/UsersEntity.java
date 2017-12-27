package Models.JPAentities;


import Services.EJBs.CRUDservice;

import javax.ejb.EJB;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class UsersEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String email;
    private String password;
    private String user_type;
    private Date lastLogin;
    private boolean disabled;

    @ManyToMany(mappedBy = "channelMembers")
    private List<ChannelsEntity> channelsList;

    @OneToMany(mappedBy = "tweed_sender")
    private List<TweedsEntity> sended_tweeds;

    public UsersEntity() {
    }

    public UsersEntity(String email, String password, String user_type, boolean disabled) {
        this.email = email;
        this.password = password;
        this.user_type = user_type;
        this.disabled = disabled;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public List<ChannelsEntity> getChannelsList() {

        return channelsList;
    }

    public void setChannelsList(List<ChannelsEntity> channelsList) {
        this.channelsList = channelsList;
    }

    public List<TweedsEntity> getSended_tweeds() {
        return sended_tweeds;
    }

    public void setSended_tweeds(List<TweedsEntity> sended_tweeds) {
        this.sended_tweeds = sended_tweeds;
    }
}
