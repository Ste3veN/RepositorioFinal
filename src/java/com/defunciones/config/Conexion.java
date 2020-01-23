
package com.defunciones.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;


public class Conexion {
    
    private final String base = "NoFetal_Relacional";
    private final String usuario = "postgres";
    private final String clave = "1234";
    private final String parametros= "?autoReconnect=true&useSSL=false";
    private final String url = "jdbc:postgresql://localhost:5432/" + base + parametros;
    private Connection con = null;
            
    public Connection getConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            con = (Connection) DriverManager.getConnection(this.url, this.usuario, this.clave);
        } catch (SQLException e) {
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return con;
    }
    
    
}
