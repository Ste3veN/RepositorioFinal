package com.defunciones.controladores;

import com.defunciones.dao.UsuarioDAO;
import com.defunciones.entidades.Usuario;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;

@ManagedBean
@RequestScoped
public class Registro {

    private Usuario usuario = new Usuario();
    private String foto;
    private String contrasenia;
    private String contraseniaConfir;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Registro() {
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        InputStream stream = event.getFile().getInputstream();
        usuario.setFoto(event.getFile().getContents());
        foto = Base64.getEncoder().encodeToString(event.getFile().getContents());
    }

    public void registrarUsuario() {
        if (!contrasenia.equals(contraseniaConfir)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contrase\u00f1as no coinciden", "Las contrase\u00f1as no coinciden"));
            return;
        }
        usuario.setContrasenia(contrasenia);
        String resp = usuarioDAO.guardarUsuario(usuario);
        if ("OK".equals(resp)) {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('confirm').show();");
        }
        if ("ERROR".equals(resp)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear usuario", "Error al crear usuario"));
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getContraseniaConfir() {
        return contraseniaConfir;
    }

    public void setContraseniaConfir(String contraseniaConfir) {
        this.contraseniaConfir = contraseniaConfir;
    }

}
