package creago.dfisc.afg.sifis.planejamento.utils;

import creago.dfisc.afg.sifis.planejamento.entities.Cidade;
import creago.dfisc.afg.sifis.planejamento.entities.Feriado;
import creago.dfisc.afg.sifis.planejamento.entities.Inspetoria;
import creago.dfisc.afg.sifis.planejamento.entities.Jurisdicao;
import creago.dfisc.afg.sifis.planejamento.facade.CidadeFacade;
import creago.dfisc.afg.sifis.planejamento.facade.FeriadoFacade;
import creago.dfisc.afg.sifis.planejamento.facade.InspetoriaFacade;
import creago.dfisc.afg.sifis.planejamento.facade.JurisdicaoFacade;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author creago
 */
public class Main {

    public static void main(String[] args) {

        CidadeFacade cf = new CidadeFacade();
        Cidade c = cf.find(8L);
        FeriadoFacade ff = new FeriadoFacade();
        Feriado f = ff.find(1L);
        
        f.getCidades().add(c);
        ff.update(f);
    }
}
