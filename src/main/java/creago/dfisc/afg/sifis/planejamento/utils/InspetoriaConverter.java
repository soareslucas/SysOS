package creago.dfisc.afg.sifis.planejamento.utils;

import creago.dfisc.afg.sifis.planejamento.entities.Inspetoria;
import creago.dfisc.afg.sifis.planejamento.facade.InspetoriaFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Tiago Borges Pereira
 */
@FacesConverter("inspetoriaConverter")
public class InspetoriaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Inspetoria inspetoria = new Inspetoria();
        InspetoriaFacade inspetoriaFacade = new InspetoriaFacade();
        inspetoria = inspetoriaFacade.find(Long.parseLong(value));
        return inspetoria;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Inspetoria inspetoria = (Inspetoria) value;
        String codigo = inspetoria.getIdinspetoria() + "";
        return codigo;
    }

}
