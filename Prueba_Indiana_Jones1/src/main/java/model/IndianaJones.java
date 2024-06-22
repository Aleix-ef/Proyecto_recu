package model;

public class IndianaJones {
    private Coordenada posicion;
    private Usuario usuario;
    private int vidas;
    private static final int VIDAS_INICIALES = 3;

    public IndianaJones(Coordenada posicion, Usuario usuario) {
        this.posicion = posicion;
        this.usuario = usuario;
        this.vidas = VIDAS_INICIALES;
    }

    public void mover(Escenario escenario, String movimiento) {
        int nuevaFila = posicion.getFila();
        int nuevaColumna = posicion.getColumna();

        switch (movimiento.toUpperCase()) {
            case "W":
                nuevaFila--;
                break;
            case "A":
                nuevaColumna--;
                break;
            case "S":
                nuevaFila++;
                break;
            case "D":
                nuevaColumna++;
                break;
            default:
                System.out.println("Movimiento no válido. Usa W, A, S o D.");
                return;
        }

        escenario.comprobarMovimiento(nuevaFila, nuevaColumna);
    }

    public int getVidas() {
        return vidas;
    }

    public void perderVida() {
        this.vidas--;
    }
}