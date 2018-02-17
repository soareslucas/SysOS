package creago.dfisc.afg.sifis.security.facade;

import creago.dfisc.afg.sifis.security.dao.RoleDAO;
import creago.dfisc.afg.sifis.security.entities.Role;
import java.util.List;

/**
 *
 * @author Tiago Borges Pereira
 */
public class RoleFacade {

    private final RoleDAO dao = new RoleDAO();

    public void create(Role role) {
        dao.persist(role);
    }

    public void update(Role role) {
        dao.merge(role);
    }

    public Role find(Long entityId) {
        Role role = dao.getById(entityId);
        return role;
    }

    public List<Role> listAll() {
        List<Role> result = dao.findAll();
        return result;
    }

    public void delete(Role role) {
        dao.removeById(role.getIdrole());
    }
}
