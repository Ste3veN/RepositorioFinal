
package com.defunciones.test;

import com.defunciones.dao.UsuarioDAO;
import com.defunciones.entidades.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario();
        usuario.setCedula("1723232323");
        usuario.setNombre("Juan");
        usuario.setCorreo("test@test.com");
        usuario.setContrasenia("password");

        File file = new File("E:\\Juanjo\\imagenes\\waasaaaaa.jpg");
        byte[] bytesArray = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(bytesArray);
        fis.close();
        usuario.setFoto(bytesArray);
        usuarioDAO.guardarUsuario(usuario);

    }

}
