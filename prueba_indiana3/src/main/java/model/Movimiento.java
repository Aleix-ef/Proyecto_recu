package model;

import java.util.Scanner;

public class Movimiento {
    private static final Scanner scanner = new Scanner(System.in);

    public static String pedirMovimiento() {
        System.out.print("Introduce tu movimiento (w/a/s/d/*): ");
        return scanner.nextLine();
    }
}
