package creago.dfisc.afg.sifis.planejamento.facade;

import creago.dfisc.afg.sifis.planejamento.dao.FeriasDAO;
import creago.dfisc.afg.sifis.planejamento.entities.Ferias;
import java.util.List;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;

/**
 *
 * @author Tiago Borges Pereira
 */
public class FeriasFacade {

    private final FeriasDAO dao = new FeriasDAO();

    public void create(Ferias ferias) {
        dao.persist(ferias);
    }

    public void update(Ferias ferias) {
        dao.merge(ferias);
    }

    public Ferias find(Long entityId) {
        Ferias ferias = dao.getById(entityId);
        return ferias;
    }

    public List<Ferias> listAll() {
        List<Ferias> result = dao.findAll();
        return result;
    }

    public void delete(Ferias ferias) {
        dao.removeById(ferias.getIdferias());
    }

    public List<DefaultScheduleEvent> getFerias() {
        return dao.getFerias();
    }
}
