package creago.dfisc.afg.sifis.planejamento.dao;

import creago.dfisc.afg.sifis.planejamento.entities.Ordem;
import creago.dfisc.afg.sifis.planejamento.entities.Viagem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;

/**
 *
 * @author Tiago Borges Pereira
 */
public class ViagemDAO {

    protected EntityManager entityManager;

    public ViagemDAO() {
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

    public Viagem getById(final Long id) {
        return entityManager.find(Viagem.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Viagem> findAll() {
        return entityManager.createQuery("FROM " + Viagem.class.getName() + " v ORDER BY v.inicio desc")
                .getResultList();
    }

    public List<DefaultScheduleEvent> getViagens() {

        List<DefaultScheduleEvent> events = new ArrayList<>();
        List<Viagem> viagens = findAll();

        for (Viagem v : viagens) {
            DefaultScheduleEvent evt = new DefaultScheduleEvent(v.getRota().getNome() + " - "
                    + v.getFiscal().getSigla(), v.getInicio(), v.getFim(), v);
            evt.setAllDay(true);
            evt.setStyleClass("categoria-" + v.getCategoria().getIdcategoria());
            events.add(evt);
        }
        return events;
    }

    public void persist(Viagem viagem) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(viagem);
            entityManager.getTransaction().commit();
            entityManager.refresh(viagem);
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Viagem viagem) {
        try {
            entityManager.getTransaction().begin();
            Viagem persisted = getById(viagem.getIdviagem());
            persisted.setInicio(viagem.getInicio());
            persisted.setFim(viagem.getFim());
            persisted.setFiscal(viagem.getFiscal());
            persisted.setCategoria(viagem.getCategoria());
            persisted.setRota(viagem.getRota());
            entityManager.merge(persisted);
            entityManager.getTransaction().commit();
            entityManager.refresh(viagem);
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Viagem viagem) {
        try {
            entityManager.getTransaction().begin();
            viagem = entityManager.find(Viagem.class, viagem.getIdviagem());
            entityManager.remove(viagem);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final Long id) {
        try {
            Viagem viagem = getById(id);
            remove(viagem);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public List<Viagem> getViagensPeriodo(String inicio, String fim) {

        return entityManager.createQuery("FROM " + Viagem.class.getName() + " v where v.inicio >= '" + inicio + "' and v.fim <= '" + fim + "'")
                .getResultList();
    }

    public String consultaId() {
        String s = null;
        entityManager.getTransaction().begin();
        try {
            Query query = entityManager.createNativeQuery("SELECT CAST(CONCAT"
                    + "(RIGHT(CONCAT('0000',max(substring(identificador,1,4)) + 1),4),'/',"
                    + "YEAR(NOW())) AS CHAR) FROM ordem o WHERE"
                    + " substring(identificador,6) = YEAR(NOW())");
            s = (String) query.getSingleResult();
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return s;
    }

    public void persistOrdem(Ordem ordem) {
        try {
          EntityManager  em = getEntityManager();
            EntityTransaction  tx = em.getTransaction();
            tx.begin();
            em.persist(ordem);
            tx.commit();
            em.refresh(ordem);
        } catch (Exception ex) {
            ex.printStackTrace();
            EntityManager  em = getEntityManager();
            em.getTransaction().rollback();
        }
    }


}
