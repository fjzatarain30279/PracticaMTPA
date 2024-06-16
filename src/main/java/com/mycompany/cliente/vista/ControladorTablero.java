/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cliente.vista;

import com.mycompany.cliente.main.Cliente;
import com.mycompany.cliente.modelo.PaquetePartida;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

/**
 * Clase que controla el tablero y se encarga de procesar la actualización de la
 * partida en curso así como de la finalización de la misma y las jugadas.
 *
 * @author Javier Zataraín
 * @author Blanca Jorge
 */
public class ControladorTablero {

    private VistaTablero vista;
    private PaquetePartida modelo;
    public static final int PUERTO = 2000;

    /**
     * Método que muestra el tablero con el modelado de la partida
     *
     * @param vista El tablero
     */
    public ControladorTablero(VistaTablero vista) {
        this.vista = vista;
        modelo = Cliente.getModeloPartida();

    }

    /**
     * Método encargado de gestionar la actualización de lo que se vaya dando en
     * la partida
     */
    public void procesaEventoActualizar() {
        java.io.PrintStream o = null;

        DecodificadorPartida dec = new DecodificadorPartida();
        try {
            Socket miSocket = Cliente.getSocket();
            o = new java.io.PrintStream(miSocket.getOutputStream());
            o.println("T;" + Cliente.getModeloLogin().getUsuario());
            BufferedReader inred = new java.io.BufferedReader(new java.io.InputStreamReader(miSocket.getInputStream()));
            String linea = inred.readLine();
            System.out.println(linea);
            modelo = dec.decodificar(linea);
            vista.actualizar(modelo);
        } catch (IOException ex) {

        }

    }

    /**
     * Método que procesa la finalización de los eventos
     */
    public void procesaEventoFinalizar() {

    }

    /**
     * Método que procesa la jugada
     *
     * @param j
     */
    public void procesaEventoJugada(int[] j) {
        modelo.setMovimiento(j);
        java.io.PrintStream o = null;
        DecodificadorPartida dec = new DecodificadorPartida();
        try {
            Socket miSocket = Cliente.getSocket();
            o = new java.io.PrintStream(miSocket.getOutputStream());
            o.println("P" + modelo.toString());
            BufferedReader inred = new java.io.BufferedReader(new java.io.InputStreamReader(miSocket.getInputStream()));
            String linea = inred.readLine();
            System.out.println(linea);
            modelo = dec.decodificar(linea);
            vista.actualizar(modelo);
        } catch (IOException ex) {

        }
    }

    public void procesaEventoCerrar() {
        java.io.PrintStream o = null;
        try {
            Socket miSocket = Cliente.getSocket();
            o = new java.io.PrintStream(miSocket.getOutputStream());
            o.println("X");
        } catch (IOException e) {

        }
    }
}
