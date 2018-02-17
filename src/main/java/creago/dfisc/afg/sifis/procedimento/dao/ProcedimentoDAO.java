package creago.dfisc.afg.sifis.procedimento.dao;

import creago.dfisc.afg.sifis.procedimento.entities.Procedimento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.hibernate.HibernateException;

/**
 *
 * @author Tiago Borges Pereira
 */
public class ProcedimentoDAO {

    protected EntityManager entityManager;

    public ProcedimentoDAO() {
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

    public Procedimento getById(final Long id) {
        return entityManager.find(Procedimento.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Procedimento> findAll() {
        return entityManager.createQuery("FROM " + Procedimento.class.getName() + " p ORDER BY p.idprocedimento desc")
                .getResultList();
    }

    public void persist(Procedimento procedimento) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(procedimento);
            entityManager.getTransaction().commit();
            entityManager.refresh(procedimento);
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Procedimento procedimento) {
        try {
            entityManager.getTransaction().begin();
            Procedimento persisted = getById(procedimento.getIdprocedimento());
            persisted.setIdentificador(procedimento.getIdentificador());
            persisted.setTipo(procedimento.getTipo());
            persisted.setAssunto(procedimento.getAssunto());
            persisted.setDescricao(procedimento.getDescricao());
            persisted.setEvidencia(procedimento.getEvidencia());
            persisted.setInicio(procedimento.getInicio());
            persisted.setFim(procedimento.getFim());
            entityManager.merge(persisted);
            entityManager.getTransaction().commit();
            entityManager.refresh(procedimento);
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Procedimento procedimento) {
        try {
            entityManager.getTransaction().begin();
            procedimento = entityManager.find(Procedimento.class, procedimento.getIdprocedimento());
            entityManager.remove(procedimento);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final Long id) {
        try {
            Procedimento procedimento = getById(id);
            remove(procedimento);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String consultaId() {
        String s = null;
        entityManager.getTransaction().begin();
        try {
            Query query = entityManager.createNativeQuery("SELECT CAST(CONCAT"
                    + "(RIGHT(CONCAT('0000',max(substring(identificador,1,4)) + 1),4),'/',"
                    + "YEAR(NOW())) AS CHAR) FROM procedimento p WHERE"
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
