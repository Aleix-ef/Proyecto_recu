// model/Personaje.java
package model;

public abstract class Personaje {
    protected Coordenada coordenada;

    public Personaje(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public abstract void mover(Escenario escenario);
}
