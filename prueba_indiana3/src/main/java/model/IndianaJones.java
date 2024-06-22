package model;

public class IndianaJones extends Personaje {
    private int vidas;
    private boolean tieneProteccion;

    public IndianaJones(Coordenada coordenada) {
        super(coordenada);
        this.vidas = 3;
        this.tieneProteccion = false;
    }

    public int getVidas() {
        return vidas;
    }

    public void perderVida() {
        if (tieneProteccion) {
            tieneProteccion = false;
        } else {
            this.vidas--;
        }
    }

    public void ganarVida() {
        this.vidas++;
    }

    public boolean tieneProteccion() {
        return tieneProteccion;
    }

    public void activarProteccion() {
        this.tieneProteccion = true;
    }

    public void desactivarProteccion() {
        this.tieneProteccion = false;
    }
}
