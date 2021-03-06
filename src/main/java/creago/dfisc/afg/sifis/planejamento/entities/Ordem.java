package creago.dfisc.afg.sifis.planejamento.entities;
// Generated 19/03/2015 11:54:19 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Ordem generated by hbm2java
 */
public class Ordem  implements java.io.Serializable {


     private Long idordem;
     private String identificador;
     private String numeroMemo;
     private Categoria categoria;
     private Fiscal fiscal;
     private Date inicio;
     private Date fim;
     private String totalAlmoco;
     private String totalDiaria;
     private String totalKm;
     private String qtdAlmoco;
     private String qtdDiaria;
     private String qtdKm;
     private String valorTotal;
     private String valorKm;
     private String valorDiaria;
     private Date dataCadastro;
     private String observacao;
     private Boolean memorando;
     private Set jurisdicaos = new HashSet(0);

    public Ordem() {
    }

	
    public Ordem(Date inicio, Date fim, String totalAlmoco, String totalDiaria, Date dataCadastro) {
        this.inicio = inicio;
        this.fim = fim;
        this.totalAlmoco = totalAlmoco;
        this.totalDiaria = totalDiaria;
        this.dataCadastro = dataCadastro;
    }
    public Ordem(Categoria categoria, Fiscal fiscal, Date inicio, Date fim, String totalAlmoco, String totalDiaria, String totalKm, String qtdAlmoco, String qtdDiaria, String qtdKm, String valorTotal, String valorKm, String valorDiaria, Date dataCadastro, String observacao, Boolean memorando, String numeroMemo, Set jurisdicaos) {
       this.categoria = categoria;
       this.fiscal = fiscal;
       this.inicio = inicio;
       this.fim = fim;
       this.totalAlmoco = totalAlmoco;
       this.totalDiaria = totalDiaria;
       this.totalKm = totalKm;
       this.qtdAlmoco = qtdAlmoco;
       this.qtdDiaria = qtdDiaria;
       this.qtdKm = qtdKm;
       this.valorTotal = valorTotal;
       this.valorKm = valorKm;
       this.valorDiaria = valorDiaria;
       this.dataCadastro = dataCadastro;
       this.observacao = observacao;
       this.memorando = memorando;
       this.numeroMemo = numeroMemo;
       this.jurisdicaos = jurisdicaos;
    }
   
    public Long getIdordem() {
        return this.idordem;
    }
    
    public void setIdordem(Long idordem) {
        this.idordem = idordem;
    }
    public Categoria getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public Fiscal getFiscal() {
        return this.fiscal;
    }
    
    public void setFiscal(Fiscal fiscal) {
        this.fiscal = fiscal;
    }
    public Date getInicio() {
        return this.inicio;
    }
    
    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }
    public Date getFim() {
        return this.fim;
    }
    
    public void setFim(Date fim) {
        this.fim = fim;
    }
    public String getTotalAlmoco() {
        return this.totalAlmoco;
    }
    
    public void setTotalAlmoco(String totalAlmoco) {
        this.totalAlmoco = totalAlmoco;
    }
    public String getTotalDiaria() {
        return this.totalDiaria;
    }
    
    public void setTotalDiaria(String totalDiaria) {
        this.totalDiaria = totalDiaria;
    }
    public String getTotalKm() {
        return this.totalKm;
    }
    
    public void setTotalKm(String totalKm) {
        this.totalKm = totalKm;
    }
    public String getQtdAlmoco() {
        return this.qtdAlmoco;
    }
    
    public void setQtdAlmoco(String qtdAlmoco) {
        this.qtdAlmoco = qtdAlmoco;
    }
    public String getQtdDiaria() {
        return this.qtdDiaria;
    }
    
    public void setQtdDiaria(String qtdDiaria) {
        this.qtdDiaria = qtdDiaria;
    }
    public String getQtdKm() {
        return this.qtdKm;
    }
    
    public void setQtdKm(String qtdKm) {
        this.qtdKm = qtdKm;
    }
    public String getValorTotal() {
        return this.valorTotal;
    }
    
    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }
    public String getValorKm() {
        return this.valorKm;
    }
    
    public void setValorKm(String valorKm) {
        this.valorKm = valorKm;
    }
    public String getValorDiaria() {
        return this.valorDiaria;
    }
    
    public void setValorDiaria(String valorDiaria) {
        this.valorDiaria = valorDiaria;
    }
    public Date getDataCadastro() {
        return this.dataCadastro;
    }
    
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    public String getObservacao() {
        return this.observacao;
    }
    
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    public Boolean getMemorando() {
        return this.memorando;
    }
    
    public void setMemorando(Boolean memorando) {
        this.memorando = memorando;
    }
    public Set getJurisdicaos() {
        return this.jurisdicaos;
    }
    
    public void setJurisdicaos(Set jurisdicaos) {
        this.jurisdicaos = jurisdicaos;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNumeroMemo() {
        return numeroMemo;
    }

    public void setNumeroMemo(String numeroMemo) {
        this.numeroMemo = numeroMemo;
    }




}


