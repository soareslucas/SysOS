package creago.dfisc.afg.sifis.procedimento.entities;
// Generated 20/02/2015 15:44:23 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Transient;

/**
 * Procedimento generated by hbm2java
 */
public class Procedimento  implements java.io.Serializable {


     private Long idprocedimento;
     private String identificador;
     private String tipo;
     private String assunto;
     private String descricao;
     private String evidencia;
     private Date inicio;
     private Date fim;
     private boolean vigente;

    public Procedimento() {
    }

    public Procedimento(String identificador, String tipo, String assunto, String descricao, String evidencia, Date inicio, Date fim) {
       this.identificador = identificador;
       this.tipo = tipo;
       this.assunto = assunto;
       this.descricao = descricao;
       this.evidencia = evidencia;
       this.inicio = inicio;
       this.fim = fim;
    }
   
    public Long getIdprocedimento() {
        return this.idprocedimento;
    }
    
    public void setIdprocedimento(Long idprocedimento) {
        this.idprocedimento = idprocedimento;
    }
    public String getIdentificador() {
        return this.identificador;
    }
    
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getAssunto() {
        return this.assunto;
    }
    
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getEvidencia() {
        return this.evidencia;
    }
    
    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
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

    @Transient
    public boolean isVigente() {
        Date dataAtual = new Date();
        if (fim == null) {
            return inicio.before(dataAtual);
        } else {
            return inicio.before(dataAtual) && fim.after(dataAtual);
        }
    }

    public void setVigencia(boolean vigencia) {
        this.vigente = vigencia;
    }




}


