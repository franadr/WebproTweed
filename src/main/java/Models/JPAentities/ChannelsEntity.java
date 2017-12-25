package Models.JPAentities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class ChannelsEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long channel_id;

    private Date creation_date;
    private String channel_name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="channels_user", joinColumns = @JoinColumn(name = "channel_id", referencedColumnName = "channel_id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
    private List<UsersEntity> channelMembers;


    @ManyToMany
    @JoinTable(name="channel_class", joinColumns = @JoinColumn(name = "channel_id", referencedColumnName = "channel_id"), inverseJoinColumns = @JoinColumn(name = "class_id", referencedColumnName = "classe_id"))
    private List<ClassesEntity> concerned_classes = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name="creator_id",referencedColumnName = "user_id")
    private UsersEntity channel_creator;


    public ChannelsEntity() {
    }

    public ChannelsEntity(Date creation_date, String channel_name, List<UsersEntity> channelMembers, List<ClassesEntity> concerned_classes, UsersEntity channel_creator) {
        this.creation_date = creation_date;
        this.channel_name = channel_name;
        this.channelMembers = channelMembers;
        this.concerned_classes = concerned_classes;
        this.channel_creator = channel_creator;
    }

    public Long getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(Long channel_id) {
        this.channel_id = channel_id;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public List<UsersEntity> getChannelMembers() {
        return channelMembers;
    }

    public void setChannelMembers(List<UsersEntity> channelMembers) {
        this.channelMembers = channelMembers;
    }

    public List<ClassesEntity> getConcerned_classes() {
        return concerned_classes;
    }

    public void setConcerned_classes(List<ClassesEntity> concerned_classes) {
        this.concerned_classes = concerned_classes;
    }

    public UsersEntity getChannel_creator() {
        return channel_creator;
    }

    public void setChannel_creator(UsersEntity channel_creator) {
        this.channel_creator = channel_creator;
    }
}
