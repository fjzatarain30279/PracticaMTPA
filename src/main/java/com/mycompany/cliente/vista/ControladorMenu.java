/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cliente.vista;

import com.mycompany.cliente.main.Cliente;
import com.mycompany.cliente.modelo.PaqueteLogin;

/**
 *
 * @author javier
 */
public class ControladorMenu {

    public static final int PUERTO = 2000;
    private VistaMenuInicial vista;
    private PaqueteLogin modelo;

    public ControladorMenu(VistaMenuInicial vista) {
        this.vista = vista;
        modelo = Cliente.getModeloLogin();

    }

    public void procesaEventoCrearUsuario() {
        modelo.setUsuario(vista.getUsr());
        modelo.setPassword(vista.getPswd());
        modelo.setComprobado(-1);

        if (modelo.getUsuario().length() < 1) {
            vista.setErrMessage("Longitud muy pequeña");
        } else if (modelo.getPassword().length() < 1) {
            vista.setErrMessage("Longitud password muy pequeña");
        } else {
            try {
                if (Cliente.getSocket() == null) {
                    vista.setErrMessage("Creando cuenta en el servidor...");
                    java.net.Socket miSocket = new java.net.Socket("localhost", PUERTO);
                    java.io.PrintStream outred = new java.io.PrintStream(miSocket.getOutputStream());
                    outred.println("L"+ modelo.toString()); // envia al servidor
                    outred.flush();
                    java.io.BufferedReader inred = new java.io.BufferedReader(new java.io.InputStreamReader(miSocket.getInputStream()));
                    String linea = inred.readLine();
                    String[] aux = linea.split(";");
                    System.out.println(aux[2]);
                    if (Integer.parseInt(aux[2]) == 1) {
                        vista.setErrMessage("EL USUARIO YA EXISTE");
                    } else {
                        Cliente.setSocket(miSocket);
                        Cliente.getGestorVistas().mostrarVistaUsuarios();
                    }
                } else {
                    vista.setErrMessage("Creando cuenta en el servidor...");
                    java.net.Socket miSocket = Cliente.getSocket();
                    java.io.PrintStream outred = new java.io.PrintStream(miSocket.getOutputStream());
                    outred.println("L" + modelo.toString()); // envia al servidor
                    outred.flush();
                    java.io.BufferedReader inred = new java.io.BufferedReader(new java.io.InputStreamReader(miSocket.getInputStream()));
                    String linea = inred.readLine();
                    String[] aux = linea.split(";");
                    System.out.println(aux[2]);
                    if (Integer.parseInt(aux[2]) == 1) {
                        vista.setErrMessage("EL USUARIO YA EXISTE");
                    } else {
                        Cliente.setSocket(miSocket);
                        Cliente.getGestorVistas().mostrarVistaUsuarios();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void procesaEventoIniciarSesion() {
        modelo.setUsuario(vista.getUsr());
        modelo.setPassword(vista.getPswd());

        if (modelo.getUsuario().length() < 1) {
            vista.setErrMessage("Longitud muy pequeña");
        } else if (modelo.getPassword().length() < 1) {
            vista.setErrMessage("Longitud password muy pequeña");
        } else {
            try {
                vista.setErrMessage("Contactando con el servidor...");
                if (Cliente.getSocket() == null) {
                    java.net.Socket miSocket = new java.net.Socket("localhost", PUERTO);
                    java.io.PrintStream outred = new java.io.PrintStream(miSocket.getOutputStream());
                    outred.println("L"+ modelo.toString()); // envia al servidor
                    outred.flush();
                    java.io.BufferedReader inred = new java.io.BufferedReader(new java.io.InputStreamReader(miSocket.getInputStream()));
                    String linea = inred.readLine();
                    String[] aux = linea.split(";");
                    System.out.println(aux[2]);
                    if (Integer.parseInt(aux[2]) == 1) {
                        vista.setErrMessage("EL USUARIO NO EXISTE");
                    } else if (Integer.parseInt(aux[2]) == 2) {
                        vista.setErrMessage("CONTRASEÑA ERRONEA");
                    } else {
                        Cliente.setSocket(miSocket);
                        System.out.println(miSocket.toString());
                        Cliente.getGestorVistas().mostrarVistaUsuarios();
                    }
                } else {
                    java.net.Socket miSocket = Cliente.getSocket();
                    java.io.PrintStream outred = new java.io.PrintStream(miSocket.getOutputStream());
                    outred.println("L" + modelo.toString()); // envia al servidor
                    outred.flush();
                    java.io.BufferedReader inred = new java.io.BufferedReader(new java.io.InputStreamReader(miSocket.getInputStream()));
                    String linea = inred.readLine();
                    String[] aux = linea.split(";");
                    System.out.println(aux[2]);
                    if (Integer.parseInt(aux[2]) == 1) {
                        vista.setErrMessage("EL USUARIO NO EXISTE");
                    } else if (Integer.parseInt(aux[2]) == 2) {
                        vista.setErrMessage("CONTRASEÑA ERRONEA");
                    } else {
                        Cliente.setSocket(miSocket);
                        System.out.println(miSocket.toString());
                        Cliente.getGestorVistas().mostrarVistaUsuarios();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
