// controller/JuegoController.java
package controller;

import model.*;
import java.util.*;

public class JuegoController {
    private Escenario escenario;
    private IndianaJones indianaJones;
    private List<Serpiente> serpientes;
    private List<Gema> gemas;
    private List<Usuario> usuarios;
    private List<Partida> partidas;
    private Scanner scanner;
    private Usuario usuarioActual;

    public JuegoController() {
        inicializarUsuarios();
        inicializarEscenario();
        inicializarPersonajes();
        inicializarGemas();
        partidas = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    private void inicializarUsuarios() {
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("u1", "1234", false));
        usuarios.add(new Usuario("u2", "1234", false));
        usuarios.add(new Usuario("u3", "1234", true));
        usuarios.add(new Usuario("u4", "1234", false));
    }

    private void inicializarEscenario() {
        escenario = new Escenario(10, 10);
    }

    private void inicializarPersonajes() {
        indianaJones = new IndianaJones(new Coordenada(1, 1), 3);
        serpientes = new ArrayList<>();
        serpientes.add(new Serpiente(new Coordenada(3, 3)));
        serpientes.add(new Serpiente(new Coordenada(5, 5)));
        serpientes.add(new Serpiente(new Coordenada(7, 7)));
    }

    private void inicializarGemas() {
        gemas = new ArrayList<>();
        gemas.add(new Gema(new Coordenada(8, 8)));
        gemas.add(new Gema(new Coordenada(2, 2)));
        gemas.add(new Gema(new Coordenada(6, 6)));
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
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
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 3);
    }

    private void mostrarMenu() {
        System.out.println("1. Login");
        System.out.println("2. Jugar");
        System.out.println("3. Salir");
        if (usuarioActual != null) {
            System.out.println("Usuario actual: " + usuarioActual.getNombre());
        }
        System.out.print("Seleccione una opción: ");
    }

    private void login() {
        System.out.print("Ingrese nombre de usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String contraseña = scanner.nextLine();

        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getContraseña().equals(contraseña)) {
                usuarioActual = usuario;
                System.out.println("Login exitoso. Usuario: " + nombre);
                return;
            }
        }

        System.out.println("Usuario o contraseña incorrectos. Intente de nuevo.");
    }

    private void jugar() {
        long inicioTiempo = System.currentTimeMillis();
        boolean partidaTerminada = false;
        int gemasRecogidas = 0;

        while (!partidaTerminada) {
            escenario.imprimirTablero();
            System.out.print("Ingrese movimiento (WASD): ");
            char movimiento = scanner.nextLine().charAt(0);
            indianaJones.mover(escenario, movimiento);
            moverSerpientes();
            actualizarEscenario();
            if (verificarColisionConSerpientes()) {
                indianaJones.perderVida();
                if (indianaJones.getVidas() == 0) {
                    partidaTerminada = true;
                    System.out.println("Has perdido. Fin del juego.");
                } else {
                    reiniciarPosiciones();
                }
            } else if (verificarRecoleccionDeGemas()) {
                gemasRecogidas++;
                if (gemasRecogidas == gemas.size()) {
                    partidaTerminada = true;
                    System.out.println("Has ganado. ¡Enhorabuena!");
                }
            }
        }

        long tiempoEmpleado = System.currentTimeMillis() - inicioTiempo;
        registrarPartida(gemasRecogidas, tiempoEmpleado);
        mostrarRanking();
    }

    private void moverSerpientes() {
        for (Serpiente serpiente : serpientes) {
            serpiente.mover(escenario);
        }
    }

    private void actualizarEscenario() {
        // Limpiar el tablero
        inicializarEscenario();
        // Colocar personajes
        escenario.colocarElemento(indianaJones.getCoordenada(), 'I');
        for (Serpiente serpiente : serpientes) {
            escenario.colocarElemento(serpiente.getCoordenada(), 'S');
        }
        // Colocar gemas
        for (Gema gema : gemas) {
            escenario.colocarElemento(gema.getCoordenada(), '@');
        }
    }

    private boolean verificarColisionConSerpientes() {
        for (Serpiente serpiente : serpientes) {
            if (serpiente.getCoordenada().getFila() == indianaJones.getCoordenada().getFila() &&
                serpiente.getCoordenada().getColumna() == indianaJones.getCoordenada().getColumna()) {
                return true;
            }
        }
        return false;
    }

    private boolean verificarRecoleccionDeGemas() {
        Iterator<Gema> iter = gemas.iterator();
        while (iter.hasNext()) {
            Gema gema = iter.next();
            if (gema.getCoordenada().getFila() == indianaJones.getCoordenada().getFila() &&
                gema.getCoordenada().getColumna() == indianaJones.getCoordenada().getColumna()) {
                iter.remove();
                return true;
            }
        }
        return false;
    }

    private void reiniciarPosiciones() {
        indianaJones = new IndianaJones(new Coordenada(1, 1), indianaJones.getVidas());
        inicializarPersonajes();
    }

    private void registrarPartida(int gemasRecogidas, long tiempoEmpleado) {
        int numeroPartida = partidas.size() + 1;
        String usuario = (usuarioActual != null) ? usuarioActual.getNombre() : "Anónimo";
        Partida partida = new Partida(numeroPartida, usuario, gemasRecogidas, tiempoEmpleado);
        partidas.add(partida);
    }

    private void mostrarRanking() {
        partidas.sort((p1, p2) -> {
            int comparacion = Integer.compare(p2.getGemasRecogidas(), p1.getGemasRecogidas());
            if (comparacion == 0) {
                comparacion = Long.compare(p1.getTiempoEmpleado(), p2.getTiempoEmpleado());
            }
            return comparacion;
        });

        System.out.println("Ranking Top 3:");
        for (int i = 0; i < Math.min(3, partidas.size()); i++) {
            Partida partida = partidas.get(i);
            System.out.println((i + 1) + ". " + partida.getUsuario() + " - Gemas: " + partida.getGemasRecogidas() + " - Tiempo: " + partida.getTiempoEmpleado() + " ms");
        }
    }
}
