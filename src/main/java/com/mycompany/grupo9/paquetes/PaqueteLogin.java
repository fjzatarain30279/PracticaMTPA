/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupo9.paquetes;


/**
 *La clase PaqueteLogin se encarga de inicializar el modelo necesario 
 para efectuar el inicio de sesión en el programa.
 * @author Javier Zataraín
 * @author Blanca Jorge
 */
public class PaqueteLogin {
    private String usuario;
    private String password;
    private int  comprobado;

    /**
     * Constructor que inicializa el aributo comprobado a 0
     */
    public PaqueteLogin(){
        comprobado = 0;
    }
    /**
     * Método encargado de devolver el usuario
     * @return nombre de usuario
     */
    public String getUsuario() {
        return usuario;
    }
    /**
     * Método que sirve para establecer el nombre de usuario
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    /**
     * Método que devuelve la contraseña del usuario
     * @return contraseña asociada al usuario
     */
    public String getPassword() {
        return password;
    }
     /**
     * Método que se usa para establecer la contraseña del usuario
     * @param password contraseña del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Método que devuelve el estado asociado al usuario
     * @return comprobado del usuario
    */
    public int getComprobado() {
        return comprobado;
    }
    /**
     * Método que establece el estado de comprobación del usuario
     * @param comprobado estado del usuario
     */
    public void setComprobado(int comprobado) {
        this.comprobado = comprobado;
    }
    /**
     * Devuelve la representación de los parámetros de la clase 
     * @return cadena de texto con el nombre de usuario, la contraseña y su estado de comprobación
     */
    @Override
    public String toString() {
        return usuario +";"+ password + ";" + comprobado;
    }
    
    
}

    
    

