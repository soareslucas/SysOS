package creago.dfisc.afg.sifis.planejamento.facade;

import creago.dfisc.afg.sifis.planejamento.dao.ViagemDAO;
import creago.dfisc.afg.sifis.planejamento.entities.Ordem;
import creago.dfisc.afg.sifis.planejamento.entities.Viagem;
import java.util.Date;
import java.util.List;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;

/**
 *
 * @author Tiago Borges Pereira
 */
public class ViagemFacade {

    private final ViagemDAO dao = new ViagemDAO();

    public void create(Viagem viagem) {
        dao.persist(viagem);
    }

    public void update(Viagem viagem) {
        dao.merge(viagem);
    }

    public Viagem find(Long entityId) {
        Viagem viagem = dao.getById(entityId);
        return viagem;
    }

    public List<Viagem> listAll() {
        List<Viagem> result = dao.findAll();
        return result;
    }

    public void delete(Viagem viagem) {
        dao.removeById(viagem.getIdviagem());
    }

    public List<DefaultScheduleEvent> getViagens() {
        return dao.getViagens();
    }

    public List<Viagem> getViagensPorPeriodo(String inicio, String fim) {
        return dao.getViagensPeriodo(inicio, fim);
    }

    public String consultaId() {
        return dao.consultaId();
    }

    public void createOrdem(Ordem ordem) {
        dao.persistOrdem(ordem);
    }

}
