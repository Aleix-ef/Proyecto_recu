// model/Serpiente.java
package model;

import java.util.Random;

public class Serpiente extends Personaje {
    private Random random;

    public Serpiente(Coordenada coordenada) {
        super(coordenada);
        random = new Random();
    }

    public void mover(Escenario escenario) {
        int intentos = 0;
        boolean movido = false;

        while (intentos < 3 && !movido) {
            int direccion = random.nextInt(4);
            int nuevaFila = coordenada.getFila();
            int nuevaColumna = coordenada.getColumna();

            switch (direccion) {
                case 0: nuevaFila--; break; // Arriba
                case 1: nuevaFila++; break; // Abajo
                case 2: nuevaColumna--; break; // Izquierda
                case 3: nuevaColumna++; break; // Derecha
            }

            if (escenario.obtenerElemento(new Coordenada(nuevaFila, nuevaColumna)) == ' ') {
                coordenada.setFila(nuevaFila);
                coordenada.setColumna(nuevaColumna);
                movido = true;
            } else {
                intentos++;
            }
        }

        if (!movido) {
            moverExhaustivamente(escenario);
        }
    }

    private void moverExhaustivamente(Escenario escenario) {
        int[] direcciones = {-1, 1, 0, 0}; // Arriba, Abajo
        int[] columnas = {0, 0, -1, 1}; // Izquierda, Derecha

        for (int i = 0; i < direcciones.length; i++) {
            int nuevaFila = coordenada.getFila() + direcciones[i];
            int nuevaColumna = coordenada.getColumna() + columnas[i];

            if (escenario.obtenerElemento(new Coordenada(nuevaFila, nuevaColumna)) == ' ') {
                coordenada.setFila(nuevaFila);
                coordenada.setColumna(nuevaColumna);
                break;
            }
        }
    }
}
