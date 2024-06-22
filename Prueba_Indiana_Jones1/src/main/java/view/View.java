package view;

import java.util.Scanner;
import model.Escenario;
import model.IndianaJones;
import model.Serpiente;
import model.Usuario;

public class View {
    Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("1. Login");
        System.out.println("2. Jugar");
        System.out.println("3. Salir");
        System.out.println("Elige: ");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void imprimirEscenario(Escenario escenario, IndianaJones indianaJones) {

        escenario.imprimirTablero();

        while (!escenario.partidaAcabada) {
            System.out.printf("GEMAS: %d%n", escenario.getGemasRecogidas());
            System.out.printf("VIDAS: %d%n", indianaJones.getVidas());
            System.out.print("Usa WASD para mover a Indiana Jones: ");
            String movimiento = scanner.nextLine();
            indianaJones.mover(escenario, movimiento);

            for (Serpiente serpiente : escenario.getSerpientes()) {
                serpiente.moverAleatoriamente(escenario);
            }

            escenario.imprimirTablero();
        }
    }
}
