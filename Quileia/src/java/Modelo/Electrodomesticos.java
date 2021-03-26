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
public class Electrodomesticos {
    private String mac;
    private int conexionActual;
    private String tipo;
    private String ip;
    private boolean conexionActiva;
    private String fecha;

    public Electrodomesticos() {
    }

    public Electrodomesticos(String mac, int conexionActual, String tipo, String ip) {
        this.mac = mac;
        this.conexionActual = conexionActual;
        this.tipo = tipo;
        this.ip = ip;
    }

    public Electrodomesticos(String mac, int conexionActual, String tipo, String ip, boolean conexionActiva, String fecha) {
        this.mac = mac;
        this.conexionActual = conexionActual;
        this.tipo = tipo;
        this.ip = ip;
        this.conexionActiva = conexionActiva;
        this.fecha = fecha;
    }

    public Electrodomesticos(String mac, boolean conexionActiva, String fecha) {
        this.mac = mac;
        this.conexionActiva = conexionActiva;
        this.fecha = fecha;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getConexionActual() {
        return conexionActual;
    }

    public void setConexionActual(int conexionActual) {
        this.conexionActual = conexionActual;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isConexionActiva() {
        return conexionActiva;
    }

    public void setConexionActiva(boolean conexionActiva) {
        this.conexionActiva = conexionActiva;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
    
}
