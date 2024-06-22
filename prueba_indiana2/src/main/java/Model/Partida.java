// model/Partida.java
package model;

public class Partida {
    private int numero;
    private String usuario;
    private int gemasRecogidas;
    private long tiempoEmpleado;

    public Partida(int numero, String usuario, int gemasRecogidas, long tiempoEmpleado) {
        this.numero = numero;
        this.usuario = usuario;
        this.gemasRecogidas = gemasRecogidas;
        this.tiempoEmpleado = tiempoEmpleado;
    }

    public int getNumero() {
        return numero;
    }

    public String getUsuario() {
        return usuario;
    }

    public int getGemasRecogidas() {
        return gemasRecogidas;
    }

    public long getTiempoEmpleado() {
        return tiempoEmpleado;
    }
}
