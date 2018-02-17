package creago.dfisc.afg.sifis.planejamento.dao;

import creago.dfisc.afg.sifis.planejamento.entities.Bairro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tiago Borges Pereira
 */
public class BairroDAO {

    protected EntityManager entityManager;

    public BairroDAO() {
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

    public Bairro getById(final Long id) {
        return entityManager.find(Bairro.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Bairro> findAll() {
        return entityManager.createQuery("FROM " + Bairro.class.getName() + " b ORDER BY b.nome")
                .getResultList();
    }

    public void persist(Bairro bairro) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(bairro);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Bairro bairro) {
        try {
            entityManager.getTransaction().begin();
            Bairro persisted = getById(bairro.getIdbairro());
            persisted.setNome(bairro.getNome());
            persisted.setJurisdicao(bairro.getJurisdicao());
            entityManager.merge(persisted);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Bairro bairro) {
        try {
            entityManager.getTransaction().begin();
            bairro = entityManager.find(Bairro.class, bairro.getIdbairro());
            entityManager.remove(bairro);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final Long id) {
        try {
            Bairro bairro = getById(id);
            remove(bairro);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
