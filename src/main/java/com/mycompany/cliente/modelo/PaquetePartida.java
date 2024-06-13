/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cliente.modelo;

/**
 *
 * @author javier
 */
public class PaquetePartida {

    private char[][] tablero;
    private int turno;
    private int[] movimiento;
    private String jug1;
    private String jug2;
    
    private boolean finalizada;
    
    public PaquetePartida() {
        this.tablero = new char[3][3];
        this.finalizada = false;
    }
    
    public PaquetePartida(String j1,String j2){
        this.tablero = new char[3][3];
        this.turno = 1;
        this.jug1 = j1;
        this.jug2 = j2;
        this.finalizada = false;
        
    }

    public char[][] getTablero() {
        return tablero;
    }

    public void setTablero(char[][] tablero) {
        this.tablero = tablero;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
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
    public String toString(){
        String mensaje;
        String tablero= "[";
        for(int i = 0;i<3;i++){
            for(int j = 0; j<3; j++){
                if(i<2&&j<2){
                tablero= tablero+this.tablero[i][j]+",";
                }else{
                    tablero=tablero+this.tablero[i][j];
                } 
            }
        }
        tablero=tablero + "]";
        mensaje = tablero + ";" + this.turno + ";" + this.movimiento.toString()
                + ";" + this.jug1 + ";" + this.jug2;
        return mensaje;
    }
    
    
}
