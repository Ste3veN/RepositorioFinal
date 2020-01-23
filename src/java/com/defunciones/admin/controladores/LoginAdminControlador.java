package com.defunciones.admin.controladores;

import com.defunciones.utils.SessionUtils;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@RequestScoped
public class LoginAdminControlador {

    private String usuario;
    private String contrasenia;

    public LoginAdminControlador() {
    }

    public String iniciarSesion() {
        if ("admin".equals(usuario) && "admin".equals(contrasenia)) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("usuario", usuario);
            session.setAttribute("rol", "admin");
            return "/views/admin/datos.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Usuario o contrase\u00f1a inv\u00e1lida",
                            "Usuario o contrase\u00f1a inv\u00e1lida"));
            return "";
        }
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
