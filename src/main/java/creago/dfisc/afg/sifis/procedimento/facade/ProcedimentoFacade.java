package creago.dfisc.afg.sifis.procedimento.facade;

import creago.dfisc.afg.sifis.procedimento.dao.ProcedimentoDAO;
import creago.dfisc.afg.sifis.procedimento.entities.Procedimento;
import java.util.List;

/**
 *
 * @author Tiago Borges Pereira
 */
public class ProcedimentoFacade {
    private final ProcedimentoDAO dao = new ProcedimentoDAO();
    
    public void create(Procedimento procedimento) {
        dao.persist(procedimento);
    }

    public void update(Procedimento procedimento) {
        dao.merge(procedimento);
    }

    public Procedimento find(Long entityId) {
        Procedimento procedimento = dao.getById(entityId);
        return procedimento;
    }

    public List<Procedimento> listAll() {
        List<Procedimento> result = dao.findAll();
        return result;
    }

    public void delete(Procedimento procedimento) {
        dao.removeById(procedimento.getIdprocedimento());
    }
    
    public String consultaId(){
        return dao.consultaId();
    }
}
