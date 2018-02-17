package creago.dfisc.afg.sifis.planejamento.beans;

import creago.dfisc.afg.sifis.planejamento.entities.Cidade;
import creago.dfisc.afg.sifis.planejamento.entities.Fiscal;
import creago.dfisc.afg.sifis.planejamento.entities.Inspetoria;
import creago.dfisc.afg.sifis.planejamento.entities.Rota;
import creago.dfisc.afg.sifis.planejamento.facade.InspetoriaFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Tiago Borges Pereira
 */
@SessionScoped
@ManagedBean
public class InspetoriaBean extends AbstractBean implements Serializable {

    private Inspetoria inspetoria;
    private List<Inspetoria> inspetorias;
    private Inspetoria selectedInspetoria;
    private List<Inspetoria> filteredInspetorias;
    
    private Rota selectedRota;
    
    private List<Cidade> filteredCidades;
    private List<Fiscal> filteredFiscais;
    private List<Rota> filteredRotas;

    private InspetoriaFacade inspetoriaFacade;

    public InspetoriaFacade getInspetoriaFacade() {
        if (inspetoriaFacade == null) {
            inspetoriaFacade = new InspetoriaFacade();
        }
        return inspetoriaFacade;
    }

    public String create() {
        try {
            inspetoria.setNome(inspetoria.getNome().toUpperCase());
            getInspetoriaFacade().create(inspetoria);
            displayInfoMessageToUser("Inspetoria cadastrada com sucesso!");
            loadInspetoria();
            resetInspetoria();
            return "inspetorias-list";
        } catch (Exception e) {
            displayErrorMessageToUser("Erro ao cadastrar o nova inspetoria!");
            return "inspetorias-new";
        }
    }

    public String newInspetoria() {
        return "inspetorias-new";
    }

    public List<Inspetoria> findAll() {
        loadInspetoria();
        return inspetorias;
    }

    public String remove() {
        try {
            getInspetoriaFacade().delete(selectedInspetoria);
            displayInfoMessageToUser("Inspetoria excluída com sucesso!");
            loadInspetoria();
            resetInspetoria();
        } catch (Exception e) {
            displayErrorMessageToUser("Erro ao excluir inspetoria!");
            e.printStackTrace();
        }
        return "inspetorias-list";
    }

    public void onRowEdit(RowEditEvent event) {
        Inspetoria inspetoriaAlterada = (Inspetoria) event.getObject();
        inspetoriaAlterada.setNome(inspetoriaAlterada.getNome().toUpperCase());
        getInspetoriaFacade().update(inspetoriaAlterada);
        displayInfoMessageToUser("Inspetoria atualizada com sucesso!");
        loadInspetoria();
        resetInspetoria();
    }

    public void onCancel(RowEditEvent event) {
        displayInfoMessageToUser("Atualização cancelada com sucesso!");
    }

    // GETTERS AND SETTERS
    public Inspetoria getInspetoria() {
        if (inspetoria == null) {
            inspetoria = new Inspetoria();
        }
        return inspetoria;
    }

    public void setInspetoria(Inspetoria inspetoria) {
        this.inspetoria = inspetoria;
    }

    public List<Inspetoria> getInspetorias() {
        inspetorias = findAll();

        return inspetorias;
    }

    public void setInspetorias(List<Inspetoria> inspetorias) {
        this.inspetorias = inspetorias;
    }

    public Inspetoria getSelectedInspetoria() {
        return selectedInspetoria;
    }

    public void setSelectedInspetoria(Inspetoria selectedInspetoria) {
        this.selectedInspetoria = selectedInspetoria;
    }

    public List<Inspetoria> getFilteredInspetorias() {
        return filteredInspetorias;
    }

    public void setFilteredInspetorias(List<Inspetoria> filteredInspetorias) {
        this.filteredInspetorias = filteredInspetorias;
    }

    public List<Cidade> getFilteredCidades() {
        return filteredCidades;
    }

    public void setFilteredCidades(List<Cidade> filteredCidades) {
        this.filteredCidades = filteredCidades;
    }

    public List<Fiscal> getFilteredFiscais() {
        return filteredFiscais;
    }

    public void setFilteredFiscais(List<Fiscal> filteredFiscais) {
        this.filteredFiscais = filteredFiscais;
    }

    public List<Rota> getFilteredRotas() {
        return filteredRotas;
    }

    public void setFilteredRotas(List<Rota> filteredRotas) {
        this.filteredRotas = filteredRotas;
    }

    public Rota getSelectedRota() {
        return selectedRota;
    }

    public void setSelectedRota(Rota selectedRota) {
        this.selectedRota = selectedRota;
    }
    

    private void loadInspetoria() {
        inspetorias = getInspetoriaFacade().listAll();
    }

    private void resetInspetoria() {
        inspetoria = new Inspetoria();
    }
}
