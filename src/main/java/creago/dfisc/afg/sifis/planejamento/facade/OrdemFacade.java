package creago.dfisc.afg.sifis.planejamento.facade;

import creago.dfisc.afg.sifis.planejamento.dao.OrdemDAO;
import creago.dfisc.afg.sifis.planejamento.dao.ViagemDAO;
import creago.dfisc.afg.sifis.planejamento.entities.Ordem;
import creago.dfisc.afg.sifis.planejamento.entities.Viagem;
import java.util.Date;
import java.util.List;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;

/**
 *
 * @author Lucas
 */
public class OrdemFacade {

    private final OrdemDAO dao = new OrdemDAO();

    public void create(Ordem ordem) {
        dao.persist(ordem);
    }

    public void update(Ordem ordem) {
        dao.merge(ordem);
    }

    public Ordem find(Long entityId) {
        Ordem ordem = dao.getById(entityId);
        return ordem;
    }

    public List<Ordem> listAll() {
        List<Ordem> result = dao.findAll();
        return result;
    }

    public void delete(Ordem ordem) {
        dao.removeById(ordem.getIdordem());
    }
    
    public List<DefaultScheduleEvent> getOrdens(){
        return dao.getOrdens();
    }
    
    
    public List<Ordem> getOrdensPorPeriodo(String inicio, String fim){
        return dao.getOrdensPeriodo(inicio, fim);
    }
    
    public String consultaId(){
        return dao.consultaId();
    }
    
}
