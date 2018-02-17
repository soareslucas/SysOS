/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creago.dfisc.afg.sifis.planejamento.entities;

import java.util.Date;

/**
 *
 * @author creago
 */
public class OrdemServico {

    private String idOrdemServico;
    private String nomeFiscal;
    private Integer matricula;
    private String lotacao;
    private String quilometragem;
    private Date inicio;
    private Date fim;
    private Date dataCadastro;
    private String cidades;
    private String diarias;
    private String almocos;
    private String valorTotalKm;
    private String valorTotalDiaria;
    private String valorTotal;
    private String observacao;
    private Rota rota;
    private String valorKm;
    private String valorDiaria;
    private String numMemo;
    private Boolean memorando;

    public OrdemServico(String idOrdemServico, String nomeFiscal, Integer matricula, String lotacao, String quantidadeKm, Date inicio, Date fim, Date dataCadastro,  String diarias, String almocos, String valorTotalKm, String valorTotalDiaria, String valorTotal, String observacao, String cidades, String valorKm, String valorDiaria, String numMemo, Boolean memorando) {

        this.observacao = observacao;
        this.idOrdemServico = idOrdemServico;

        this.nomeFiscal = nomeFiscal;
        this.matricula = matricula;
        this.lotacao = lotacao;
        this.quilometragem = quantidadeKm;

        this.inicio = inicio;
        this.fim = fim;
        this.diarias = diarias;
        this.almocos = almocos;
        this.valorTotalKm = valorTotalKm;
        this.valorTotalDiaria = valorTotalDiaria;
        this.valorTotal = valorTotal;
        this.cidades = cidades;
        this.valorDiaria = valorDiaria;
        this.valorKm = valorKm;
        this.dataCadastro = dataCadastro;
        this.numMemo = numMemo;
        this.memorando = memorando;
    }

    public String getIdOrdemServico() {
        return idOrdemServico;
    }

    public void setIdOrdemServico(String idOrdemServico) {
        this.idOrdemServico = idOrdemServico;
    }

    public String getNomeFiscal() {
        return nomeFiscal;
    }

    public void setNomeFiscal(String nomeFiscal) {
        this.nomeFiscal = nomeFiscal;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getLotacao() {
        return lotacao;
    }

    public void setLotacao(String lotacao) {
        this.lotacao = lotacao;
    }

    public String getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(String quantidadeKm) {
        this.quilometragem = quantidadeKm;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public void setCidades(String cidades) {
        this.cidades = cidades;
    }

    public String getDiarias() {
        return diarias;
    }

    public void setDiarias(String diarias) {
        this.diarias = diarias;
    }

    public String getAlmocos() {
        return almocos;
    }

    public void setAlmocos(String almocos) {
        this.almocos = almocos;
    }

    public String getValorTotalKm() {
        return valorTotalKm;
    }

    public void setValorTotalKm(String valorTotalKm) {
        this.valorTotalKm = valorTotalKm;
    }

    public String getValorDiarias() {
        return valorTotalDiaria;
    }

    public void setValorDiarias(String valorDiarias) {
        this.valorTotalDiaria = valorDiarias;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getCidades() {
        return cidades;

    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getValorKm() {
        return valorKm;
    }

    public void setValorKm(String valorKm) {
        this.valorKm = valorKm;
    }

    public String getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(String valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public String getValorTotalDiaria() {
        return valorTotalDiaria;
    }

    public void setValorTotalDiaria(String valorTotalDiaria) {
        this.valorTotalDiaria = valorTotalDiaria;
    }

    public String getNumMemo() {
        return numMemo;
    }

    public void setNumMemo(String numMemo) {
        this.numMemo = numMemo;
    }

    public Boolean getMemorando() {
        return memorando;
    }

    public void setMemorando(Boolean memorando) {
        this.memorando = memorando;
    }
    
    
    

    
    
    
}
