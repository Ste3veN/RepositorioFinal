package com.defunciones.controladores;

import com.defunciones.dao.UsuarioDAO;
import com.defunciones.utils.SessionUtils;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@RequestScoped
public class Login {

    private String usuario;
    private String contrasenia;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Login() {
    }

    public String iniciarSesion() {
        boolean valid = usuarioDAO.validarUsuarioContraseña(usuario, contrasenia);
        if (valid) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("usuario", usuario);
            session.setAttribute("rol", "cliente");
            return "/views/home/home.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Usuario o contrase\u00f1a inv\u00e1lida",
                            "Usuario o contrase\u00f1a inv\u00e1lida"));
            return "";
        }
    }

    public String cerrarSesion() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "/views/index.xhtml?faces-redirect=true";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

}
