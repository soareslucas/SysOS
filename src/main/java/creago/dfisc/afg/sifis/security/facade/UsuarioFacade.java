package creago.dfisc.afg.sifis.security.facade;

import creago.dfisc.afg.sifis.security.dao.UsuarioDAO;
import creago.dfisc.afg.sifis.security.entities.Usuario;
import java.util.List;

/**
 *
 * @author Tiago Borges Pereira
 */
public class UsuarioFacade {

    private final UsuarioDAO dao = new UsuarioDAO();

    public void create(Usuario usuario) {
        dao.persist(usuario);
    }

    public void update(Usuario usuario) {
        dao.merge(usuario);
    }

    public Usuario find(Long entityId) {
        Usuario usuario = dao.getById(entityId);
        return usuario;
    }
    
    public Usuario find(String username) {
        Usuario usuario = dao.getByUsername(username);
        return usuario;
    }

    public List<Usuario> listAll() {
        List<Usuario> result = dao.findAll();
        return result;
    }

    public void delete(Usuario usuario) {
        dao.removeById(usuario.getIdusuario());
    }
}
