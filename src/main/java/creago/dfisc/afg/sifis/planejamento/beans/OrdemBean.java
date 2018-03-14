package creago.dfisc.afg.sifis.planejamento.beans;

import creago.dfisc.afg.sifis.planejamento.entities.Jurisdicao;
import creago.dfisc.afg.sifis.planejamento.entities.Categoria;
import creago.dfisc.afg.sifis.planejamento.entities.Cidade;
import creago.dfisc.afg.sifis.planejamento.entities.Feriado;
import creago.dfisc.afg.sifis.planejamento.entities.Ferias;
import creago.dfisc.afg.sifis.planejamento.entities.Fiscal;
import creago.dfisc.afg.sifis.planejamento.entities.Inspetoria;
import creago.dfisc.afg.sifis.planejamento.entities.Ordem;
import creago.dfisc.afg.sifis.planejamento.entities.OrdemServico;
import creago.dfisc.afg.sifis.planejamento.entities.Rota;
import creago.dfisc.afg.sifis.planejamento.entities.Viagem;
import creago.dfisc.afg.sifis.planejamento.facade.FeriadoFacade;
import creago.dfisc.afg.sifis.planejamento.facade.FeriasFacade;
import creago.dfisc.afg.sifis.planejamento.facade.FiscalFacade;
import creago.dfisc.afg.sifis.planejamento.facade.InspetoriaFacade;
import creago.dfisc.afg.sifis.planejamento.facade.OrdemFacade;
import creago.dfisc.afg.sifis.planejamento.facade.RotaFacade;
import creago.dfisc.afg.sifis.planejamento.facade.ViagemFacade;
import creago.dfisc.afg.sifis.planejamento.utils.GerarPDF;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.DualListModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Tiago Borges Pereira
 */
@ManagedBean
@ViewScoped
public class OrdemBean extends AbstractBean implements Serializable {

    //SCHEDULE
    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();

    //VIAGEM
    private Ordem ordem;
    private List<Ordem> ordens;
    private Ordem selectedOrdem;
    private List<Ordem> filteredOrdem;

    //FERIADO
    private List<Feriado> feriados;
    private Feriado feriado;

    //FERIAS
    private List<Ferias> feriasList;
    private Ferias ferias;

    private Fiscal selectedFiscal;
    private Inspetoria selectedInspetoria;
    private Inspetoria inspetoria;

    private Fiscal fiscal;

    //BOOLEAN
    private boolean isViagem;
    private boolean isFeriado;
    private boolean isFerias;

    //ROTAS E FISCAIS
    private List<Rota> rotas;
    private List<Fiscal> fiscais;

    //FACADE
    private OrdemFacade ordemFacade;
    private InspetoriaFacade inspetoriaFacade;
    private RotaFacade rotaFacade;
    private FiscalFacade fiscalFacade;
    private FeriadoFacade feriadoFacade;
    private FeriasFacade feriasFacade;

    //PAINEL CONTROLE DE VISIBILIDADE
    private boolean painelMemo = false;

    //JURISDICAO
    private List<Jurisdicao> jurisdicaoSource = new ArrayList<>();
    private List<Jurisdicao> jurisdicaoTarget = new ArrayList<>();
    private DualListModel<Jurisdicao> jurisdicoes;

    public List<Ordem> getFilteredOrdem() {
        return filteredOrdem;
    }

    public void setFilteredOrdem(List<Ordem> filteredOrdem) {
        this.filteredOrdem = filteredOrdem;
    }

    public InspetoriaFacade getInspetoriaFacade() {
        if (inspetoriaFacade == null) {
            inspetoriaFacade = new InspetoriaFacade();
        }
        return inspetoriaFacade;
    }

    public OrdemFacade getOrdemFacade() {
        if (ordemFacade == null) {
            ordemFacade = new OrdemFacade();
        }
        return ordemFacade;
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

            if (ordem.getMemorando()) {
                double qtdAlmoco = Double.parseDouble(ordem.getQtdAlmoco());
                double valorDiaria = Double.parseDouble(ordem.getValorDiaria());
                double qtdDiaria = Double.parseDouble(ordem.getQtdDiaria());
                double valorKm = Double.parseDouble(ordem.getValorKm());
                double qtdKm = Double.parseDouble(ordem.getQtdKm());
                double totalAlmoco = qtdAlmoco * (valorDiaria / 5);
                double totalDiarias = valorDiaria * qtdDiaria;
                double totalKm = valorKm * qtdKm;
                double total = totalAlmoco + totalDiarias + totalKm;
                
                ordem.setTotalAlmoco(String.valueOf(totalAlmoco));
                ordem.setTotalDiaria(String.valueOf(totalDiarias));
                ordem.setTotalKm(String.valueOf(totalKm));
                ordem.setValorTotal(String.valueOf(total));
            } else {
                ordem.setQtdAlmoco("0");
                ordem.setQtdDiaria("0");
                ordem.setQtdKm("0");
                ordem.setTotalAlmoco("0.0");
                ordem.setTotalDiaria("0.0");
                ordem.setTotalKm("0.0");
                ordem.setValorTotal("0.0");

            }

            if (ordem.getObservacao() == null) {
                ordem.setObservacao("");
            }

            Iterator it = jurisdicoes.getTarget().iterator();

            while (it.hasNext()) {
                ordem.getJurisdicaos().add((Jurisdicao) it.next());
            }

            Date dt = new Date();
            
            ordem.setDataCadastro(dt);
            
            
            getOrdemFacade().create(ordem);
            displayInfoMessageToUser("Ordem cadastrada com sucesso!");
            loadOrdens();
            resetOrdem();
            return "ordem-list";

        } catch (Exception e) {
            displayErrorMessageToUser("Erro ao cadastrar a nova rota!");
            return "rotas-inspetoria-selection";
        }
    }

    public String newOrdem() {

        fiscais = new ArrayList<>();
        resetOrdem();
        painelMemo = false;
        return "ordem-new";
    }

    public List<Ordem> findAll() {
        loadOrdens();
        return ordens;
    }

    public String remove() {
        try {
            getOrdemFacade().delete(selectedOrdem);
            displayInfoMessageToUser("Ordem excluída com sucesso!");
            loadOrdens();
            resetOrdem();
        } catch (Exception e) {
            displayErrorMessageToUser("Erro ao excluir ordem de serviço!");
            e.printStackTrace();
        }
        return "ordem-list";
    }

    public void onRowEdit(RowEditEvent event) {
        Ordem ordemAlterada = (Ordem) event.getObject();
        getOrdemFacade().update(ordemAlterada);
        displayInfoMessageToUser("Ordem de serviço atualizada com sucesso!");
        loadOrdens();
        resetOrdem();
    }

    public void onCancel(RowEditEvent event) {
        displayInfoMessageToUser("Atualização cancelada com sucesso!");
    }

    // GETTERS AND SETTERS
    // VIAGEM
    public Ordem getOrdem() {
        if (ordem == null) {
            resetOrdem();
            geraCidades();
            resetValores();

        }
        return ordem;
    }

    public void setOrdem(Ordem ordem) {
        this.ordem = ordem;
    }

    public List<Ordem> getOrdens() {
        ordens = findAll();
        return ordens;
    }

    public void setOrdens(List<Ordem> ordens) {
        this.ordens = ordens;
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

    public Ordem getSelectedOrdem() {
        return selectedOrdem;
    }

    public void setSelectedOrdem(Ordem selectedOrdem) {
        this.selectedOrdem = selectedOrdem;
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

    public Fiscal getSelectedFiscal() {
        return selectedFiscal;
    }

    public void setSelectedFiscal(Fiscal selectedFiscal) {
        this.selectedFiscal = selectedFiscal;
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

    //PAINEL CONTROLE VISIBILIDADE
    public boolean isPainelMemo() {
        return painelMemo;
    }

    public void setPainelMemo(boolean painelMemo) {
        this.painelMemo = painelMemo;
    }

    public void mostraPainel() {

        if (this.painelMemo) {
            this.painelMemo = false;
        } else {
            this.painelMemo = true;
        }
    }

    // LOADERS AND RESETERS
    private void loadOrdens() {
        ordens = getOrdemFacade().listAll();
    }

    private void resetOrdem() {
        ordem = new Ordem();

        ordem.setCategoria(new Categoria());
        ordem.setFiscal(new Fiscal());

        String id = getOrdemFacade().consultaId();
        if (id != null) {
            ordem.setIdentificador(id);
        } else {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(1);
            ordem.setIdentificador((new StringBuilder()).append("0001/").append(year).toString());
        }
    }

    public void resetValores() {
        ordem.setValorDiaria("190");
        ordem.setValorKm("0.34");

    }

    /**
     * PROCESSADORES
     */
    /**
     * Método que processa alterações relativas à mudança da rota selecionada
     * para determinada ordem.
     */
//    public void onRotaChange() {
//        fiscais = new ArrayList<>();
//        painelMemo = true;
//        ordem.setRota(getRotaFacade().find(ordem.getRota().getIdrota()));
//        ordem.setInicio(null);
//        ordem.setFim(null);
//    }
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
     * uma determinada ordem.
     */
//    public void onDateSelect() {
//        resetFiscais();
//        ordem.setFim(ordem.getInicio());
//
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(ordem.getFim());
//
//        int dias = ordem.getRota().getAlmoco() + ordem.getRota().getDiaria();
//        cal.add(Calendar.DATE, dias - 1);
//        ordem.setFim(cal.getTime());
//
//        /**
//         * Verifica se o período selecionado contém fim de semana.
//         */
//        if (isWeekend()) {
//            FacesContext f = FacesContext.getCurrentInstance();
//            f.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                    "Data inválida!",
//                    "A viagem deve ser realizada somente em dias úteis."));
//        }
//
//        /**
//         * Verifica se algum fiscal está de férias e retira da lista de fiscais.
//         */
//        List<Fiscal> listaSecundaria = new ArrayList<>();
//        for (Fiscal fiscal : fiscais) {
//            if (!isVacationing(fiscal)) {
//                listaSecundaria.add(fiscal);
//            }
//        }
//        fiscais = listaSecundaria;
//    }
    /**
     * Verifica se o período selecionado para a ordem contém sábado ou domingo.
     *
     * @return true caso seja fim de semana
     */
//    public boolean isWeekend() {
//        int inicio = ordem.getInicio().getDay();
//        int dias = ordem.getRota().getAlmoco() + ordem.getRota().getDiaria();
//        int limite = inicio + dias;
//
//        for (int i = inicio; i < limite; i++) {
//            if (i == 6 || i == 0) {
//                return true;
//            }
//        }
//        return false;
//    }
    /**
     * Verifica se o fiscal está de férias no período selecionado para a ordem
     *
     * @param fiscal Fiscal que poderá realizar a ordem caso não esteja de
     * férias
     * @return true caso o fiscal esteja de férias
     */
    public boolean isVacationing(Fiscal fiscal) {
        Ferias ferias;
        Iterator it = fiscal.getFeriases().iterator();
        while (it.hasNext()) {
            ferias = (Ferias) it.next();
            boolean inicio = ordem.getInicio().after(ferias.getInicio())
                    && ordem.getInicio().before(ferias.getFim());
            boolean fim = ordem.getFim().after(ferias.getInicio())
                    && ordem.getFim().before(ferias.getFim());
            boolean igualInicio = ordem.getInicio().equals(ferias.getInicio())
                    || ordem.getInicio().equals(ferias.getFim());
            boolean igualFim = ordem.getFim().equals(ferias.getInicio())
                    || ordem.getFim().equals(ferias.getFim());
            return inicio || fim || igualInicio || igualFim;
        }
        return false;
    }

    //SCHEDULE
    public ScheduleModel getEventModel() {
        loadOrdens();
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

        List<DefaultScheduleEvent> vgs = getOrdemFacade().getOrdens();
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
        selectedOrdem = (Ordem) event.getData();
    }

//    public void pdf() {
//        Double valorKm = ordem.getRota().getQuilometragem() * 0.3;
//        Double valorDiarias = (ordem.getRota().getDiaria() * 190.0) + (ordem.getRota().getAlmoco() * 38.0);
//        Double valorTotal = valorKm + valorDiarias;
//        String nomeFiscal = ordem.getFiscal().getNome() + " " + ordem.getFiscal().getSobrenome();
//
//        ArrayList<String> cidades = new ArrayList<>();
//
//        Set<Jurisdicao> jur = ordem.getRota().getJurisdicaos();
//
//        for (Jurisdicao iterador : jur) {
//            cidades.add(iterador.getCidade().getNome());
//        }
//
//        OrdemServico os = new OrdemServico(ordem.getIdordem(), nomeFiscal,
//                ordem.getFiscal().getMatricula(), ordem.getFiscal().getInspetoria().getNome(),
//                ordem.getRota().getQuilometragem(), ordem.getInicio(), ordem.getFim(), ordem.getRota().getJurisdicaos(),
//                ordem.getRota().getDiaria(), ordem.getRota().getAlmoco(), valorKm, valorDiarias, valorTotal, ordem.getObservacao(), cidades, ordem.getRota());
//
//        GerarPDF.imprimirRelatorioOS(os);
//    }
//    public void onEventSelect(SelectEvent selectEvent) {
//        event = (ScheduleEvent) selectEvent.getObject();
//        System.out.println("EVENTO SELECIONADO!");
//
//        if (event != null) {
//            if (event.getStyleClass().contains("categoria")) {
//                selectedViagem = (Viagem) event.getData();
//                isViagem = true;
//                isFeriado = false;
//                isFerias = false;
//                System.out.println("É UMA VIAGEM");
//            }
//            if (event.getStyleClass().equals("feriado")) {
//                feriado = (Feriado) event.getData();
//                isViagem = false;
//                isFeriado = true;
//                isFerias = false;
//                System.out.println("É UM FERIADO");
//            }
//            if (event.getStyleClass().equals("ferias")) {
//                ferias = (Ferias) event.getData();
//                isViagem = false;
//                isFeriado = false;
//                isFerias = true;
//                System.out.println("SÃO FÉRIAS");
//            }
//        }
//    }
    public Fiscal getFiscal() {
        return fiscal;
    }

    public void setFiscal(Fiscal fiscal) {
        this.fiscal = fiscal;
    }

    /**
     * Método que processa alterações relativas à mudança da inspetoria
     * selecionada para determinada ordem.
     */
    public void onFiscalChange() {

        painelMemo = false;
        ordem.setInicio(null);
        ordem.setFim(null);
        ordem.setFiscal(getFiscalFacade().find(ordem.getFiscal().getIdfiscal()));

    }

    public Inspetoria getInspetoria() {
        return inspetoria;
    }

    public void setInspetoria(Inspetoria inspetoria) {
        this.inspetoria = inspetoria;
    }

    public String geraCidades() {
        Iterator insp = getInspetoriaFacade().listAll().iterator();

        while (insp.hasNext()) {
            Inspetoria i = (Inspetoria) insp.next();
            Iterator it = i.getCidades().iterator();
            while (it.hasNext()) {
                Cidade c = (Cidade) it.next();
                Iterator itJ = c.getJurisdicaos().iterator();
                while (itJ.hasNext()) {
                    jurisdicaoSource.add((Jurisdicao) itJ.next());
                }
            }

        }

        this.jurisdicoes = new DualListModel<>(jurisdicaoSource, jurisdicaoTarget);
        return "ordem-new";

    }

    public Feriado getFeriado() {
        return feriado;
    }

    public void setFeriado(Feriado feriado) {
        this.feriado = feriado;
    }

    public boolean isIsViagem() {
        return isViagem;
    }

    public void setIsViagem(boolean isViagem) {
        this.isViagem = isViagem;
    }

    public boolean isIsFeriado() {
        return isFeriado;
    }

    public void setIsFeriado(boolean isFeriado) {
        this.isFeriado = isFeriado;
    }

    public boolean isIsFerias() {
        return isFerias;
    }

    public void setIsFerias(boolean isFerias) {
        this.isFerias = isFerias;
    }

    public List<Jurisdicao> getJurisdicaoSource() {
        return jurisdicaoSource;
    }

    public void setJurisdicaoSource(List<Jurisdicao> jurisdicaoSource) {
        this.jurisdicaoSource = jurisdicaoSource;
    }

    public List<Jurisdicao> getJurisdicaoTarget() {
        return jurisdicaoTarget;
    }

    public void setJurisdicaoTarget(List<Jurisdicao> jurisdicaoTarget) {
        this.jurisdicaoTarget = jurisdicaoTarget;
    }

    public DualListModel<Jurisdicao> getJurisdicoes() {
        return jurisdicoes;
    }

    public void setJurisdicoes(DualListModel<Jurisdicao> jurisdicoes) {
        this.jurisdicoes = jurisdicoes;
    }
    
      public void pdf() {
        Double valorKm = Double.parseDouble(ordem.getValorKm());
        Double valorDiarias = Double.parseDouble(ordem.getTotalDiaria()) + Double.parseDouble(ordem.getTotalAlmoco());
        Double valorTotal = valorKm + valorDiarias;
        String nomeFiscal = ordem.getFiscal().getNome() + " " + ordem.getFiscal().getSobrenome();

        String cidades = "";

        Set<Jurisdicao> jur = ordem.getJurisdicaos();
        
       List<Jurisdicao> list = new ArrayList<Jurisdicao>(jur);
        
        for (Jurisdicao iterador : list) {
            cidades += iterador.getCidade().getNome();
            if(list.get(list.size()-1) == iterador ){
                cidades += ".";
            } else{
                cidades += ", ";
            }               
        }
        
        System.out.println(ordem.getDataCadastro().toString());

        OrdemServico os = new OrdemServico(ordem.getIdentificador(), nomeFiscal,
                ordem.getFiscal().getMatricula(),
                ordem.getFiscal().getInspetoria().getNome(),
                Integer.parseInt(ordem.getQtdKm()),
                ordem.getInicio(), ordem.getFim(),
                ordem.getDataCadastro(), ordem.getJurisdicaos(),
                Integer.parseInt(ordem.getQtdDiaria()),
                Integer.parseInt(ordem.getQtdAlmoco()),
                valorKm, valorDiarias, valorTotal,
                ordem.getObservacao(), cidades, ordem.getValorKm(), 
                ordem.getValorDiaria());

        GerarPDF.imprimirRelatorioOS(os);
    }
    
    
    

}
