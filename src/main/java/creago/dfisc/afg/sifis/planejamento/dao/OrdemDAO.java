package creago.dfisc.afg.sifis.planejamento.dao;

import creago.dfisc.afg.sifis.planejamento.entities.Ordem;
import creago.dfisc.afg.sifis.planejamento.entities.Viagem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;

/**
 *
 * @author Lucas
 */
public class OrdemDAO {

    protected EntityManager entityManager;

    public OrdemDAO() {
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

    public Ordem getById(final Long id) {
        return entityManager.find(Ordem.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Ordem> findAll() {
    
        return entityManager.createQuery("FROM " + Ordem.class.getName() + " o ORDER BY o.identificador desc")
                .getResultList();
    }

    public List<DefaultScheduleEvent> getOrdens() {

        List<DefaultScheduleEvent> events = new ArrayList<>();
        List<Ordem> ordens = findAll();

        for (Ordem o : ordens) {
            DefaultScheduleEvent evt = new DefaultScheduleEvent( " - "
                    + o.getFiscal().getSigla(), o.getInicio(), o.getFim(), o);
            evt.setAllDay(true);
            evt.setStyleClass("categoria-" + o.getCategoria().getIdcategoria());
            events.add(evt);
        }
        return events;
    }

    public void persist(Ordem ordem) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(ordem);
            entityManager.getTransaction().commit();
            entityManager.refresh(ordem);
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Ordem ordem) {
        try {
            entityManager.getTransaction().begin();
            Ordem persisted = getById(ordem.getIdordem());
            persisted.setInicio(ordem.getInicio());
            persisted.setFim(ordem.getFim());
            persisted.setFiscal(ordem.getFiscal());
            persisted.setCategoria(ordem.getCategoria());
//            persisted.setRota(ordem.getRota());
            entityManager.merge(persisted);
            entityManager.getTransaction().commit();
            entityManager.refresh(ordem);
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Ordem ordem) {
        try {
            entityManager.getTransaction().begin();
            ordem = entityManager.find(Ordem.class, ordem.getIdordem());
            entityManager.remove(ordem);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final Long id) {
        try {
            Ordem ordem = getById(id);
            remove(ordem);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public List<Ordem> getOrdensPeriodo(String inicio, String fim) {
    

        return  entityManager.createQuery("FROM " + Ordem.class.getName() + " v where v.inicio >= '" +inicio+"' and v.fim <= '" + fim+"'")
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

}
