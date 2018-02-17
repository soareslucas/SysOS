package creago.dfisc.afg.sifis.planejamento.facade;

import creago.dfisc.afg.sifis.planejamento.dao.CategoriaDAO;
import creago.dfisc.afg.sifis.planejamento.entities.Categoria;
import java.util.List;

/**
 *
 * @author Tiago Borges Pereira
 */
public class CategoriaFacade {

    private final CategoriaDAO dao = new CategoriaDAO();

    public void create(Categoria categoria) {
        dao.persist(categoria);
    }

    public void update(Categoria categoria) {
        dao.merge(categoria);
    }

    public Categoria find(Long entityId) {
        Categoria categoria = dao.getById(entityId);
        return categoria;
    }

    public List<Categoria> listAll() {
        List<Categoria> result = dao.findAll();
        return result;
    }

    public void delete(Categoria categoria) {
        dao.removeById(categoria.getIdcategoria());
    }
}
