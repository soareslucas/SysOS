package creago.dfisc.afg.sifis.planejamento.dao;

import creago.dfisc.afg.sifis.planejamento.entities.Cidade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tiago Borges Pereira
 */
public class CidadeDAO {

    protected EntityManager entityManager;

    public CidadeDAO() {
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

    public Cidade getById(final Long id) {
        return entityManager.find(Cidade.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Cidade> findAll() {
        return entityManager.createQuery("FROM " + Cidade.class.getName() + " c ORDER BY c.inspetoria, c.nome ")
                .getResultList();
    }

    public void persist(Cidade cidade) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cidade);
            entityManager.getTransaction().commit();
            entityManager.refresh(cidade);
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Cidade cidade) {
        try {
            entityManager.getTransaction().begin();
            Cidade persisted = getById(cidade.getIdcidade());
            persisted.setNome(cidade.getNome());
            persisted.setInspetoria(cidade.getInspetoria());
            entityManager.merge(persisted);
            entityManager.getTransaction().commit();
            entityManager.refresh(cidade);
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Cidade cidade) {
        try {
            entityManager.getTransaction().begin();
            cidade = entityManager.find(Cidade.class, cidade.getIdcidade());
            entityManager.remove(cidade);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final Long id) {
        try {
            Cidade cidade = getById(id);
            remove(cidade);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
