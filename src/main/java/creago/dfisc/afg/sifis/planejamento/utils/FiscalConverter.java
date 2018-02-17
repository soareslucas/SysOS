package creago.dfisc.afg.sifis.planejamento.utils;

import creago.dfisc.afg.sifis.planejamento.entities.Fiscal;
import creago.dfisc.afg.sifis.planejamento.facade.FiscalFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Tiago Borges Pereira
 */
@FacesConverter("ficalConverter")
public class FiscalConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Fiscal fiscal = new Fiscal();
        FiscalFacade fiscalFacade = new FiscalFacade();
        fiscal = fiscalFacade.find(Long.parseLong(value));
        return fiscal;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Fiscal fiscal = (Fiscal) value;
        String codigo = fiscal.getIdfiscal() + "";
        return codigo;
    }
}
