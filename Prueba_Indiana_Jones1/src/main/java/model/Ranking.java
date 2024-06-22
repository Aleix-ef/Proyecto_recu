/*package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ranking {
    private final List<Partida> partidas;

    public Ranking() {
        partidas = new ArrayList<>();
    }

    public void agregarPartida(Partida partida) {
        partidas.add(partida);
        Collections.sort(partidas);
        if (partidas.size() > 3) {
            partidas.remove(partidas.size() - 1); // Mantener solo las tres mejores partidas
        }
    }

    public void mostrarRanking() {
        System.out.println("Ranking de las tres mejores partidas:");
        for (int i = 0; i < partidas.size(); i++) {
            Partida partida = partidas.get(i);
            System.out.printf("%d. Usuario: %s - Tiempo: %d segundos%n", i + 1, partida.getUsuario(), partida.getTiempo());
        }
    }
}*/
