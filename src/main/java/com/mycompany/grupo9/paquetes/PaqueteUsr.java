/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupo9.paquetes;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 * Clase que contiene la lista con todos los usuarios registrados.
 * @author Javier Zataraín
 * @author Blanca Jorge
 */
public class PaqueteUsr extends AbstractListModel{
    private ArrayList<String> listaUsuarios;
    private String seleccionado;
    private String seleccionador;

    /**
     * Método que obtiene el usuario seleccionado
     * @return seleccionado Usuario seleccionado
     */
    public String getSeleccionado() {
        return seleccionado;
    }
    /**
     * Método que establece el usuario seleccionado
     * @param seleccionado Usuario seleccionado 
     */
    public void setSeleccionado(String seleccionado) {
        this.seleccionado = seleccionado;
    }
    /**
     * Método que obtiene el usuario seleccionador
     * @return seleccionador Usuario que selecciona
     */
    public String getSeleccionador() {
        return seleccionador;
    }
    /**
     * Método que establece al usuario
     * @param seleccionador 
     */
    public void setSeleccionador(String seleccionador) {
        this.seleccionador = seleccionador;
    }
    /**
     * Devulve una lista con los usuarios disponibles
     * @return listaUsuarios Listado de usuarios disponibles
     */
    public ArrayList<String> getListaUsuarios() {
        return listaUsuarios;
    }
    /**
     * Permite establecer una lista con los usuarios
     * @param listaUsuarios Listado de usuarios
     */
    public void setListaUsuarios(ArrayList<String> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    public PaqueteUsr(){
        listaUsuarios = new ArrayList<String>();
        seleccionado = "";
        seleccionador = "";
    }
    /**
     * Método que muestra el tamaño de la lista de usuarios
     * @return listaUsuario.size() Tamaño de la lista
     */
    @Override
    public int getSize() {
       return listaUsuarios.size();
    }
    /**
 * Obtiene el elemento de la lista de usuarios en la posición especificada.
 * @param indice El índice del elemento a obtener.
 * @return El nombre del usuario en la posición indicada.
 */
    @Override
    public String getElementAt(int indice) {
      String usr = listaUsuarios.get(indice);
      return usr;
    }
    
}
