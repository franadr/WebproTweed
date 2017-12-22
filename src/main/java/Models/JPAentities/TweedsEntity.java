package Models.JPAentities;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class TweedsEntity {

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


}
