package creago.dfisc.afg.sifis.planejamento.facade;

import creago.dfisc.afg.sifis.planejamento.dao.BairroDAO;
import creago.dfisc.afg.sifis.planejamento.entities.Bairro;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Tiago Borges Pereira
 */
public class BairroFacade implements Serializable {

    private final BairroDAO dao = new BairroDAO();

    public void create(Bairro bairro) {
        dao.persist(bairro);
    }

    public void update(Bairro bairro) {
        dao.merge(bairro);
    }

    public Bairro find(Long entityId) {
        Bairro bairro = dao.getById(entityId);
        return bairro;
    }

    public List<Bairro> listAll() {
        List<Bairro> result = dao.findAll();
        return result;
    }

    public void delete(Bairro bairro) {
        dao.removeById(bairro.getIdbairro());
    }
}
