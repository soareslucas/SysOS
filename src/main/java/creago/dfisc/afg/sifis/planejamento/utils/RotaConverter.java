package creago.dfisc.afg.sifis.planejamento.utils;

import creago.dfisc.afg.sifis.planejamento.entities.Rota;
import creago.dfisc.afg.sifis.planejamento.facade.RotaFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Tiago Borges Pereira
 */
@FacesConverter("rotaConverter")
public class RotaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Rota rota = new Rota();
        RotaFacade rotaFacade = new RotaFacade();
        rota = rotaFacade.find(Long.parseLong(value));
        return rota;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Rota rota = (Rota) value;
        String codigo = rota.getIdrota() + "";
        return codigo;
    }

}
