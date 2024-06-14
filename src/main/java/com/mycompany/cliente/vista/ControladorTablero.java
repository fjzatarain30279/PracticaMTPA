/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cliente.vista;

import com.mycompany.cliente.main.Cliente;
import com.mycompany.cliente.modelo.PaquetePartida;

/**
 *
 * @author javier
 */
public class ControladorTablero {
    private VistaUsuarios vista;
    private PaquetePartida modelo;
    public static final int PUERTO = 2000;

    public ControladorTablero(VistaUsuarios vista) {
        this.vista = vista;
        modelo = Cliente.getModeloPartida();

    }
}
