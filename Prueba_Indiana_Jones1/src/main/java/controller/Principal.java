package controller;

import controller.Juego;
import model.*;
import view.View;

import java.util.Scanner;

public class Principal {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.iniciar();
    }
}
