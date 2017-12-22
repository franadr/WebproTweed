package Models.JPAentities;


import javax.persistence.*;
import java.util.Date;

@Entity
public class ResponsesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long response_id;

    private String response_text;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    private UsersEntity response_sender;

    @ManyToOne
    @JoinColumn(name = "tweed_id", referencedColumnName = "tweed_id")
    private TweedsEntity concerning_tweed;


    private Date postDate;

    public ResponsesEntity() {
    }

    public Long getResponse_id() {
        return response_id;
    }

    public void setResponse_id(Long response_id) {
        this.response_id = response_id;
    }

    public String getResponse_text() {
        return response_text;
    }

    public void setResponse_text(String response_text) {
        this.response_text = response_text;
    }

    public UsersEntity getResponse_sender() {
        return response_sender;
    }

    public void setResponse_sender(UsersEntity response_sender) {
        this.response_sender = response_sender;
    }

    public TweedsEntity getConcerning_tweed() {
        return concerning_tweed;
    }

    public void setConcerning_tweed(TweedsEntity concerning_tweed) {
        this.concerning_tweed = concerning_tweed;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
}
