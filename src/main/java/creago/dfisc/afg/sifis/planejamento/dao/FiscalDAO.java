package creago.dfisc.afg.sifis.planejamento.dao;

import creago.dfisc.afg.sifis.planejamento.entities.Fiscal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tiago Borges Pereira
 */
public class FiscalDAO {

    protected EntityManager entityManager;

    public FiscalDAO() {
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

    public Fiscal getById(final Long id) {
        return entityManager.find(Fiscal.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Fiscal> findAll() {
        return entityManager.createQuery("FROM " + Fiscal.class.getName() + " fis ORDER BY fis.nome, fis.sobrenome")
                .getResultList();
    }

    public void persist(Fiscal fiscal) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(fiscal);
            entityManager.getTransaction().commit();
            entityManager.refresh(fiscal);
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Fiscal fiscal) {
        try {
            entityManager.getTransaction().begin();
            Fiscal persisted = getById(fiscal.getIdfiscal());
            persisted.setNome(fiscal.getNome());
            persisted.setSobrenome(fiscal.getSobrenome());
            persisted.setMatricula(fiscal.getMatricula());
            persisted.setSigla(fiscal.getSigla());
            persisted.setIsAtivo(fiscal.getIsAtivo());
            persisted.setInspetoria(fiscal.getInspetoria());
            entityManager.merge(persisted);
            entityManager.getTransaction().commit();
            entityManager.refresh(fiscal);
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Fiscal fiscal) {
        try {
            entityManager.getTransaction().begin();
            fiscal = entityManager.find(Fiscal.class, fiscal.getIdfiscal());
            entityManager.remove(fiscal);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final Long id) {
        try {
            Fiscal fiscal = getById(id);
            remove(fiscal);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
