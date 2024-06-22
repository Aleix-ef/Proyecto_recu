package model;

import java.util.List;
import java.util.Random;

public class Escenario {

    private String[][] tablero;
    private int filas;
    private int columnas;

    private static final String MURO = "0";
    private static final String GEM = "*";
    private static final String VACIO = " ";
    private static final String INDIANA_JONES = "9";
    private static final String SERPIENTE = "S";

    private Coordenada indianaJonesPos;
    private IndianaJones indianaJones;
    private Coordenada[] serpientesPos;
    private Serpiente[] serpientes;
    private int gemasRecogidas;
    public boolean partidaAcabada = false;

    public Escenario(int filas, int columnas, IndianaJones indianaJones) {
        this.filas = filas;
        this.columnas = columnas;
        this.gemasRecogidas = 0;
        this.indianaJones = indianaJones;
        tablero = new String[filas][columnas];
        inicializarTablero();
    }

    public void inicializarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = VACIO;
            }
        }
        for (int i = 0; i < filas; i++) {
            tablero[i][0] = MURO;
            tablero[i][columnas - 1] = MURO;
        }
        for (int j = 0; j < columnas; j++) {
            tablero[0][j] = MURO;
            tablero[filas - 1][j] = MURO;
        }
        indianaJonesPos = new Coordenada(1, 1);
        tablero[indianaJonesPos.getFila()][indianaJonesPos.getColumna()] = INDIANA_JONES;

        colocarElementosAleatorios(GEM, 5);
        serpientesPos = colocarElementosAleatorios(SERPIENTE, 3);
        serpientes = new Serpiente[serpientesPos.length];
        for (int i = 0; i < serpientesPos.length; i++) {
            serpientes[i] = new Serpiente(serpientesPos[i]);
        }
        colocarElementosAleatorios(MURO, 7);
    }

    private Coordenada[] colocarElementosAleatorios(String elemento, int cantidad) {
        Random rand = new Random();
        Coordenada[] posiciones = new Coordenada[cantidad];
        int colocados = 0;
        while (colocados < cantidad) {
            int fila = rand.nextInt(filas - 2) + 1; // Exclusión de los bordes
            int columna = rand.nextInt(columnas - 2) + 1; // Exclusión de los bordes
            if (tablero[fila][columna].equals(VACIO)) {
                tablero[fila][columna] = elemento;
                posiciones[colocados] = new Coordenada(fila, columna);
                colocados++;
            }
        }
        return posiciones;
    }

    public void imprimirTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public String[][] getTablero() {
        return tablero;
    }

    public Coordenada getIndianaJonesPos() {
        return indianaJonesPos;
    }

    public Coordenada[] getSerpientesPos() {
        return serpientesPos;
    }

    public Serpiente[] getSerpientes() {
        return serpientes;
    }

    public void comprobarMovimiento(int nuevaFila, int nuevaColumna) {
        String destino = tablero[nuevaFila][nuevaColumna];

        if (destino.equals(MURO)) {
            return;
        }

        if (destino.equals(SERPIENTE)) {
            this.indianaJones.perderVida();
            if (this.indianaJones.getVidas() > 0) {
                resetPosiciones();
            } else {
                imprimirTablero();
                System.out.println("Has perdido. Fin del juego");
                partidaAcabada = true;
            }
        }

        if (destino.equals(GEM)) {
            gemasRecogidas++;
            if (gemasRecogidas == 5) {
                imprimirTablero();
                System.out.println("Has ganado. ¡Enhorabuena!");
                partidaAcabada = true;
            }
        }

        tablero[indianaJonesPos.getFila()][indianaJonesPos.getColumna()] = VACIO;
        indianaJonesPos = new Coordenada(nuevaFila, nuevaColumna);
        tablero[nuevaFila][nuevaColumna] = INDIANA_JONES;
    }

    public int getGemasRecogidas() {
        return gemasRecogidas;
    }

    private void resetPosiciones() {
        indianaJonesPos = new Coordenada(1, 1);
        tablero[indianaJonesPos.getFila()][indianaJonesPos.getColumna()] = INDIANA_JONES;

        for (int i = 0; i < serpientesPos.length; i++) {
            tablero[serpientesPos[i].getFila()][serpientesPos[i].getColumna()] = VACIO;
        }

        serpientesPos = colocarElementosAleatorios(SERPIENTE, serpientesPos.length);
        for (int i = 0; i < serpientesPos.length; i++) {
            serpientes[i].setPosicion(serpientesPos[i]);
        }
    }
}