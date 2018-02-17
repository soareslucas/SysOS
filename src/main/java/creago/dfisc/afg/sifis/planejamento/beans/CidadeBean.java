package creago.dfisc.afg.sifis.planejamento.beans;

import creago.dfisc.afg.sifis.planejamento.entities.Cidade;
import creago.dfisc.afg.sifis.planejamento.entities.Feriado;
import creago.dfisc.afg.sifis.planejamento.entities.Inspetoria;
import creago.dfisc.afg.sifis.planejamento.entities.Jurisdicao;
import creago.dfisc.afg.sifis.planejamento.facade.CidadeFacade;
import creago.dfisc.afg.sifis.planejamento.facade.FeriadoFacade;
import creago.dfisc.afg.sifis.planejamento.facade.InspetoriaFacade;
import creago.dfisc.afg.sifis.planejamento.facade.JurisdicaoFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.*;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Tiago Borges Pereira
 */
@SessionScoped
@ManagedBean
public class CidadeBean extends AbstractBean implements Serializable {

    //CIDADE
    private Cidade cidade;
    private List<Cidade> cidades;
    private Cidade selectedCidade;
    private List<Cidade> filteredCidades;

    // JURISDIÇÃO
    private Jurisdicao jurisdicao;
    private List<Jurisdicao> jurisdicoes;
    private Jurisdicao selectedJurisdicao;
    private List<Jurisdicao> filteredJurisdicoes;

    //FERIADO
    private Feriado feriado;
    private List<Feriado> feriados;
    private Feriado selectedFeriado;
    private List<Feriado> filteredFeriados;

    //FACADE
    private CidadeFacade cidadeFacade;
    private JurisdicaoFacade jurisdicaoFacade;
    private FeriadoFacade feriadoFacade;
    private InspetoriaFacade inspetoriaFacade;

    //FACADE GETTERS
    public CidadeFacade getCidadeFacade() {
        if (cidadeFacade == null) {
            cidadeFacade = new CidadeFacade();
        }
        return cidadeFacade;
    }

    public JurisdicaoFacade getJurisdicaoFacade() {
        if (jurisdicaoFacade == null) {
            jurisdicaoFacade = new JurisdicaoFacade();
        }
        return jurisdicaoFacade;
    }

    public FeriadoFacade getFeriadoFacade() {
        if (feriadoFacade == null) {
            feriadoFacade = new FeriadoFacade();
        }
        return feriadoFacade;
    }

    public InspetoriaFacade getInspetoriaFacade() {
        if (inspetoriaFacade == null) {
            inspetoriaFacade = new InspetoriaFacade();
        }
        return inspetoriaFacade;
    }

    // CIDADE
    public String create() {
        try {
            cidade.setNome(cidade.getNome().toUpperCase());
            getCidadeFacade().create(cidade);
            jurisdicao = new Jurisdicao();
            jurisdicao.setCidade(cidade);
            jurisdicao.setNome("JURISDIÇÃO 01");
            getJurisdicaoFacade().create(jurisdicao);
            displayInfoMessageToUser("Cidade cadastrada com sucesso!");
            loadCidades();
            resetCidade();
            return "cidades-list";
        } catch (Exception e) {
            displayErrorMessageToUser("Erro ao cadastrar a nova cidade!");
            return "cidades-new";
        }
    }

    public String newCidade() {
        return "cidades-new";
    }

    public List<Cidade> findAll() {
        loadCidades();
        return cidades;
    }

    public String remove() {
        try {
            getCidadeFacade().delete(this.selectedCidade);
            displayInfoMessageToUser("Cidade excluída com sucesso!");
            loadCidades();
            resetCidade();
        } catch (Exception e) {
            displayErrorMessageToUser("Erro ao excluir cidade!");
            e.printStackTrace();
        }
        return "cidades-list";
    }

    public void onRowEdit(RowEditEvent event) {
        Cidade cidadeAlterada = (Cidade) event.getObject();
        cidadeAlterada.setInspetoria(cidadeAlterada.getInspetoria());
        cidadeAlterada.setNome(cidadeAlterada.getNome().toUpperCase());
        getCidadeFacade().update(cidadeAlterada);
        displayInfoMessageToUser("Cidade Atualizada.");
    }

    public void onCancel(RowEditEvent event) {
        displayInfoMessageToUser("Atualização Cancelada.");
    }

    // JURISDICAO
    public String createJurisdicao() {
        try {
            jurisdicao.setCidade(getCidadeFacade().find(selectedCidade.getIdcidade()));
            jurisdicao.setNome(jurisdicao.getNome().toUpperCase());
            getJurisdicaoFacade().create(jurisdicao);

            selectedCidade.getJurisdicaos().add(jurisdicao);

            displayInfoMessageToUser("Jurisdição cadastrada com sucesso!");

            resetJurisdicao();

            return "cidades-details";

        } catch (Exception e) {
            e.printStackTrace();
            displayErrorMessageToUser("Erro ao cadastrar a nova jurisdição!");
            return "cidades-details";
        }
    }

    public String newJurisdicao() {
        return "cidades-new-jurisdicao";
    }

    public String removeJurisdicao() {
        try {
            Jurisdicao jur = getJurisdicaoFacade().find(selectedJurisdicao.getIdjurisdicao());
            getJurisdicaoFacade().delete(jur);

            selectedCidade.getJurisdicaos().remove(selectedJurisdicao);
            displayInfoMessageToUser("Jurisdição excluída com sucesso!");

            return "cidades-details";
        } catch (Exception e) {
            displayErrorMessageToUser("Erro ao excluir feriado!");
            e.printStackTrace();
            return "cidades-details";
        }
    }

    public void onRowEditJurisdicao(RowEditEvent event) {
        Jurisdicao jurisdicaoAlterada = (Jurisdicao) event.getObject();
        jurisdicaoAlterada.setNome(jurisdicaoAlterada.getNome().toUpperCase());
        getJurisdicaoFacade().update(jurisdicaoAlterada);

        displayInfoMessageToUser("Jurisdicão Atualizada");
    }

    public void onCancelJurisdicao(RowEditEvent event) {
        displayInfoMessageToUser("Atualização Cancelada");
    }

    // FERIADO
    public String createFeriado() {
        try {
            feriado.getCidades().add(getCidadeFacade().find(selectedCidade.getIdcidade()));
            feriado.setNome(feriado.getNome().toUpperCase());
            getFeriadoFacade().create(feriado);

            selectedCidade.getFeriados().add(feriado);

            displayInfoMessageToUser("Feriado municipal cadastrado com sucesso!");

            resetFeriado();

            return "cidades-details";

        } catch (Exception e) {
            e.printStackTrace();
            displayErrorMessageToUser("Erro ao cadastrar o novo feriado municipal!");
            return "cidades-details";
        }
    }

    public String linkFeriado() {
        try {
            Cidade c = getCidadeFacade().find(selectedCidade.getIdcidade());
            Feriado f = getFeriadoFacade().find(feriado.getIdferiado());
            f.getCidades().add(c);
            getFeriadoFacade().update(f);

            selectedCidade.getFeriados().add(f);

            displayInfoMessageToUser("Feriado municipal adicionado com sucesso!");

            resetFeriado();

            return "cidades-details";

        } catch (Exception e) {
            e.printStackTrace();
            displayErrorMessageToUser("Erro ao adicionar feriado municipal!");
            return "cidades-details";
        }
    }

    public String newFeriado() {
        return "cidades-new-feriadomunicipal";
    }

    //GETTERS AND SETTERS
    public Cidade getCidade() {
        if (cidade == null) {
            cidade = new Cidade();
            cidade.setInspetoria(new Inspetoria());
        }
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getCidades() {
        cidades = findAll();

        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public Cidade getSelectedCidade() {
        return selectedCidade;
    }

    public void setSelectedCidade(Cidade selectedCidade) {
        this.selectedCidade = selectedCidade;
    }

    public List<Cidade> getFilteredCidades() {
        return filteredCidades;
    }

    public void setFilteredCidades(List<Cidade> filteredCidades) {
        this.filteredCidades = filteredCidades;
    }

    public Jurisdicao getJurisdicao() {
        if (jurisdicao == null) {
            jurisdicao = new Jurisdicao();
        }
        return jurisdicao;
    }

    public void setJurisdicao(Jurisdicao jurisdicao) {
        this.jurisdicao = jurisdicao;
    }

    public List<Jurisdicao> getJurisdicoes() {
        return jurisdicoes;
    }

    public void setJurisdicoes(List<Jurisdicao> jurisdicoes) {
        this.jurisdicoes = jurisdicoes;
    }

    public Jurisdicao getSelectedJurisdicao() {
        return selectedJurisdicao;
    }

    public void setSelectedJurisdicao(Jurisdicao selectedJurisdicao) {
        this.selectedJurisdicao = selectedJurisdicao;
    }

    public List<Jurisdicao> getFilteredJurisdicoes() {
        return filteredJurisdicoes;
    }

    public void setFilteredJurisdicoes(List<Jurisdicao> filteredJurisdicoes) {
        this.filteredJurisdicoes = filteredJurisdicoes;
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

    // LOADERS AND RESETERS
    // cidade
    private void loadCidades() {
        cidades = getCidadeFacade().listAll();
    }

    private void resetCidade() {
        cidade = new Cidade();
        cidade.setInspetoria(new Inspetoria());
    }

    // jurisdicao
    private void loadJurisdicoes() {
        jurisdicoes = getJurisdicaoFacade().listAll();
    }

    private void resetJurisdicao() {
        jurisdicao = new Jurisdicao();
    }

    // feriado
    private void loadFeriados() {
        feriados = getFeriadoFacade().listAll();
    }

    private void resetFeriado() {
        feriado = new Feriado();
    }

}
