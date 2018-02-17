package creago.dfisc.afg.sifis.planejamento.beans;

import creago.dfisc.afg.sifis.planejamento.entities.Ferias;
import creago.dfisc.afg.sifis.planejamento.entities.Fiscal;
import creago.dfisc.afg.sifis.planejamento.entities.Inspetoria;
import creago.dfisc.afg.sifis.planejamento.entities.Viagem;
import creago.dfisc.afg.sifis.planejamento.facade.FeriasFacade;
import creago.dfisc.afg.sifis.planejamento.facade.FiscalFacade;
import creago.dfisc.afg.sifis.planejamento.facade.ViagemFacade;
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
public class FiscalBean extends AbstractBean implements Serializable {

    private Fiscal fiscal;
    private List<Fiscal> fiscais;
    private Fiscal selectedFiscal;
    private List<Fiscal> filteredFiscais;

    private Ferias ferias;
    private Ferias selectedFerias;
    private List<Ferias> filteredFerias;
    
    private Viagem viagem;
    private Viagem selectedViagem;
    private List<Viagem> filteredViagens;

    private FiscalFacade fiscalFacade;
    private FeriasFacade feriasFacade;
    private ViagemFacade viagemFacade;

    public FiscalFacade getFiscalFacade() {
        if (fiscalFacade == null) {
            fiscalFacade = new FiscalFacade();
        }
        return fiscalFacade;
    }

    public FeriasFacade getFeriasFacade() {
        if (feriasFacade == null) {
            feriasFacade = new FeriasFacade();
        }
        return feriasFacade;
    }

    public ViagemFacade getViagemFacade() {
        if (viagemFacade == null) {
            viagemFacade = new ViagemFacade();
        }
        return viagemFacade;
    }

    public String create() {
        try {
            getFiscalFacade().create(fiscal);
            displayInfoMessageToUser("Fiscal cadastrado com sucesso!");
            loadFiscais();
            resetFiscal();
            return "fiscais-list";
        } catch (Exception e) {
            displayErrorMessageToUser("Erro ao cadastrar o novo fiscal!");
            return "fiscais-new";
        }
    }

    public String newFiscal() {
        return "fiscais-new";
    }

    public List<Fiscal> findAll() {
        loadFiscais();
        return fiscais;
    }

    public String remove() {
        try {
            getFiscalFacade().delete(selectedFiscal);
            displayInfoMessageToUser("Fiscal excluído com sucesso!");
            loadFiscais();
            resetFiscal();
        } catch (Exception e) {
            displayErrorMessageToUser("Erro ao excluir fiscal!");
            e.printStackTrace();
        }
        return "fiscais-list";
    }

    public void onRowEdit(RowEditEvent event) {
        Fiscal fiscalAlterado = (Fiscal) event.getObject();
        getFiscalFacade().update(fiscalAlterado);
        displayInfoMessageToUser("Fiscal atualizado com sucesso!");
        loadFiscais();
        resetFiscal();
    }

    public void onCancel(RowEditEvent event) {
        displayInfoMessageToUser("Atualização cancelada com sucesso!");
    }

    // FERIAS
    public String createFerias() {
        try {
            ferias.setFiscal(getFiscalFacade().find(selectedFiscal.getIdfiscal()));
            getFeriasFacade().create(ferias);

            selectedFiscal.getFeriases().add(ferias);

            displayInfoMessageToUser("Ferias cadastrada com sucesso!");

            resetFerias();

            return "fiscais-details";

        } catch (Exception e) {
            e.printStackTrace();
            displayErrorMessageToUser("Erro ao cadastrar as novas férias!");
            return "fiscais-details";
        }
    }

    public String newFerias() {
        return "fiscais-new-ferias";
    }
    
    public String removeFerias() {
        try {
            Ferias f = getFeriasFacade().find(selectedFerias.getIdferias());
            getFeriasFacade().delete(f);

            selectedFiscal.getFeriases().remove(selectedFerias);
            displayInfoMessageToUser("Férias excluídas com sucesso!");

            return "fiscais-details";
        } catch (Exception e) {
            displayErrorMessageToUser("Erro ao excluir férias!");
            e.printStackTrace();
            return "fiscais-details";
        }
    }
    
    public void onRowEditFerias(RowEditEvent event) {
        Ferias feriasAlterada = (Ferias) event.getObject();
        getFeriasFacade().update(feriasAlterada);
        displayInfoMessageToUser("Férias atualizadas com sucesso!");

    }

    public void onCancelFerias(RowEditEvent event) {
        displayInfoMessageToUser("Atualização cancelada com sucesso!");
    }
    
    // VIAGEM
    public String createViagem() {
        try {
            viagem.setFiscal(getFiscalFacade().find(selectedFiscal.getIdfiscal()));
            getViagemFacade().create(viagem);

            selectedFiscal.getViagems().add(viagem);

            displayInfoMessageToUser("Viagem cadastrada com sucesso!");

            resetViagem();

            return "fiscais-details";

        } catch (Exception e) {
            e.printStackTrace();
            displayErrorMessageToUser("Erro ao cadastrar a nova viagem!");
            return "fiscais-details";
        }
    }

    public String newViagem() {
        return "fiscais-new-viagem";
    }

    // GETTERS AND SETTERS
    public Fiscal getFiscal() {
        if (fiscal == null) {
            fiscal = new Fiscal();
            fiscal.setInspetoria(new Inspetoria());
        }
        return fiscal;
    }

    public void setFiscal(Fiscal fiscal) {
        this.fiscal = fiscal;
    }

    public List<Fiscal> getFiscais() {
        fiscais = findAll();

        return fiscais;
    }

    public void setFiscais(List<Fiscal> fiscais) {
        this.fiscais = fiscais;
    }

    public Fiscal getSelectedFiscal() {
        return selectedFiscal;
    }

    public void setSelectedFiscal(Fiscal selectedFiscal) {
        this.selectedFiscal = selectedFiscal;
    }

    public List<Fiscal> getFilteredFiscais() {
        return filteredFiscais;
    }

    public void setFilteredFiscais(List<Fiscal> filteredFiscais) {
        this.filteredFiscais = filteredFiscais;
    }

    public Ferias getFerias() {
        if (ferias == null) {
            ferias = new Ferias();
        }
        return ferias;
    }

    public void setFerias(Ferias ferias) {
        this.ferias = ferias;
    }

    public Ferias getSelectedFerias() {
        return selectedFerias;
    }

    public void setSelectedFerias(Ferias selectedFerias) {
        this.selectedFerias = selectedFerias;
    }

    public List<Ferias> getFilteredFerias() {
        return filteredFerias;
    }

    public void setFilteredFerias(List<Ferias> filteredFerias) {
        this.filteredFerias = filteredFerias;
    }

    public Viagem getViagem() {
        if (viagem == null) {
            viagem = new Viagem();
        }
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public Viagem getSelectedViagem() {
        return selectedViagem;
    }

    public void setSelectedViagem(Viagem selectedViagens) {
        this.selectedViagem = selectedViagens;
    }

    public List<Viagem> getFilteredViagens() {
        return filteredViagens;
    }

    public void setFilteredViagens(List<Viagem> filteredViagens) {
        this.filteredViagens = filteredViagens;
    }

    // LOADER AND RESETER
    private void loadFiscais() {
        fiscais = getFiscalFacade().listAll();
    }

    private void resetFiscal() {
        fiscal = new Fiscal();
        fiscal.setInspetoria(new Inspetoria());
    }
    
    private void resetFerias() {
        ferias = new Ferias();
    }
    
    private void resetViagem(){
        viagem = new Viagem();
    }
}
