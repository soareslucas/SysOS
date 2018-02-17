package creago.dfisc.afg.sifis.planejamento.facade;

import creago.dfisc.afg.sifis.planejamento.dao.InspetoriaDAO;
import creago.dfisc.afg.sifis.planejamento.entities.Inspetoria;
import java.util.List;

/**
 *
 * @author Tiago Borges Pereira
 */
public class InspetoriaFacade {

    private final InspetoriaDAO dao = new InspetoriaDAO();

    public void create(Inspetoria inspetoria) {
        dao.persist(inspetoria);
    }

    public void update(Inspetoria inspetoria) {
        dao.merge(inspetoria);
    }

    public Inspetoria find(Long entityId) {
        Inspetoria inspetoria = dao.getById(entityId);
        return inspetoria;
    }

    public List<Inspetoria> listAll() {
        List<Inspetoria> result = dao.findAll();
        return result;
    }

    public void delete(Inspetoria inspetoria) {
        dao.removeById(inspetoria.getIdinspetoria());
    }
}
