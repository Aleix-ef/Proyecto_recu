package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ranking {
    private static List<Partida> partidas = new ArrayList<>();

    public static void agregarPartida(Partida partida) {
        partidas.add(partida);
    }

    public static void mostrarTop3() {
        Collections.sort(partidas, new Comparator<Partida>() {
            @Override
            public int compare(Partida p1, Partida p2) {
                if (p1.getGemasRecolectadas() != p2.getGemasRecolectadas()) {
                    return p2.getGemasRecolectadas() - p1.getGemasRecolectadas();
                }
                return Long.compare(p1.getTiempoEmpleado(), p2.getTiempoEmpleado());
            }
        });

        System.out.println("Ranking de las 3 mejores partidas:");
        for (int i = 0; i < Math.min(3, partidas.size()); i++) {
            Partida partida = partidas.get(i);
            double tiempoEnSegundos = partida.getTiempoEmpleado() / 1000.0;
            System.out.printf("%d. %s - Gemas: %d, Tiempo: %.2f segundos%n",
                    (i + 1), partida.getUsuario().getNombre(), partida.getGemasRecolectadas(), tiempoEnSegundos);
        }
    }
}
