package creago.dfisc.afg.sifis.planejamento.dao;

import creago.dfisc.afg.sifis.planejamento.entities.Jurisdicao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tiago Borges Pereira
 */
public class JurisdicaoDAO {

    protected EntityManager entityManager;

    public JurisdicaoDAO() {
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

    public Jurisdicao getById(final Long id) {
        return entityManager.find(Jurisdicao.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Jurisdicao> findAll() {
        return entityManager.createQuery("FROM " + Jurisdicao.class.getName() + " j ORDER BY j.nome")
                .getResultList();
    }

    public void persist(Jurisdicao jurisdicao) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(jurisdicao);
            entityManager.getTransaction().commit();
            entityManager.refresh(jurisdicao);
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Jurisdicao jurisdicao) {
        try {
            entityManager.getTransaction().begin();
            Jurisdicao persisted = getById(jurisdicao.getIdjurisdicao());
            persisted.setNome(jurisdicao.getNome());
            entityManager.merge(persisted);
            entityManager.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Jurisdicao jurisdicao) {
        try {
            entityManager.getTransaction().begin();
            jurisdicao = entityManager.find(Jurisdicao.class, jurisdicao.getIdjurisdicao());
            entityManager.remove(jurisdicao);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final Long id) {
        try {
            Jurisdicao jurisdicao = getById(id);
            remove(jurisdicao);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
