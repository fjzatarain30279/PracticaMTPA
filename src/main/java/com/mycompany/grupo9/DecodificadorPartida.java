/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupo9;

import com.mycompany.grupo9.paquetes.PaquetePartida;

/**
 * Clase que implementa el decodificador de la partida
 * @author Javier Zataraín
 * @author Blanca Jorge
 */
public class DecodificadorPartida implements Decodificador {
    /**
     * Decodifica una cadena de texto en un objeto de la clase
     * @param linea Línea de texto que contiene los datos de la partida
     * @return paquete Objeto que contiene los datos decodificados
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
        p.setFinalizada(Boolean.parseBoolean(parcial[5]));
        p.setGanador(parcial[6]);
        
        return p;
    }
}
