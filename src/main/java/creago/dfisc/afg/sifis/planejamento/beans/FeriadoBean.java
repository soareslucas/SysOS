package creago.dfisc.afg.sifis.planejamento.beans;

import creago.dfisc.afg.sifis.planejamento.entities.Feriado;
import creago.dfisc.afg.sifis.planejamento.facade.FeriadoFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Tiago Borges Pereira
 */
@SessionScoped
@ManagedBean
public class FeriadoBean extends AbstractBean implements Serializable {

    private Feriado feriado;
    private List<Feriado> feriados;
    private Feriado selectedFeriado;
    private List<Feriado> filteredFeriados;

    private FeriadoFacade feriadoFacade;

    public FeriadoFacade getFeriadoFacade() {
        if (feriadoFacade == null) {
            feriadoFacade = new FeriadoFacade();
        }
        return feriadoFacade;
    }

    public String create() {
        try {
            getFeriadoFacade().create(feriado);
            displayInfoMessageToUser("Feriado cadastrado com sucesso!");
            loadFeriados();
            resetFeriado();
            return "feriados-list";
        } catch (Exception e) {
            displayErrorMessageToUser("Erro ao cadastrar o novo feriado!");
            return "feriados-new";
        }
    }

    public String newFeriado() {
        return "feriados-new";
    }

    public List<Feriado> findAll() {
        loadFeriados();
        return feriados;
    }

    public String remove() {
        try {
            getFeriadoFacade().delete(selectedFeriado);
            displayInfoMessageToUser("Feriado excluído com sucesso!");
            loadFeriados();
            resetFeriado();
        } catch (Exception e) {
            displayErrorMessageToUser("Erro ao excluir feriado!");
            e.printStackTrace();
        }
        return "feriados-list";
    }

    public void onRowEdit(RowEditEvent event) {
        Feriado feriadoAlterado = (Feriado) event.getObject();
        getFeriadoFacade().update(feriadoAlterado);
        displayInfoMessageToUser("Feriado atualizado com sucesso!");
        loadFeriados();
        resetFeriado();
    }

    public void onCancel(RowEditEvent event) {
        displayInfoMessageToUser("Atualização cancelada com sucesso!");
    }

    public Feriado getFeriado() {
        if (feriado == null) {
            feriado = new Feriado();
        }
        return feriado;
    }

    public void setFeriado(Feriado feriado) {
        this.feriado = feriado;
    }

    public List<Feriado> getFeriados() {
        feriados = findAll();

        return feriados;
    }

    public void setFeriados(List<Feriado> feriados) {
        this.feriados = feriados;
    }

    public Feriado getSelectedFeriado() {
        return selectedFeriado;
    }

    public void setSelectedFeriado(Feriado selectedFeriado) {
        this.selectedFeriado = selectedFeriado;
    }

    public List<Feriado> getFilteredFeriados() {
        return filteredFeriados;
    }

    public void setFilteredFeriados(List<Feriado> filteredFeriados) {
        this.filteredFeriados = filteredFeriados;
    }

    public FeriadoFacade getFeriadoService() {
        return feriadoFacade;
    }

    public void setFeriadoService(FeriadoFacade feriadoService) {
        this.feriadoFacade = feriadoService;
    }

    private void loadFeriados() {
        feriados = getFeriadoFacade().listAll();
    }

    private void resetFeriado() {
        feriado = new Feriado();
    }
}
