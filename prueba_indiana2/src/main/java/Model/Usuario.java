// model/Usuario.java
package model;

public class Usuario {
    private String nombre;
    private String contraseña;
    private boolean esPremium;

    public Usuario(String nombre, String contraseña, boolean esPremium) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.esPremium = esPremium;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public boolean isEsPremium() {
        return esPremium;
    }
}
