package creago.dfisc.afg.sifis.security.beans;

import creago.dfisc.afg.sifis.planejamento.beans.AbstractBean;
import creago.dfisc.afg.sifis.security.entities.Role;
import creago.dfisc.afg.sifis.security.entities.Usuario;
import creago.dfisc.afg.sifis.security.facade.RoleFacade;
import creago.dfisc.afg.sifis.security.facade.UsuarioFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Tiago Borges Pereira
 */
@ManagedBean
@SessionScoped
public class UsuarioBean extends AbstractBean implements Serializable {

    private Usuario usuarioAutenticado;
    private Usuario usuario;
    private List<Usuario> usuarios;
    private Usuario selectedUsuario;
    private List<Usuario> filteredUsuarios;

    private List<Role> roles;
    private List<String> selectedRoles;

    private String confirmacaoEmail;

    private UsuarioFacade usuarioFacade;
    private RoleFacade roleFacade;

    public UsuarioFacade getUsuarioFacade() {
        if (usuarioFacade == null) {
            usuarioFacade = new UsuarioFacade();
        }
        return usuarioFacade;
    }

    public RoleFacade getRoleFacade() {
        if (roleFacade == null) {
            roleFacade = new RoleFacade();
        }
        return roleFacade;
    }

    public String validaEmail() {
        if (!usuario.getEmail().equals(confirmacaoEmail)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmação de e-mail incorreta", null));
            return null;
        } else {
            String email = usuario.getEmail();
            String[] username = email.split(Pattern.quote("@"));
            usuario.setUsername(username[0]);
            senhaAleatoria();
            String retorno = create();
            //emailCadastro();
            return retorno;
        }
    }
    
    public void senhaAleatoria() {
        usuario.setPassword(PasswordGenerator.getRandomPassword(8));
    }
    
    public void emailCadastro() {
        SendMail mail = new SendMail();
        mail.sendMail("tiagopereira@crea-go.org.br", usuario.getEmail(),
                "Cadastro no Sistema de Informação da Fiscalização - SIFIS | Crea-GO",
                "Olá " + usuario.getNome() + ",\n"
                + "\n"
                + "Você acaba de ser cadastrado no novo sistema do Departamento "
                + "de Fiscalização. Confira os seus dados:\n"
                + "\n"
                + "Nome completo: " + usuario.getNome() + " " + usuario.getSobrenome() + "\n"
                + "Matrícula: " + usuario.getMatricula() + "\n"
                + "Usuário: " + usuario.getUsername() + "\n"
                + "Senha: " + usuario.getPassword() + "\n"
                + "\n"
                + "A sua senha atual foi gerada automaticamente pelo sistema mas você poderá alterá-la, caso desejar."
                + " Para acessar o sistema, clique no link a seguir: http://www.crea-go.org.br/Fiscalizacao/index.jsf \n"
                + "\n"
                + "Até mais,\n"
                + "Tiago B. Pereira\n");
    }
    
    public String create() {
        try {
            Iterator i = selectedRoles.iterator();
            while(i.hasNext()) {
                Long id = Long.parseLong((String) i.next());
                usuario.getRoles().add(getRoleFacade().find(id));
            }
            selectedUsuario = usuario;
            getUsuarioFacade().create(usuario);
            displayInfoMessageToUser("Usuário cadastrado com sucesso!");
            loadUsuarios();
            resetUsuario();
            return "usuarios-details";
        } catch (Exception e) {
            displayErrorMessageToUser("Erro ao cadastrar o novo usuário!");
            return "usuarios-new";
        }
    }

    public String newUsuario() {
        return "usuarios-new";
    }

    public List<Usuario> findAll() {
        loadUsuarios();
        return usuarios;
    }

    public String remove() {
        try {
            getUsuarioFacade().delete(selectedUsuario);
            displayInfoMessageToUser("Usuário excluído com sucesso!");
            loadUsuarios();
            resetUsuario();
        } catch (Exception e) {
            displayErrorMessageToUser("Erro ao excluir usuário!");
            e.printStackTrace();
        }
        return "usuarios-list";
    }

    //GETTERS AND SETTERS
    public Usuario getUsuarioAutenticado() {
        usuarioAutenticado = new Usuario();
        SecurityContext context = SecurityContextHolder.getContext();
        if (context instanceof SecurityContext) {
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication) {
                usuarioAutenticado.setUsername(authentication.getName());
            }
        }
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        usuarios = findAll();
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public List<Usuario> getFilteredUsuarios() {
        return filteredUsuarios;
    }

    public void setFilteredUsuarios(List<Usuario> filteredUsuarios) {
        this.filteredUsuarios = filteredUsuarios;
    }

    public String getConfirmacaoEmail() {
        if (confirmacaoEmail == null) {
            confirmacaoEmail = "";
        }
        return confirmacaoEmail;
    }

    public void setConfirmacaoEmail(String confirmacaoEmail) {
        this.confirmacaoEmail = confirmacaoEmail;
    }

    public List<Role> getRoles() {
        loadRoles();
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<String> getSelectedRoles() {
        if (selectedRoles == null) {
            selectedRoles = new ArrayList<>();
        }
        return selectedRoles;
    }

    public void setSelectedRoles(List<String> selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

    //LOADDERS AND RESETTERS
    private void loadUsuarios() {
        usuarios = getUsuarioFacade().listAll();
    }

    private void resetUsuario() {
        usuario = new Usuario();
    }

    private void loadRoles() {
        roles = getRoleFacade().listAll();
    }

}
