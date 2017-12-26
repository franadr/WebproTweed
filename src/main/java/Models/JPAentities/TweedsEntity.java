package Models.JPAentities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class TweedsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tweed_id;

    private String content;
    private Date publication_date;

    @ManyToOne
    @JoinColumn(name = "related_channel_id")
    private ChannelsEntity related_channel;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UsersEntity tweed_sender;

    @OneToMany(mappedBy = "concerning_tweed")
    private List<ResponsesEntity> responsesEntities;

    public Long getTweed_id() {
        return tweed_id;
    }

    public TweedsEntity() {
    }

    public TweedsEntity(String content, Date publication_date, ChannelsEntity related_channel, UsersEntity tweed_sender) {
        this.content = content;
        this.publication_date = publication_date;
        this.related_channel = related_channel;
        this.tweed_sender = tweed_sender;
    }

    public void setTweed_id(Long tweed_id) {
        this.tweed_id = tweed_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(Date publication_date) {
        this.publication_date = publication_date;
    }

    public ChannelsEntity getRelated_channel() {
        return related_channel;
    }

    public void setRelated_channel(ChannelsEntity related_channel) {
        this.related_channel = related_channel;
    }

    public UsersEntity getTweed_sender() {
        return tweed_sender;
    }

    public void setTweed_sender(UsersEntity tweed_sender) {
        this.tweed_sender = tweed_sender;
    }

    public List<ResponsesEntity> getResponsesEntities() {
        return responsesEntities;
    }

    public void setResponsesEntities(List<ResponsesEntity> responsesEntities) {
        this.responsesEntities = responsesEntities;
    }
}
