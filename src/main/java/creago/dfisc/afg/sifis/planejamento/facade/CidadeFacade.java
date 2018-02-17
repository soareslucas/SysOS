package creago.dfisc.afg.sifis.planejamento.facade;

import creago.dfisc.afg.sifis.planejamento.dao.CidadeDAO;
import creago.dfisc.afg.sifis.planejamento.entities.Cidade;
import java.util.List;

/**
 *
 * @author Tiago Borges Pereira
 */
public class CidadeFacade {

    private final CidadeDAO dao = new CidadeDAO();

    public void create(Cidade cidade) {
        dao.persist(cidade);
    }

    public void update(Cidade cidade) {
        dao.merge(cidade);
    }

    public Cidade find(Long entityId) {
        Cidade cidade = dao.getById(entityId);
        return cidade;
    }

    public List<Cidade> listAll() {
        List<Cidade> result = dao.findAll();
        return result;
    }

    public void delete(Cidade cidade) {
        dao.removeById(cidade.getIdcidade());
    }
}
