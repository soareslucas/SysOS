package creago.dfisc.afg.sifis.planejamento.dao;

import creago.dfisc.afg.sifis.planejamento.entities.Inspetoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tiago Borges Pereira
 */
public class InspetoriaDAO {

    protected EntityManager entityManager;

    public InspetoriaDAO() {
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

    public Inspetoria getById(final Long id) {
        return entityManager.find(Inspetoria.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Inspetoria> findAll() {
        return entityManager.createQuery("FROM " + Inspetoria.class.getName() + " i ORDER BY i.nome")
                .getResultList();
    }

    public void persist(Inspetoria inspetoria) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(inspetoria);
            entityManager.getTransaction().commit();
            entityManager.refresh(inspetoria);
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Inspetoria inspetoria) {
        try {
            entityManager.getTransaction().begin();
            Inspetoria persisted = getById(inspetoria.getIdinspetoria());
            persisted.setNome(inspetoria.getNome());
            entityManager.merge(persisted);
            entityManager.getTransaction().commit();
            entityManager.refresh(inspetoria);
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Inspetoria inspetoria) {
        try {
            entityManager.getTransaction().begin();
            inspetoria = entityManager.find(Inspetoria.class, inspetoria.getIdinspetoria());
            entityManager.remove(inspetoria);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final Long id) {
        try {
            Inspetoria inspetoria = getById(id);
            remove(inspetoria);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
