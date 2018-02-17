/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creago.dfisc.afg.sifis.planejamento.beans;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author creago
 */
@ManagedBean
@ApplicationScoped
public class ValoresBean {
    
    
    private double valorKm;
    private double valorDiaria;    
    
    public ValoresBean(){    
        
        this.valorDiaria = 190.0;
        this.valorKm = 0.3;
    } 

    public double getValorKm() {
        return valorKm;
    }

    public void setValorKm(double valorKm) {
        this.valorKm = valorKm;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }
    
    
    
    
}
