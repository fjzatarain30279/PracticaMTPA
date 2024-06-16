/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cliente.vista;

import com.mycompany.cliente.modelo.PaquetePartida;

/**
 * Clase que implementa la decodificación de los datos de la partida de cadena
 * a objeto de la clase
 * @author Javier Zataraín
 * @author Blanca Jorge
 */
public class DecodificadorPartida implements Decodificador {
    /**
     * Método que decodifica una cadena de carácteres a un objeto de la clase
     * @param linea Contiene la información de la partida separadas por ;
     * @return paquete Objeto con los datos decodificados
     */
    @Override
    public PaquetePartida decodificar(String linea) {
        PaquetePartida p = new PaquetePartida();
        String[] parcial = linea.split(";");
        String cadena = parcial[0].substring(1, parcial[0].length() - 1);
        String[] aux2 = cadena.split(",");
        char[][] tablero = new char[3][3];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = aux2[index].charAt(0);
                index++;
            }
        }
        cadena = parcial[2].substring(1, parcial[2].length()-1);
        aux2 = cadena.split(",");
        int[] mov = new int[2];
        mov[0] = Integer.parseInt(aux2[0]);
        mov[1] = Integer.parseInt(aux2[1]);
        p.setTablero(tablero);
        p.setTurno(parcial[1]);
        p.setMovimiento(mov);
        p.setJug1(parcial[3]);
        p.setJug2(parcial[4]);
        p.setFinalizada(Boolean.getBoolean(parcial[5]));
        p.setGanador(parcial[6]);
        
        return p;
    }
}
