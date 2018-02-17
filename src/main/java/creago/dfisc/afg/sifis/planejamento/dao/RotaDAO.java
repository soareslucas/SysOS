package creago.dfisc.afg.sifis.planejamento.dao;

import creago.dfisc.afg.sifis.planejamento.entities.Rota;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tiago Borges Pereira
 */
public class RotaDAO {

    protected EntityManager entityManager;

    public RotaDAO() {
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

    public Rota getById(final Long id) {
        return entityManager.find(Rota.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Rota> findAll() {
        return entityManager.createQuery("FROM " + Rota.class.getName() + " r ORDER BY r.inspetoria, r.nome")
                .getResultList();
    }

    public void persist(Rota rota) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(rota);
            entityManager.getTransaction().commit();
            entityManager.refresh(rota);
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Rota rota) {
        try {
            entityManager.getTransaction().begin();
            Rota persisted = getById(rota.getIdrota());
            persisted.setNome(rota.getNome());
            persisted.setAlmoco(rota.getAlmoco());
            persisted.setCriterioRevisita(rota.getCriterioRevisita());
            persisted.setDiaria(rota.getDiaria());
            persisted.setQuilometragem(rota.getQuilometragem());
            persisted.setInspetoria(rota.getInspetoria());
            entityManager.merge(persisted);
            entityManager.getTransaction().commit();
            entityManager.refresh(rota);
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Rota rota) {
        try {
            entityManager.getTransaction().begin();
            rota = entityManager.find(Rota.class, rota.getIdrota());
            entityManager.remove(rota);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final Long id) {
        try {
            Rota rota = getById(id);
            remove(rota);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
