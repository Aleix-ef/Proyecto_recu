package model;

import java.time.LocalDateTime;

public class Partida {
    private int numero;
    private Usuario usuario;
    private int gemasRecogidas;
    private long tiempo;

    public Partida(int numero, Usuario usuario, int gemasRecogidas, long tiempo) {
        this.numero = numero;
        this.usuario = usuario;
        this.gemasRecogidas = gemasRecogidas;
        this.tiempo = tiempo;
    }

    // Getters y Setters

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getGemasRecogidas() {
        return gemasRecogidas;
    }

    public void setGemasRecogidas(int gemasRecogidas) {
        this.gemasRecogidas = gemasRecogidas;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }
}
