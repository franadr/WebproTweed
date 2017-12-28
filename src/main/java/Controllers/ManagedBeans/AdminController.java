package Controllers.ManagedBeans;


import Models.JPAentities.ClassesEntity;
import Services.EJBs.CRUDservice;
import Services.EJBs.ULclassService;
import net.bootsfaces.utils.FacesMessages;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean
@ViewScoped
public class AdminController implements Serializable {

    Logger logger = Logger.getLogger("AdminController");

    @EJB(name="CRUDserviceImpl")
    private CRUDservice crudService;

    @EJB(name="ULclassServiceImpl")
    private ULclassService uLclassService;

    @ManagedProperty(value = "#{channelController}")
    private
    ChannelController channelController;

    private ClassesEntity modULclass;
    private ClassesEntity newULclass = new ClassesEntity();
    private ClassesEntity delUlclass;
    private List<ClassesEntity> classesEntityList;

    public void updateClass(boolean newclass){
        if(newclass){
            if(crudService.saveUpdateEntity(newULclass))
                FacesMessages.info("new class inserted.");
            else
                FacesMessages.error("error during class insertion.");
        }else{
            if(crudService.saveUpdateEntity(modULclass))
                FacesMessages.info("class modified.");
            else
                FacesMessages.error("error during class modification.");
        }
    }

    public void deleteClass(){
        if(uLclassService.removeClass(delUlclass))
            FacesMessages.info(delUlclass.getTitle()+ " class removed.");
        else
            FacesMessages.warning(delUlclass.getTitle()+ " class not removed, see logs");

    }

    public ClassesEntity getNewULclass() {
        return newULclass;
    }

    public void setNewULclass(ClassesEntity newULclass) {
        this.newULclass = newULclass;
    }

    public ClassesEntity getModULclass() {
        return modULclass;
    }

    public void setModULclass(ClassesEntity modULclass) {
        this.modULclass = modULclass;
    }

    public List<ClassesEntity> getClassesEntityList() {
        List<ClassesEntity> list = crudService.findAll(ClassesEntity.class);
        logger.info("Class list size : "+list.size());

        return list;
    }

    public void setClassesEntityList(List<ClassesEntity> classesEntityList) {
        this.classesEntityList = classesEntityList;
    }

    public ClassesEntity getDelUlclass() {
        return delUlclass;
    }

    public void setDelUlclass(ClassesEntity delUlclass) {
        this.delUlclass = delUlclass;
    }

    public ChannelController getChannelController() {
        return channelController;
    }

    public void setChannelController(ChannelController channelController) {
        this.channelController = channelController;
    }
}
