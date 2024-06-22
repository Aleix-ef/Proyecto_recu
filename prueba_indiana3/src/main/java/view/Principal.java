package view;

import controller.Juego;
import java.util.Scanner;

public class Principal {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicio();
    }

    public static void inicio() {
        Juego juego = new Juego();
        int opcion;
        do {
            System.out.println("1. Login");
            System.out.println("2. Jugar");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    juego.login();
                    break;
                case 2:
                    juego.jugar();
                    break;
                case 3:
                    System.out.println("Gracias por jugar. ¡Adiós!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion != 3);
    }
}
