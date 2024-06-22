package controller;

import java.util.ArrayList;
import model.*;
import view.View;

import java.util.List;
import java.util.Scanner;
import model.Manager.UsuarioManager;

public class Juego {

    private Usuario usuarioActual;
    private Scanner scanner;

    public Juego() {
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Jugar");
            System.out.println("3. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    login();
                    break;
                case 2:
                    jugar();
                    break;
                case 3:
                    System.out.println("Saliendo del juego...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void login() {
        System.out.print("Introduce tu nombre de usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Introduce tu contraseña: ");
        String contrasena = scanner.nextLine();

        Usuario usuario = UsuarioManager.autenticar(nombre, contrasena);
        if (usuario != null) {
            usuarioActual = usuario;
            System.out.println("Login exitoso. Bienvenido, " + usuario.getNombre());
        } else {
            System.out.println("Login fallido. Nombre de usuario o contraseña incorrectos.");
        }
    }

    private void jugar() {
        if (usuarioActual == null) {
            System.out.println("Estás jugando como Anónimo.");
            usuarioActual = new Usuario("Anonimo", " ", false);
        } else {
            System.out.println("Estás jugando como " + usuarioActual.getNombre());
        }

        Coordenada indiana = new Coordenada(1, 1);
        IndianaJones indianaJones = new IndianaJones(indiana, usuarioActual);
        Escenario escenario = new Escenario(10, 10, indianaJones);
        View view = new View();

        view.imprimirEscenario(escenario, indianaJones);
    }
}