package Models.JPAentities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class ClassesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classe_id;

    private String programm;
    private String title;
    private String lecturer;

    @ManyToMany(mappedBy = "concerned_classes",fetch = FetchType.EAGER)
    private List<ChannelsEntity> channels;


    public ClassesEntity() {
    }

    public ClassesEntity(String programm, String title, String lecturer) {
        this.programm = programm;
        this.title = title;
        this.lecturer = lecturer;
    }

    public Long getClasse_id() {
        return classe_id;
    }

    public void setClasse_id(Long classe_id) {
        this.classe_id = classe_id;
    }

    public String getProgramm() {
        return programm;
    }

    public void setProgramm(String programm) {
        this.programm = programm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public List<ChannelsEntity> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelsEntity> channels) {
        this.channels = channels;
    }
}
