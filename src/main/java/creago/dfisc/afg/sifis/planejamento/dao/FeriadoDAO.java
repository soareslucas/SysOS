package creago.dfisc.afg.sifis.planejamento.dao;

import creago.dfisc.afg.sifis.planejamento.entities.Feriado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;

/**
 *
 * @author Tiago Borges Pereira
 */
public class FeriadoDAO {

    protected EntityManager entityManager;

    public FeriadoDAO() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("PlanejamentoPU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public Feriado getById(final Long id) {
        return entityManager.find(Feriado.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Feriado> findAll() {
        return entityManager.createQuery("FROM " + Feriado.class.getName() + " f ORDER BY f.data")
                .getResultList();
    }

    public List<DefaultScheduleEvent> getFeriados() {

        List<DefaultScheduleEvent> events = new ArrayList<>();
        List<Feriado> feriados = findAll();

        for (Feriado f : feriados) {
            DefaultScheduleEvent evt = new DefaultScheduleEvent(f.getNome(), f.getData(), f.getData());
            evt.setAllDay(true);
            if (f.getCidades() == null || f.getCidades().isEmpty()) {
                evt.setStyleClass("feriado");
            } else {
                evt.setStyleClass("feriadoMunicipal");
            }
            events.add(evt);
        }
        return events;
    }

    public void persist(Feriado feriado) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(feriado);
            entityManager.getTransaction().commit();
            entityManager.refresh(feriado);
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Feriado feriado) {
        try {
            entityManager.getTransaction().begin();
            Feriado persisted = getById(feriado.getIdferiado());
            persisted.setNome(feriado.getNome());
            persisted.setData(feriado.getData());
            persisted.setIsFixo(feriado.getIsFixo());
            entityManager.merge(persisted);
            entityManager.getTransaction().commit();
            entityManager.refresh(feriado);
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Feriado feriado) {
        try {
            entityManager.getTransaction().begin();
            feriado = entityManager.find(Feriado.class, feriado.getIdferiado());
            entityManager.remove(feriado);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final Long id) {
        try {
            Feriado feriado = getById(id);
            remove(feriado);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
