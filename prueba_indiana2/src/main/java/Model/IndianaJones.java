// model/IndianaJones.java
package model;

public class IndianaJones extends Personaje {
    private int vidas;

    public IndianaJones(Coordenada coordenada, int vidas) {
        super(coordenada);
        this.vidas = vidas;
    }

    public int getVidas() {
        return vidas;
    }

    public void perderVida() {
        vidas--;
    }

    public void mover(Escenario escenario, char direccion) {
        int nuevaFila = coordenada.getFila();
        int nuevaColumna = coordenada.getColumna();

        switch (direccion) {
            case 'w': case 'W':
                nuevaFila--;
                break;
            case 's': case 'S':
                nuevaFila++;
                break;
            case 'a': case 'A':
                nuevaColumna--;
                break;
            case 'd': case 'D':
                nuevaColumna++;
                break;
        }

        if (escenario.obtenerElemento(new Coordenada(nuevaFila, nuevaColumna)) != '#') {
            coordenada.setFila(nuevaFila);
            coordenada.setColumna(nuevaColumna);
        }
    }

    @Override
    public void mover(Escenario escenario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
