// model/Escenario.java
package model;

public class Escenario {
    private char[][] tablero;

    public Escenario(int filas, int columnas) {
        tablero = new char[filas][columnas];
        inicializarTablero();
    }

    private void inicializarTablero() {
        // Inicializa el tablero con muros y espacios vacíos
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (i == 0 || i == tablero.length - 1 || j == 0 || j == tablero[i].length - 1) {
                    tablero[i][j] = '#'; // Muros en los bordes
                } else {
                    tablero[i][j] = ' '; // Espacios vacíos
                }
            }
        }
    }

    public void colocarElemento(Coordenada coord, char elemento) {
        tablero[coord.getFila()][coord.getColumna()] = elemento;
    }

    public char obtenerElemento(Coordenada coord) {
        return tablero[coord.getFila()][coord.getColumna()];
    }

    public void imprimirTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j]);
            }
            System.out.println();
        }
    }
}
