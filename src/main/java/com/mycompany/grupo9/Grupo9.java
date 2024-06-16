/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.grupo9;

import java.util.Scanner;

/**
 * Clase principal del servidor
* @author Javier Zataraín
* @author Blanca Jorge
 */
public class Grupo9 {

    /**
     * Método principal del servidor
     * @param args Comando de líneas de argumento
     */
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        ControladorServidor srv = new ControladorServidor();
        do {
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println(srv.getListaUsuarios().keySet().toString());
                    break;
                case 2:
                    System.out.println(srv.getConectados());
                    break;
                case 3:
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    srv.guardaDatos();
                    srv.stopServidor();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
            System.out.println(); // Línea en blanco para separar iteraciones del menú
        } while (opcion != 0);

        scanner.close();
    }
}
