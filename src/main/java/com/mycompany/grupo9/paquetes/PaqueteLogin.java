/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupo9.paquetes;


/**
 *
 * @author javier
 */
public class PaqueteLogin {
    private String usuario;
    private String password;
    private int  comprobado;

    
    public PaqueteLogin(){
        comprobado = 0;
    }
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getComprobado() {
        return comprobado;
    }

    public void setComprobado(int comprobado) {
        this.comprobado = comprobado;
    }

    @Override
    public String toString() {
        return usuario +";"+ password + ";" + comprobado;
    }
    
    
}
