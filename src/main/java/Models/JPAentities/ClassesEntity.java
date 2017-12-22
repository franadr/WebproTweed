package Models.JPAentities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClassesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classe_id;

    private String programm;
    private String title;
    private String lecturer;


    public ClassesEntity() {
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
}
