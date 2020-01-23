package com.defunciones.admin.controladores;

import com.defunciones.dao.UsuarioDAO;
import com.defunciones.entidades.Auditoria;
import com.defunciones.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@RequestScoped
public class DatosAdminControlador {
    
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private List<Auditoria> auditorias = new ArrayList<Auditoria>();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public DatosAdminControlador() {
    }
    
    @PostConstruct
    public void init() {
        this.usuarios = usuarioDAO.recuperarTodosUsuarios();
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Auditoria> getAuditorias() {
        return auditorias;
    }

    public void setAuditorias(List<Auditoria> auditorias) {
        this.auditorias = auditorias;
    }
    
}
