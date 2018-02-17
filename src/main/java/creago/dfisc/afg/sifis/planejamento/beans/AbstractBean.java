package creago.dfisc.afg.sifis.planejamento.beans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Tiago Borges Pereira
 */
public class AbstractBean {

    public AbstractBean() {
        super();
    }

    protected void displayErrorMessageToUser(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_ERROR, message, null));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    protected void displayInfoMessageToUser(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_INFO, message, null));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
}
