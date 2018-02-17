package creago.dfisc.afg.sifis.planejamento.facade;

import creago.dfisc.afg.sifis.planejamento.dao.JurisdicaoDAO;
import creago.dfisc.afg.sifis.planejamento.entities.Jurisdicao;
import java.util.List;

/**
 *
 * @author Tiago Borges Pereira
 */
public class JurisdicaoFacade {

    private final JurisdicaoDAO dao = new JurisdicaoDAO();

    public void create(Jurisdicao jurisdicao) {
        dao.persist(jurisdicao);
    }

    public void update(Jurisdicao jurisdicao) {
        dao.merge(jurisdicao);
    }

    public Jurisdicao find(Long entityId) {
        Jurisdicao jurisdicao = dao.getById(entityId);
        return jurisdicao;
    }

    public List<Jurisdicao> listAll() {
        List<Jurisdicao> result = dao.findAll();
        return result;
    }

    public void delete(Jurisdicao jurisdicao) {
        dao.removeById(jurisdicao.getIdjurisdicao());
    }
}
