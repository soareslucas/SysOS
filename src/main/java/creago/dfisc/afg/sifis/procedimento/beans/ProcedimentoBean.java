package creago.dfisc.afg.sifis.procedimento.beans;

import creago.dfisc.afg.sifis.planejamento.utils.GerarPDF;
import creago.dfisc.afg.sifis.planejamento.beans.AbstractBean;
import creago.dfisc.afg.sifis.procedimento.entities.Procedimento;
import creago.dfisc.afg.sifis.procedimento.facade.ProcedimentoFacade;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Tiago Borges Pereira
 */
@SessionScoped
@ManagedBean
public class ProcedimentoBean extends AbstractBean implements Serializable {

    //PROCEDIMENTO
    private Procedimento procedimento;
    private List<Procedimento> procedimentos;
    private Procedimento selectedProcedimento;
    private List<Procedimento> filteredProcedimento;

    //FACADE
    private ProcedimentoFacade procedimentoFacade;

    public ProcedimentoFacade getProcedimentoFacade() {
        if (procedimentoFacade == null) {
            procedimentoFacade = new ProcedimentoFacade();
        }
        return procedimentoFacade;
    }

    //CRUD
    public String create() {
        try {
            getProcedimentoFacade().create(procedimento);
            displayInfoMessageToUser("Procedimento cadastrado com sucesso!");
            loadProcedimentos();
            resetProcedimento();
            return "procedimentos-list";
        } catch (Exception e) {
            displayErrorMessageToUser("Erro ao cadastrar o novo procedimento!");
            return "procedimentos-new";
        }
    }

    public String newProcedimento() {
        return "procedimentos-new";
    }

    public List<Procedimento> findAll() {
        loadProcedimentos();
        return procedimentos;
    }

    public String remove() {
        try {
            getProcedimentoFacade().delete(selectedProcedimento);
            displayInfoMessageToUser("Procedimento excluído com sucesso!");
            loadProcedimentos();
            resetProcedimento();
        } catch (Exception e) {
            displayErrorMessageToUser("Erro ao remover o procedimento!");
            e.printStackTrace();
        }
        return "procedimentos-list";
    }

    public String edit() {
        return "procedimentos-edit";
    }

    public String update() {
        try {
            getProcedimentoFacade().update(selectedProcedimento);
            displayInfoMessageToUser("Procedimento atualizado com sucesso!");
            loadProcedimentos();
            resetProcedimento();
            return "procedimentos-details";
        } catch (Exception e) {
            displayErrorMessageToUser("Erro ao atualizar o procedimento!");
            return "procedimentos-edit";
        }
    }

    //GETTERS AND SETTERS
    public Procedimento getProcedimento() {
        if (procedimento == null) {
            resetProcedimento();
        }
        return procedimento;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    public List<Procedimento> getProcedimentos() {
        procedimentos = findAll();
        return procedimentos;
    }

    public void setProcedimentos(List<Procedimento> procedimentos) {
        this.procedimentos = procedimentos;
    }

    public Procedimento getSelectedProcedimento() {
        return selectedProcedimento;
    }

    public void setSelectedProcedimento(Procedimento selectedProcedimento) {
        this.selectedProcedimento = selectedProcedimento;
    }

    public List<Procedimento> getFilteredProcedimento() {
        return filteredProcedimento;
    }

    public void setFilteredProcedimento(List<Procedimento> filteredProcedimento) {
        this.filteredProcedimento = filteredProcedimento;
    }

    //LOAD AND RESET
    private void loadProcedimentos() {
        procedimentos = getProcedimentoFacade().listAll();
    }

    private void resetProcedimento() {
        procedimento = new Procedimento();
        String id = getProcedimentoFacade().consultaId();
        if (id != null) {
            procedimento.setIdentificador(id);
        } else {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(1);
            procedimento.setIdentificador((new StringBuilder()).append("0001/").append(year).toString());
        }
    }

        public void pdf() {

        Date todayDate = new Date();

        if (null != this.procedimento.getInicio()) {
            System.out.println("inicio não está nulo");
        }

        if (null != this.procedimento.getFim()) {
            System.out.println("fim não está nulo");
        }

        this.procedimento.setVigencia(false);        
        if (todayDate.after(this.procedimento.getInicio())) {
            if (this.procedimento.getFim() == null) {
                this.procedimento.setVigencia(true);
            } else {
                if (todayDate.before(this.procedimento.getFim())) {
                    this.procedimento.setVigencia(true);
                }
            }
        } 
        GerarPDF.imprimirRelatorio(this.procedimento);
    }
   

}
