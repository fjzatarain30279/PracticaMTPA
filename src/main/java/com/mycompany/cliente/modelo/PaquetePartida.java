/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cliente.modelo;

/**
 * Clase que representa una partida entre dosjugadores
 *
 * @author Javier Zatarín
 * @author Blanca Jorge
 */
public class PaquetePartida {

    private char[][] tablero;
    private String turno;
    private int[] movimiento;
    private String jug1;
    private String jug2;
    private boolean finalizada;
    private String ganador;

    /**
     * Constructor por defecto que inicializa el tablero y marca la partida como
     * no finalizada.
     */
    public PaquetePartida() {
        this.tablero = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = '-';
            }
        }
        this.finalizada = false;
        this.ganador = "";
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    /**
     * Constructor que genera una nueva partida para dos usuarios. Inicializa el
     * turno con el jugador 1.
     *
     * @param j1 jugador 1
     * @param j2 jugador 2
     */
    public PaquetePartida(String j1, String j2) {
        this.tablero = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = '-';
            }
        }
        this.turno = j1;
        this.jug1 = j1;
        this.jug2 = j2;
        this.movimiento = new int[2];
        this.finalizada = false;
    }

    /**
     * Obtiene el tablero de la partida.
     *
     * @return tablero una matriz de caracteres que representa el tablero.
     */
    public char[][] getTablero() {
        return tablero;
    }

    /**
     * Establece el tablero de la partida.
     *
     * @param tablero una matriz de caracteres que representa el tablero.
     */
    public void setTablero(char[][] tablero) {
        this.tablero = tablero;
    }

    /**
     * Obtiene el jugador que tiene el turno.
     *
     * @return turno una cadena con el nombre del jugador que tiene el turno.
     */
    public String getTurno() {
        return turno;
    }

    /**
     * Establece el jugador que tiene el turno.
     *
     * @param turno una cadena con el nombre del jugador que tiene el turno.
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     * Obtiene el movimiento actual.
     *
     * @return movimiento El movimiento actual.
     */
    public int[] getMovimiento() {
        return movimiento;
    }

    /**
     * Establece el movimiento actual.
     *
     * @param movimiento El movimiento actual.
     */
    public void setMovimiento(int[] movimiento) {
        this.movimiento = movimiento;
    }

    /**
     * Obtiene el nombre del jugador 1.
     *
     * @return jug1 El nombre del jugador 1.
     */
    public String getJug1() {
        return jug1;
    }

    /**
     * Establece el nombre del jugador 1
     *
     * @param jug1 El nombre del jugador 1.
     */
    public void setJug1(String jug1) {
        this.jug1 = jug1;
    }

    /**
     * Obtiene el nombre del jugador 2.
     *
     * @return jug2 El nombre del jugador 2.
     */
    public String getJug2() {
        return jug2;
    }

    /**
     * Establece el nombre del jugador 2.
     *
     * @param jug2 El nombre del jugador 2.
     */
    public void setJug2(String jug2) {
        this.jug2 = jug2;
    }

    /**
     * Verifica si la partida ha finalizado.
     *
     * @return finalizada true si la partida ha finalizado, false si no.
     */
    public boolean isFinalizada() {
        return finalizada;
    }

    /**
     * Establece el estado de la partida.
     *
     * @param finalizada true si la partida ha finalizado, false si no
     */
    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    /**
     * Convierte la información de la partida en una cadena de texto.
     *
     * @return mensaje El tablero, turno actual, jugador 1, jugador 2
     */
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
        tablero = tablero + "]";
        int[] movimientoArray = this.movimiento;
        String movimientoString = "[" + movimientoArray[0] + "," + movimientoArray[1] + "]";
        mensaje = tablero + ";" + this.turno + ";" + movimientoString + ";" + this.jug1 + ";" + this.jug2 + ";" + this.finalizada + ";" + this.ganador;
        return mensaje;
    }
}
