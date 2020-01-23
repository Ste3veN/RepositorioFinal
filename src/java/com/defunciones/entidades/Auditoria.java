
package com.defunciones.entidades;

public class Auditoria {

    private String tabla;
    private String sentencia;
    private String usuario;
    private String fecha;

    public Auditoria() {
    }
    
    public String getFecha() {
        return fecha;
    }

    public String getSentencia() {
        return sentencia;
    }

    public String getTabla() {
        return tabla;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setSentencia(String sentencia) {
        this.sentencia = sentencia;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}
