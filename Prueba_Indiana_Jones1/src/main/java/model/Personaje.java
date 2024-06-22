package model;

public abstract class Personaje {
    protected Coordenada posicion;

    public Personaje(Coordenada posicion) {
        this.posicion = posicion;
    }

    public Coordenada getPosicion() {
        return posicion;
    }

    public void setPosicion(Coordenada posicion) {
        this.posicion = posicion;
    }

    public abstract void mover(Escenario escenario, String direccion);
}
