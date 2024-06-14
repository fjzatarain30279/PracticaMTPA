/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupo9.paquetes;

/**
 *
 * @author javier
 */
public class PaquetePartida {

    private char[][] tablero;
    private String turno;
    private int[] movimiento;
    private String jug1;
    private String jug2;

    private boolean finalizada;

    public PaquetePartida() {
        this.tablero = new char[3][3];
        this.finalizada = false;
    }

    public PaquetePartida(String j1, String j2) {
        this.tablero = new char[3][3];
        this.turno = j1;
        this.jug1 = j1;
        this.jug2 = j2;
        this.movimiento = new int[2];
        this.finalizada = false;

    }

    public char[][] getTablero() {
        return tablero;
    }

    public void setTablero(char[][] tablero) {
        this.tablero = tablero;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int[] getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(int[] movimiento) {
        this.movimiento = movimiento;
    }

    public String getJug1() {
        return jug1;
    }

    public void setJug1(String jug1) {
        this.jug1 = jug1;
    }

    public String getJug2() {
        return jug2;
    }

    public void setJug2(String jug2) {
        this.jug2 = jug2;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    @Override
    public String toString() {
        String mensaje;
        String tablero = "[";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero += this.tablero[i][j];
                if (j < 2) {
                    tablero += ",";
                }
            }
            if (i < 2) {
                tablero += ",";
            }
        }
        tablero += "]";
        
        int[] movimientoArray = this.movimiento;
        String movimientoString = "[" + movimientoArray[0] + "," + movimientoArray[1] + "]";
        mensaje = tablero + ";" + this.turno + ";" + movimientoString
                + ";" + this.jug1 + ";" + this.jug2;
        return mensaje;
    }

}
