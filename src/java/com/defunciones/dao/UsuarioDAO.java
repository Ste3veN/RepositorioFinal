package com.defunciones.dao;

import com.defunciones.config.Conexion;
import com.defunciones.entidades.Auditoria;
import com.defunciones.entidades.Usuario;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO extends Conexion implements Serializable {

    public String guardarUsuario(Usuario usuario) {

        Connection con = getConexion();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("INSERT INTO usuario (cedula, nombre, correo, contrasenia, foto) "
                    + "values (?, ?, ?, ?, ?)");
            ps.setString(1, usuario.getCedula());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getContrasenia());
            ps.setBytes(5, usuario.getFoto());
            ps.executeUpdate();
            con.close();
            ps.close();
            return "OK";
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, "Error insertando usuario", ex);
            return "ERROR";
        }

    }

    public Usuario recuperarUsuarioPorCorreo(String correo) {
        Connection con = getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario = new Usuario();
        try {
            ps = con.prepareStatement("SELECT * FROM usuario where correo = ?");
            ps.setString(1, correo);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario.setId(rs.getLong("id"));
                usuario.setCedula(rs.getString("cedula"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasenia(rs.getString("contrasenia"));
                usuario.setFoto(rs.getBytes("foto"));
            }
            con.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, "Error insertando usuario", ex);
        }
        return usuario;
    }

    public boolean validarUsuarioContraseña(String usuario, String contrasenia) {
        Connection con = getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean resultado;
        try {
            ps = con.prepareStatement("SELECT * FROM usuario WHERE correo = ? AND contrasenia = ?");
            ps.setString(1, usuario);
            ps.setString(2, contrasenia);
            rs = ps.executeQuery();
            if (rs.next()) {
                resultado = true;
            } else {
                resultado = false;
            }
            con.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, "Error validando usuario", ex);
            resultado = false;
        }
        return resultado;
    }
    
    public boolean validarExisteCorreo(String correo) {
        Connection con = getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean resultado;
        try {
            ps = con.prepareStatement("SELECT * FROM usuario WHERE correo = ?");
            ps.setString(1, correo);
            rs = ps.executeQuery();
            if (rs.next()) {
                resultado = true;
            } else {
                resultado = false;
            }
            con.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, "Error validando usuario", ex);
            resultado = false;
        }
        return resultado;
    }

    public String cambiarContraseña(String cedula, String contraActual, String contraNueva) {
        Connection con = getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario = new Usuario();
        Usuario usrPassAnt = this.recuperarUsuarioPorCorreo(cedula);
        if (!usrPassAnt.getContrasenia().equals(contraActual)) {
            return "NO-COINCIDE";
        }
        try {
            ps = con.prepareStatement("UPDATE usuario set contrasenia = ? where id = ?");
            ps.setString(1, contraNueva);
            ps.setLong(2, usrPassAnt.getId());
            ps.executeUpdate();
            con.close();
            ps.close();
            return "OK";
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, "Error insertando usuario", ex);
            return "ERROR";
        }
    }
    
    public List<Usuario> recuperarTodosUsuarios() {
        Connection con = getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM usuario");
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setCedula(rs.getString("cedula"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasenia(rs.getString("contrasenia"));
                usuario.setFoto(rs.getBytes("foto"));
                usuarios.add(usuario);
            }
            con.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, "Error insertando usuario", ex);
        }
        return usuarios;
    }
    
    public List<Auditoria> recuperarAuditoria() {
        String tabla = "";
        Connection con = getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Auditoria> auditorias = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM auditoria");
            rs = ps.executeQuery();
            while (rs.next()) {
                Auditoria auditoria = new Auditoria();
                auditoria.setTabla(rs.getString("tabla"));
                auditoria.setSentencia(rs.getString("sentencia"));
                auditoria.setUsuario(rs.getString("usuario"));              
                auditorias.add(auditoria);
            }
            con.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, "Error sacando auditorias", ex);
        }
        return auditorias;
    }
}
