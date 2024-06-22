/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Manager;

import java.util.ArrayList;
import java.util.List;
import model.Usuario;

/**
 *
 * @author aleix
 */
public class UsuarioManager {

    private static List<Usuario> usuarios;

    static {
        // Inicializar la lista de usuarios
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("u1", "1234", false));
        usuarios.add(new Usuario("u2", "1234", false));
        usuarios.add(new Usuario("u3", "1234", true));
        usuarios.add(new Usuario("u4", "1234", false));
    }

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    public static Usuario autenticar(String nombre, String contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getContrasena().equals(contrasena)) {
                return usuario;
            }
        }
        return null; // O lanzar una excepción si la autenticación falla
    }
}

