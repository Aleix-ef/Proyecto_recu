package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Escenario {
    private char[][] matriz;
    private IndianaJones indianaJones;
    private Serpiente[] serpientes;
    private List<Gema> gemas;
    private ObjetoProtector objetoProtector;
    private PersonajeMaligno personajeMaligno;
    private ObjetoVidaExtra objetoVidaExtra;
    private int gemasRecolectadas;
    private int totalGemas; // Variable para mantener el número total de gemas creadas

    public Escenario() {
        matriz = new char[10][10];
        serpientes = new Serpiente[3];
        gemas = new ArrayList<>();
        gemasRecolectadas = 0;
        totalGemas = 0; // Inicialización de la variable
    }

    public void inicializar() {
        // Inicializar el escenario con muros alrededor
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (i == 0 || i == matriz.length - 1 || j == 0 || j == matriz[i].length - 1) {
                    matriz[i][j] = '#';
                } else {
                    matriz[i][j] = '.';
                }
            }
        }

        // Posicionar Indiana Jones
        indianaJones = new IndianaJones(new Coordenada(1, 1));
        matriz[indianaJones.getCoordenada().getFila()][indianaJones.getCoordenada().getColumna()] = '9';

        // Posicionar serpientes
        Random rand = new Random();
        for (int i = 0; i < serpientes.length; i++) {
            Coordenada coord;
            do {
                coord = new Coordenada(rand.nextInt(8) + 1, rand.nextInt(8) + 1); // Evitar posiciones de borde
            } while (matriz[coord.getFila()][coord.getColumna()] != '.');
            serpientes[i] = new Serpiente(coord);
            matriz[serpientes[i].getCoordenada().getFila()][serpientes[i].getCoordenada().getColumna()] = 'S';
        }

        // Posicionar gemas
        String[] colores = {"Rojo", "Verde", "Azul", "Amarillo", "Morado"};
        int[] puntuaciones = {10, 20, 30, 40, 50};
        for (int i = 0; i < 5; i++) { // Suponemos que siempre habrá 5 gemas
            Coordenada coord;
            do {
                coord = new Coordenada(rand.nextInt(8) + 1, rand.nextInt(8) + 1); // Evitar posiciones de borde
            } while (matriz[coord.getFila()][coord.getColumna()] != '.');
            Gema gema = new Gema(coord, puntuaciones[i], colores[i]);
            gemas.add(gema);
            matriz[coord.getFila()][coord.getColumna()] = '*';
            totalGemas++; // Incrementamos el total de gemas
        }

        // Posicionar objeto protector
        do {
            objetoProtector = new ObjetoProtector(new Coordenada(rand.nextInt(8) + 1, rand.nextInt(8) + 1));
        } while (matriz[objetoProtector.getCoordenada().getFila()][objetoProtector.getCoordenada().getColumna()] != '.');
        matriz[objetoProtector.getCoordenada().getFila()][objetoProtector.getCoordenada().getColumna()] = 'o';

        // Posicionar personaje maligno
        do {
            personajeMaligno = new PersonajeMaligno(new Coordenada(rand.nextInt(8) + 1, rand.nextInt(8) + 1));
        } while (matriz[personajeMaligno.getCoordenada().getFila()][personajeMaligno.getCoordenada().getColumna()] != '.');
        matriz[personajeMaligno.getCoordenada().getFila()][personajeMaligno.getCoordenada().getColumna()] = 'X';

        // Posicionar objeto de vida extra
        do {
            objetoVidaExtra = new ObjetoVidaExtra(new Coordenada(rand.nextInt(8) + 1, rand.nextInt(8) + 1), 5);
        } while (matriz[objetoVidaExtra.getCoordenada().getFila()][objetoVidaExtra.getCoordenada().getColumna()] != '.');
        matriz[objetoVidaExtra.getCoordenada().getFila()][objetoVidaExtra.getCoordenada().getColumna()] = '8';
    }

    public boolean moverIndianaJones(String movimiento) {
        int nuevaFila = indianaJones.getCoordenada().getFila();
        int nuevaColumna = indianaJones.getCoordenada().getColumna();

        switch (movimiento.toLowerCase()) {
            case "w":
                nuevaFila--;
                break;
            case "s":
                nuevaFila++;
                break;
            case "a":
                nuevaColumna--;
                break;
            case "d":
                nuevaColumna++;
                break;
            case "*":
                if (indianaJones.tieneProteccion()) {
                    for (Gema gema : gemas) {
                        int difFila = Math.abs(gema.getCoordenada().getFila() - nuevaFila);
                        int difColumna = Math.abs(gema.getCoordenada().getColumna() - nuevaColumna);
                        if (difFila <= 1 && difColumna <= 1) {
                            nuevaFila = gema.getCoordenada().getFila();
                            nuevaColumna = gema.getCoordenada().getColumna();
                            break;
                        }
                    }
                }
                break;
            default:
                return false;
        }

        if (nuevaFila < 0 || nuevaFila >= 10 || nuevaColumna < 0 || nuevaColumna >= 10 || matriz[nuevaFila][nuevaColumna] == '#') {
            return false;
        }

        matriz[indianaJones.getCoordenada().getFila()][indianaJones.getCoordenada().getColumna()] = '.';
        indianaJones.getCoordenada().setFila(nuevaFila);
        indianaJones.getCoordenada().setColumna(nuevaColumna);

        final int finalNuevaFila = nuevaFila;
        final int finalNuevaColumna = nuevaColumna;

        if (matriz[finalNuevaFila][finalNuevaColumna] == '*') {
            gemasRecolectadas++;
            gemas.removeIf(g -> g.getCoordenada().getFila() == finalNuevaFila && g.getCoordenada().getColumna() == finalNuevaColumna);
        } else if (matriz[finalNuevaFila][finalNuevaColumna] == 'o') {
            indianaJones.activarProteccion();
        } else if (matriz[finalNuevaFila][finalNuevaColumna] == 'X') {
            indianaJones.perderVida();
            indianaJones.perderVida();
        } else if (matriz[finalNuevaFila][finalNuevaColumna] == '8') {
            indianaJones.ganarVida();
        }

        matriz[finalNuevaFila][finalNuevaColumna] = '9';
        return true;
    }

    public void moverSerpientes() {
        Random rand = new Random();
        for (Serpiente serpiente : serpientes) {
            int intentos = 0;
            boolean movido = false;
            while (intentos < 3 && !movido) {
                int direccion = rand.nextInt(4);
                int nuevaFila = serpiente.getCoordenada().getFila();
                int nuevaColumna = serpiente.getCoordenada().getColumna();

                switch (direccion) {
                    case 0:
                        nuevaFila--;
                        break;
                    case 1:
                        nuevaFila++;
                        break;
                    case 2:
                        nuevaColumna--;
                        break;
                    case 3:
                        nuevaColumna++;
                        break;
                }

                if (nuevaFila >= 0 && nuevaFila < 10 && nuevaColumna >= 0 && nuevaColumna < 10 && matriz[nuevaFila][nuevaColumna] == '.') {
                    matriz[serpiente.getCoordenada().getFila()][serpiente.getCoordenada().getColumna()] = '.';
                    serpiente.getCoordenada().setFila(nuevaFila);
                    serpiente.getCoordenada().setColumna(nuevaColumna);
                    matriz[nuevaFila][nuevaColumna] = 'S';
                    movido = true;
                } else {
                    intentos++;
                }
            }
            if (!movido) {
                // Movimiento exhaustivo
                for (int i = 0; i < 4 && !movido; i++) {
                    int nuevaFila = serpiente.getCoordenada().getFila();
                    int nuevaColumna = serpiente.getCoordenada().getColumna();

                    switch (i) {
                        case 0:
                            nuevaFila--;
                            break;
                        case 1:
                            nuevaFila++;
                            break;
                        case 2:
                            nuevaColumna--;
                            break;
                        case 3:
                            nuevaColumna++;
                            break;
                    }

                    if (nuevaFila >= 0 && nuevaFila < 10 && nuevaColumna >= 0 && nuevaColumna < 10 && matriz[nuevaFila][nuevaColumna] == '.') {
                        matriz[serpiente.getCoordenada().getFila()][serpiente.getCoordenada().getColumna()] = '.';
                        serpiente.getCoordenada().setFila(nuevaFila);
                        serpiente.getCoordenada().setColumna(nuevaColumna);
                        matriz[nuevaFila][nuevaColumna] = 'S';
                        movido = true;
                    }
                }
            }
        }
    }

    public void imprimir() {
        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String BLUE = "\u001B[34m";
        final String YELLOW = "\u001B[33m";
        final String MAGENTA = "\u001B[35m";

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                boolean isGema = false;
                for (Gema gema : gemas) {
                    if (gema.getCoordenada().getFila() == i && gema.getCoordenada().getColumna() == j) {
                        switch (gema.getColor()) {
                            case "Rojo":
                                System.out.print(RED + "*" + RESET + " ");
                                break;
                            case "Verde":
                                System.out.print(GREEN + "*" + RESET + " ");
                                break;
                            case "Azul":
                                System.out.print(BLUE + "*" + RESET + " ");
                                break;
                            case "Amarillo":
                                System.out.print(YELLOW + "*" + RESET + " ");
                                break;
                            case "Morado":
                                System.out.print(MAGENTA + "*" + RESET + " ");
                                break;
                        }
                        isGema = true;
                        break;
                    }
                }
                if (!isGema) {
                    System.out.print(matriz[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("GEMAS: " + gemasRecolectadas);
        System.out.println("VIDAS: " + indianaJones.getVidas());
    }

    public boolean todasGemasRecolectadas() {
        return gemasRecolectadas == totalGemas;
    }

    public int getGemasRecolectadas() {
        return gemasRecolectadas;
    }
}
