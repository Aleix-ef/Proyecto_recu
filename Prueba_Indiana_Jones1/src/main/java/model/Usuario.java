package model;

public class Usuario {
    private String nombre;
    private String contrasena;
    boolean esPremium;

    public Usuario(String nombre, String contrasena, boolean esPremium) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.esPremium = esPremium;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public boolean esPremium(String nombre) {
        return nombre.equals("u3");
    }
}
