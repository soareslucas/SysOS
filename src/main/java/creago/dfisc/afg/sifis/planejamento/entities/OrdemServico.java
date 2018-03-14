/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creago.dfisc.afg.sifis.planejamento.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author creago
 */
public class OrdemServico {

    private String idOrdemServico;
    private String nomeFiscal;
    private Integer matricula;
    private String lotacao;
    private Integer quilometragem;
    private Date inicio;
    private Date fim;
    private Date dataCadastro;
    private String cidades;
    private Set<Jurisdicao> jurisdicoes;
    private Integer diarias;
    private Integer almocos;
    private Double valorTotalKm;
    private Double valorDiarias;
    private Double valorTotal;
    private String observacao;
    private Rota rota;
    private String valorKm;
    private String valorDiaria;

    public OrdemServico(String idOrdemServico, String nomeFiscal, Integer matricula, String lotacao, Integer quantidadeKm, Date inicio, Date fim, Date dataCadastro, Set<Jurisdicao> jurisdicoes, Integer diarias, Integer almocos, Double valorTotalKm, Double valorDiarias, Double valorTotal, String observacao, String cidades, String valorKm, String valorDiaria) {

        this.observacao = observacao;
        this.idOrdemServico = idOrdemServico;

        this.nomeFiscal = nomeFiscal;
        this.matricula = matricula;
        this.lotacao = lotacao;
        this.quilometragem = quantidadeKm;
        this.jurisdicoes = jurisdicoes;
        this.inicio = inicio;
        this.fim = fim;
        this.diarias = diarias;
        this.almocos = almocos;
        this.valorTotalKm = valorTotalKm;
        this.valorDiarias = valorDiarias;
        this.valorTotal = valorTotal;
        this.cidades = cidades;
        this.valorDiaria = valorDiaria;
        this.valorKm = valorKm;
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

    public Integer getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Integer quantidadeKm) {
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

    public Integer getDiarias() {
        return diarias;
    }

    public void setDiarias(Integer diarias) {
        this.diarias = diarias;
    }

    public Integer getAlmocos() {
        return almocos;
    }

    public void setAlmocos(Integer almocos) {
        this.almocos = almocos;
    }

    public Double getValorTotalKm() {
        return valorTotalKm;
    }

    public void setValorTotalKm(Double valorTotalKm) {
        this.valorTotalKm = valorTotalKm;
    }

    public Double getValorDiarias() {
        return valorDiarias;
    }

    public void setValorDiarias(Double valorDiarias) {
        this.valorDiarias = valorDiarias;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
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

}
