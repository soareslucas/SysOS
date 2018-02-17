package creago.dfisc.afg.sifis.planejamento.facade;

import creago.dfisc.afg.sifis.planejamento.dao.RotaDAO;
import creago.dfisc.afg.sifis.planejamento.entities.Rota;
import java.util.List;

/**
 *
 * @author Tiago Borges Pereira
 */
public class RotaFacade {

    private final RotaDAO dao = new RotaDAO();

    public void create(Rota rota) {
        dao.persist(rota);
    }

    public void update(Rota rota) {
        dao.merge(rota);
    }

    public Rota find(Long entityId) {
        Rota rota = dao.getById(entityId);
        return rota;
    }

    public List<Rota> listAll() {
        List<Rota> result = dao.findAll();
        return result;
    }

    public void delete(Rota rota) {
        dao.removeById(rota.getIdrota());
    }
}
