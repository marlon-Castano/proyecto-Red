/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class Conexion {
    public static Connection cnx = null;
    public static Conexion instance;

    private Conexion() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cnx= DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_quileia","root","");
        } catch (ClassNotFoundException | SQLException ex) {
                System.out.println("Erro conectar bd bd: "+ex.getMessage());
            }
    }
    public static synchronized Conexion getInstance(){
        if(instance==null){
            instance=new Conexion();
        }
        return instance;
    }
    
    public Connection getcnn(){
        return cnx;
    }
    public void cerrarConexion(){
        instance=null;
    }
}
