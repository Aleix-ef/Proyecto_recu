package model;

public class Partida {
    private int numeroPartida;
    private Usuario usuario;
    private int gemasRecolectadas;
    private long tiempoEmpleado; // Tiempo en milisegundos

    public Partida(int numeroPartida, Usuario usuario, int gemasRecolectadas, long tiempoEmpleado) {
        this.numeroPartida = numeroPartida;
        this.usuario = usuario;
        this.gemasRecolectadas = gemasRecolectadas;
        this.tiempoEmpleado = tiempoEmpleado;
    }

    public int getNumeroPartida() {
        return numeroPartida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public int getGemasRecolectadas() {
        return gemasRecolectadas;
    }

    public long getTiempoEmpleado() {
        return tiempoEmpleado;
    }
}
