/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author ASUS
 */
public class Red {
    private int    id;
    private int tipo;
    private String nombreRed;
    private String tipoCifrado;
    private String usuario;
    private String contrasena;

    public Red() {
    }

    public Red(int id, int tipo, String nombreRed, String tipoCifrado, String usuario, String contrasena) {
        this.id = id;
        this.tipo = tipo;
        this.nombreRed = nombreRed;
        this.tipoCifrado = tipoCifrado;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    
    public Red(int tipo, String nombreRed, String tipoCifrado, String usuario, String contrasena) {
        this.tipo = tipo;
        this.nombreRed = nombreRed;
        this.tipoCifrado = tipoCifrado;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int  tipo) {
        this.tipo = tipo;
    }

    public String getNombreRed() {
        return nombreRed;
    }

    public void setNombreRed(String nombreRed) {
        this.nombreRed = nombreRed;
    }

    public String getTipoCifrado() {
        return tipoCifrado;
    }

    public void setTipoCifrado(String tipoCifrado) {
        this.tipoCifrado = tipoCifrado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    
    
    
}
