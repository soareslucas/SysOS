package creago.dfisc.afg.sifis.security.dao;

import creago.dfisc.afg.sifis.security.entities.Role;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tiago Borges Pereira
 */
public class RoleDAO {

    protected EntityManager entityManager;

    public RoleDAO() {
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

    public Role getById(final Long id) {
        return entityManager.find(Role.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Role> findAll() {
        return entityManager.createQuery("FROM " + Role.class.getName() + " r ORDER BY r.description")
                .getResultList();
    }

    public void persist(Role role) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(role);
            entityManager.getTransaction().commit();
            entityManager.refresh(role);
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Role role) {
        try {
            entityManager.getTransaction().begin();
            Role persisted = getById(role.getIdrole());
            persisted.setDescription(role.getDescription());
            entityManager.merge(persisted);
            entityManager.getTransaction().commit();
            entityManager.refresh(role);
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Role role) {
        try {
            entityManager.getTransaction().begin();
            role = entityManager.find(Role.class, role.getIdrole());
            entityManager.remove(role);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final Long id) {
        try {
            Role role = getById(id);
            remove(role);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
