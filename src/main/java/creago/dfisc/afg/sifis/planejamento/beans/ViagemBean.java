package creago.dfisc.afg.sifis.planejamento.beans;

import creago.dfisc.afg.sifis.planejamento.entities.Jurisdicao;
import creago.dfisc.afg.sifis.planejamento.entities.Categoria;
import creago.dfisc.afg.sifis.planejamento.entities.Feriado;
import creago.dfisc.afg.sifis.planejamento.entities.Ferias;
import creago.dfisc.afg.sifis.planejamento.entities.Fiscal;
import creago.dfisc.afg.sifis.planejamento.entities.Inspetoria;
import creago.dfisc.afg.sifis.planejamento.entities.Ordem;
import creago.dfisc.afg.sifis.planejamento.entities.Rota;
import creago.dfisc.afg.sifis.planejamento.entities.Viagem;
import creago.dfisc.afg.sifis.planejamento.facade.FeriadoFacade;
import creago.dfisc.afg.sifis.planejamento.facade.FeriasFacade;
import creago.dfisc.afg.sifis.planejamento.facade.FiscalFacade;
import creago.dfisc.afg.sifis.planejamento.facade.InspetoriaFacade;
import creago.dfisc.afg.sifis.planejamento.facade.RotaFacade;
import creago.dfisc.afg.sifis.planejamento.facade.ViagemFacade;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Tiago Borges Pereira
 */
@SessionScoped
@ManagedBean
public class ViagemBean extends AbstractBean implements Serializable {

    //SCHEDULE
    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();

    //VIAGEM
    private Viagem viagem;
    private List<Viagem> viagens;
    private Viagem selectedViagem;
    private List<Viagem> filteredViagem;
    private boolean viagensFiltradas;

    private Date inicioFilter;
    private Date fimFilter;

    private List<Viagem> filteredViagens;

    //FERIADO
    private List<Feriado> feriados;

    //FERIAS
    private List<Ferias> feriasList;

    //INSPETORIA
    private Inspetoria selectedInspetoria;

    //BOOLEAN
    private boolean pagamento;

    //ROTAS E FISCAIS
    private List<Rota> rotas;
    private List<Fiscal> fiscais;

    //FACADE
    private ViagemFacade viagemFacade;
    private InspetoriaFacade inspetoriaFacade;
    private RotaFacade rotaFacade;
    private FiscalFacade fiscalFacade;
    private FeriadoFacade feriadoFacade;
    private FeriasFacade feriasFacade;

    //PAINEL CONTROLE DE VISIBILIDADE
    private boolean painelRota = false;

    private String numMemo;

    public ViagemBean() {
        this.numMemo = "";
    }

    // GETTERS AND SETTERS
    // VIAGEM
    public Viagem getViagem() {
        if (viagem == null) {
            viagem = new Viagem();
            viagem.setCategoria(new Categoria());
            viagem.setFiscal(new Fiscal());
            viagem.setRota(new Rota());
        }
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public List<Viagem> getViagens() {
        
        this.viagensFiltradas = false;
        viagens = findAll();
        return viagens;
    }

    public void setViagens(List<Viagem> viagens) {
        this.viagens = viagens;
    }

    public List<Feriado> getFeriados() {
        return feriados;
    }

    public void setFeriados(List<Feriado> feriados) {
        this.feriados = feriados;
    }

    public List<Ferias> getFeriasList() {
        return feriasList;
    }

    public void setFeriasList(List<Ferias> feriasList) {
        this.feriasList = feriasList;
    }

    public Viagem getSelectedViagem() {
        return selectedViagem;
    }

    public void setSelectedViagem(Viagem selectedViagem) {
        this.selectedViagem = selectedViagem;
    }

    // INSPETORIA
    public Inspetoria getSelectedInspetoria() {
        if (selectedInspetoria == null) {
            selectedInspetoria = new Inspetoria();
        }
        return selectedInspetoria;
    }

    public void setSelectedInspetoria(Inspetoria selectedInspetoria) {
        this.selectedInspetoria = selectedInspetoria;
    }

    // ROTA E FISCAL
    public List<Rota> getRotas() {
        return rotas;
    }

    public void setRotas(List<Rota> rotas) {
        this.rotas = rotas;
    }

    public List<Fiscal> getFiscais() {
        return fiscais;
    }

    public void setFiscais(List<Fiscal> fiscais) {
        this.fiscais = fiscais;
    }

    public String getNumMemo() {
        return numMemo;
    }

    public void setNumMemo(String numMemo) {
        this.numMemo = numMemo;
    }

    public Date getInicioFilter() {
        return inicioFilter;
    }

    public void setInicioFilter(Date inicioFilter) {
        this.inicioFilter = inicioFilter;
    }

    public Date getFimFilter() {
        return fimFilter;
    }

    public void setFimFilter(Date fimFilter) {
        this.fimFilter = fimFilter;
    }

    public boolean isViagensFiltradas() {
        return viagensFiltradas;
    }

    public void setViagensFiltradas(boolean viagensFiltradas) {
        this.viagensFiltradas = viagensFiltradas;
    }

    //PAINEL CONTROLE VISIBILIDADE
    public boolean isPainelRota() {
        return painelRota;
    }

    public void setPainelRota(boolean painelRota) {
        this.painelRota = painelRota;
    }

    public List<Viagem> getFilteredViagens() {
        return filteredViagens;
    }

    public void setFilteredViagens(List<Viagem> filteredViagens) {
        this.filteredViagens = filteredViagens;
    }

    public List<Viagem> getFilteredViagem() {
        return filteredViagem;
    }

    public void setFilteredViagem(List<Viagem> filteredViagem) {
        this.filteredViagem = filteredViagem;
    }

    public InspetoriaFacade getInspetoriaFacade() {
        if (inspetoriaFacade == null) {
            inspetoriaFacade = new InspetoriaFacade();
        }
        return inspetoriaFacade;
    }

    public ViagemFacade getViagemFacade() {
        if (viagemFacade == null) {
            viagemFacade = new ViagemFacade();
        }
        return viagemFacade;
    }

    public RotaFacade getRotaFacade() {
        if (rotaFacade == null) {
            rotaFacade = new RotaFacade();
        }
        return rotaFacade;
    }

    public FiscalFacade getFiscalFacade() {
        if (fiscalFacade == null) {
            fiscalFacade = new FiscalFacade();
        }
        return fiscalFacade;
    }

    public FeriadoFacade getFeriadoFacade() {
        if (feriadoFacade == null) {
            feriadoFacade = new FeriadoFacade();
        }
        return feriadoFacade;
    }

    public FeriasFacade getFeriasFacade() {
        if (feriasFacade == null) {
            feriasFacade = new FeriasFacade();
        }
        return feriasFacade;
    }

    // CRUD
    public String create() {
        try {
            getViagemFacade().create(viagem);
            displayInfoMessageToUser("Viagem cadastrada com sucesso!");
            loadViagens();
            resetViagem();
            return "viagens-list";
        } catch (Exception e) {
            displayErrorMessageToUser("Erro ao cadastrar a nova viagem!");
            return "viagens-new";
        }
    }

    public String newViagem() {
        selectedInspetoria = new Inspetoria();
        rotas = new ArrayList<>();
        fiscais = new ArrayList<>();
        resetViagem();
        painelRota = false;
        return "viagens-new";
    }

    public List<Viagem> findAll() {
        loadViagens();
        return viagens;
    }

    public String remove() {
        try {
            getViagemFacade().delete(selectedViagem);
            displayInfoMessageToUser("Viagem excluída com sucesso!");
            loadViagens();
            resetViagem();
        } catch (Exception e) {
            displayErrorMessageToUser("Erro ao excluir viagem!");
            e.printStackTrace();
        }
        return "viagens-list";
    }

    public void onRowEdit(RowEditEvent event) {
        Viagem viagemAlterada = (Viagem) event.getObject();
        getViagemFacade().update(viagemAlterada);
        displayInfoMessageToUser("Viagem atualizada com sucesso!");
        loadViagens();
        resetViagem();
    }

    public void onCancel(RowEditEvent event) {
        displayInfoMessageToUser("Atualização cancelada com sucesso!");
    }

    // LOADERS AND RESETERS
    public void loadViagens() {
        viagens = getViagemFacade().listAll();
    }

    public void resetViagem() {
        viagem = new Viagem();
        viagem.setCategoria(new Categoria());
        viagem.setFiscal(new Fiscal());
        viagem.setRota(new Rota());
        viagem.setGerouOrdem(false);

    }

    /**
     * PROCESSADORES
     */
    /**
     * Método que processa alterações relativas à mudança da rota selecionada
     * para determinada viagem.
     */
    public void onRotaChange() {
        fiscais = new ArrayList<>();
        painelRota = true;
        viagem.setRota(getRotaFacade().find(viagem.getRota().getIdrota()));
        viagem.setInicio(null);
        viagem.setFim(null);
    }

    /**
     * Método que processa alterações relativas à mudança da inspetoria
     * selecionada para determinada viagem.
     */
    public void onInspetoriaChange() {
        painelRota = false;
        viagem.setInicio(null);
        viagem.setFim(null);
        painelRota = true;
        fiscais = new ArrayList<>();
        resetRotas();
    }

    /**
     * Realimenta a lista de rotas cadastradas para a inspetoria selecionada.
     */
    public void resetRotas() {
        rotas = new ArrayList<>();
        Iterator itR = getInspetoriaFacade().find(
                selectedInspetoria.getIdinspetoria()).getRotas().iterator();
        while (itR.hasNext()) {
            rotas.add((Rota) itR.next());
        }
    }

    /**
     * Realimenta a lista de fiscais alocados na inspetoria selecionada.
     */
    public void resetFiscais() {
        fiscais = new ArrayList<>();
        Iterator itF = getInspetoriaFacade().find(
                selectedInspetoria.getIdinspetoria()).getFiscals().iterator();
        while (itF.hasNext()) {
            fiscais.add((Fiscal) itF.next());
        }
    }

    /**
     * Método que processa alterações relativas à mudança da data de início de
     * uma determinada viagem.
     */
    public void onDateSelect() {
        resetFiscais();
        viagem.setFim(viagem.getInicio());

        Calendar cal = Calendar.getInstance();
        cal.setTime(viagem.getFim());

        int dias = viagem.getRota().getAlmoco() + viagem.getRota().getDiaria();
        cal.add(Calendar.DATE, dias - 1);
        viagem.setFim(cal.getTime());

        /**
         * Verifica se o período selecionado contém fim de semana.
         */
        if (isWeekend()) {
            FacesContext f = FacesContext.getCurrentInstance();
            f.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Data inválida!",
                    "A viagem deve ser realizada somente em dias úteis."));
        }

        /**
         * Verifica se algum fiscal está de férias e retira da lista de fiscais.
         */
        List<Fiscal> listaSecundaria = new ArrayList<>();
        for (Fiscal fiscal : fiscais) {
            if (!isVacationing(fiscal)) {
                listaSecundaria.add(fiscal);
            }
        }
        fiscais = listaSecundaria;
    }

    /**
     * Verifica se o período selecionado para a viagem contém sábado ou domingo.
     *
     * @return true caso seja fim de semana
     */
    public boolean isWeekend() {
        int inicio = viagem.getInicio().getDay();
        int dias = viagem.getRota().getAlmoco() + viagem.getRota().getDiaria();
        int limite = inicio + dias;

        for (int i = inicio; i < limite; i++) {
            if (i == 6 || i == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se o fiscal está de férias no período selecionado para a viagem
     *
     * @param fiscal Fiscal que poderá realizar a viagem caso não esteja de
     * férias
     * @return true caso o fiscal esteja de férias
     */
    public boolean isVacationing(Fiscal fiscal) {
        Ferias ferias;
        Iterator it = fiscal.getFeriases().iterator();
        while (it.hasNext()) {
            ferias = (Ferias) it.next();
            boolean inicio = viagem.getInicio().after(ferias.getInicio())
                    && viagem.getInicio().before(ferias.getFim());
            boolean fim = viagem.getFim().after(ferias.getInicio())
                    && viagem.getFim().before(ferias.getFim());
            boolean igualInicio = viagem.getInicio().equals(ferias.getInicio())
                    || viagem.getInicio().equals(ferias.getFim());
            boolean igualFim = viagem.getFim().equals(ferias.getInicio())
                    || viagem.getFim().equals(ferias.getFim());
            return inicio || fim || igualInicio || igualFim;
        }
        return false;
    }

    //SCHEDULE
    public ScheduleModel getEventModel() {
        loadViagens();
        startSchedule();
        return eventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void startSchedule() {
        this.eventModel = new DefaultScheduleModel();

        List<DefaultScheduleEvent> vgs = getViagemFacade().getViagens();
        if (vgs != null) {
            for (DefaultScheduleEvent v : vgs) {
                this.event = v;
                this.eventModel.addEvent(this.event);
            }
        }

        List<DefaultScheduleEvent> fds = getFeriadoFacade().getFeriados();
        if (fds != null) {
            for (DefaultScheduleEvent f : fds) {
                this.event = f;
                this.eventModel.addEvent(this.event);
            }
        }

        List<DefaultScheduleEvent> frs = getFeriasFacade().getFerias();
        if (frs != null) {
            for (DefaultScheduleEvent f : frs) {
                this.event = f;
                this.eventModel.addEvent(this.event);
            }
        }
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
        selectedViagem = (Viagem) event.getData();
    }

    public void addMessage() {
        String summary = pagamento ? "Pagando diária" : "Não está pagando diária";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void dialog() {
        RequestContext.getCurrentInstance().openDialog("insere-memorando");
    }

    public String gerarOrdem() {

        FacesContext context = FacesContext.getCurrentInstance();
        ValoresBean bean = context.getApplication().evaluateExpressionGet(context, "#{valoresBean}", ValoresBean.class);
        OrdemBean oBean = context.getApplication().evaluateExpressionGet(context, "#{ordemBean}", OrdemBean.class);

        oBean.resetOrdem();

        Double valorKm = viagem.getRota().getQuilometragem() * bean.getValorKm();
        Double valorAlmoco = (viagem.getRota().getAlmoco() * (bean.getValorDiaria() / 4.0));
        Double valorDiaria = (viagem.getRota().getDiaria() * bean.getValorDiaria());
        Double valorTotal = valorKm + valorDiaria + valorAlmoco;

        Date dt = new Date();

        Ordem os = new Ordem(viagem.getCategoria(), viagem.getFiscal(), viagem.getInicio(), viagem.getFim(),
                String.valueOf(valorAlmoco), String.valueOf(valorDiaria), String.valueOf(valorKm),
                String.valueOf(viagem.getRota().getAlmoco()), String.valueOf(viagem.getRota().getDiaria()),
                String.valueOf(viagem.getRota().getQuilometragem()), String.valueOf(valorTotal), String.valueOf(bean.getValorKm()),
                String.valueOf(bean.getValorDiaria()), dt, viagem.getObservacao(), true);

        String id = getViagemFacade().consultaId();
        if (id != null) {
            os.setIdentificador(id);
        } else {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(1);
            os.setIdentificador((new StringBuilder()).append("0001/").append(year).toString());
        }

        os.setNumeroMemo(numMemo);
        os.setIdviagem(viagem.getIdviagem());

        Iterator it = viagem.getRota().getJurisdicaos().iterator();

        while (it.hasNext()) {
            os.getJurisdicaos().add((Jurisdicao) it.next());
        }

        getViagemFacade().createOrdem(os);

        viagem.setGerouOrdem(true);
        getViagemFacade().update(viagem);
        oBean.loadOrdens();
        oBean.resetOrdem();
        displayInfoMessageToUser("Ordem cadastrada com sucesso!");

        if (viagensFiltradas) {
            return "viagens-periodo-list";
        } else {
            return "viagens-list";
        }
    }

    public String gerarOS(boolean viagensFiltradas) {

        this.viagensFiltradas = viagensFiltradas;
        FacesContext context = FacesContext.getCurrentInstance();

        OrdemBean oBean = context.getApplication().evaluateExpressionGet(context, "#{ordemBean}", OrdemBean.class);
        oBean.resetOrdem();

        if (viagem.getGerouOrdem() != null && viagem.getGerouOrdem() == true) {
            displayErrorMessageToUser("Já foi gerada ordem de serviço para essa viagem!");
            return "viagens-list";
        } else {
            return "insere-memorando";
        }

    }

    public String listFilteredViagens() throws ParseException {

        SimpleDateFormat formatter5 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String inicio = formatter5.format(this.inicioFilter);
        String fim = formatter5.format(this.fimFilter);

        this.filteredViagens = getViagemFacade().getViagensPorPeriodo(inicio, fim);
        return "viagens-periodo-list";
    }

}
