package model;

import java.util.Random;

public class Serpiente {
    private Coordenada posicion;
    private static final String VACIO = " ";
    private static final String SERPIENTE = "S";

    public Serpiente(Coordenada posicion) {
        this.posicion = posicion;
    }

    public void moverAleatoriamente(Escenario escenario) {
        Random rand = new Random();
        int filas = escenario.getTablero().length;
        int columnas = escenario.getTablero()[0].length;
        int nuevaFila, nuevaColumna;

        while (true) {
            int direccion = rand.nextInt(4);
            nuevaFila = posicion.getFila();
            nuevaColumna = posicion.getColumna();

            switch (direccion) {
                case 0:
                    nuevaFila--; // Arriba
                    break;
                case 1:
                    nuevaFila++; // Abajo
                    break;
                case 2:
                    nuevaColumna--; // Izquierda
                    break;
                case 3:
                    nuevaColumna++; // Derecha
                    break;
            }

            if (nuevaFila >= 0 && nuevaFila < filas && nuevaColumna >= 0 && nuevaColumna < columnas
                    && escenario.getTablero()[nuevaFila][nuevaColumna].equals(VACIO)) {
                escenario.getTablero()[posicion.getFila()][posicion.getColumna()] = VACIO;
                posicion = new Coordenada(nuevaFila, nuevaColumna);
                escenario.getTablero()[nuevaFila][nuevaColumna] = SERPIENTE;
                break;
            }
        }
    }

    public void setPosicion(Coordenada posicion) {
        this.posicion = posicion;
    }
}
