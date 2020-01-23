package com.defunciones.controladores;

import com.defunciones.dao.UsuarioDAO;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

@ManagedBean
@RequestScoped
public class CambiarContraseniaControlador {

    private String contraAnterior;
    private String contraNueva;
    private String contraNuevaConfirmar;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public CambiarContraseniaControlador() {
    }

    public void cambiarContrasenia() {
        if (!contraNueva.equals(contraNuevaConfirmar)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contrase\u00f1as no coinciden", "Las contrase\u00f1as no coinciden"));
            return;
        }
        String resp = usuarioDAO.cambiarContraseña("1723232323", contraAnterior, contraNueva);
        if ("NO-COINCIDE".equals(resp)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contrase\u00f1a actual no es correcta", "La contrase\u00f1a actual no es correcta"));
        }
        if ("ERROR".equals(resp)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al cambiar contrase\u00f1a", "Error al cambiar contrase\u00f1a"));
        }
        if ("OK".equals(resp)) {
//            FacesContext.getCurrentInstance().addMessage(null,
//                new FacesMessage(FacesMessage.SEVERITY_INFO, "La contrase\u00f1a se actualiz\u00f3 correctamente", "La contrase\u00f1a se actualiz\u00f3 correctamente"));
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('confirm').show();");
        }
    }

    public String getContraAnterior() {
        return contraAnterior;
    }

    public void setContraAnterior(String contraAnterior) {
        this.contraAnterior = contraAnterior;
    }

    public String getContraNueva() {
        return contraNueva;
    }

    public void setContraNueva(String contraNueva) {
        this.contraNueva = contraNueva;
    }

    public String getContraNuevaConfirmar() {
        return contraNuevaConfirmar;
    }

    public void setContraNuevaConfirmar(String contraNuevaConfirmar) {
        this.contraNuevaConfirmar = contraNuevaConfirmar;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

}
