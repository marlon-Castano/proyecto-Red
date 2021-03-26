/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Conexion.Conexion;
import Modelo.Red;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class RedDAO {

    private static final String SQL_READ = "SELECT * FROM red WHERE id = ?";
     
    private static final String SQL_READ_ALL = "select * from red ";
    private static final String SQL_INSERT = "INSERT INTO red (id,nombreRed, tipo, tipoCifrado, usuario, contrasena) VALUES (null, ?, ?, ?, ?,?)";
    private static final String SQL_UPDATE = "UPDATE red SET  nombreRed= ?, tipo=?, tipoCifrado=?, usuario=?, contrasena=? WHERE id= ?";
    private static final Conexion cnx = Conexion.getInstance();

    public boolean create(Red nuevo) {
        PreparedStatement ps;
        try {
            ps = cnx.getcnn().prepareStatement(SQL_INSERT);
            ps.setString(1, nuevo.getNombreRed());
            ps.setInt(2, nuevo.getTipo());
            ps.setString(3, nuevo.getTipoCifrado());
            ps.setString(4, nuevo.getUsuario());
            ps.setString(5, nuevo.getContrasena());

            if (ps.executeUpdate() > 0) {
                System.out.println("Se agregó");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar: " + ex.getMessage());
        } finally {
            cnx.cerrarConexion();
        }
        return false;
    }

    public List<Red> getRedes() {
        List<Red> listPedi = null;
        if (cnx.getcnn() != null) {
            PreparedStatement psmt;
            try {
                psmt = cnx.getcnn().prepareStatement(SQL_READ_ALL);
                ResultSet rs = psmt.executeQuery();
                listPedi = new ArrayList<>();
                while (rs.next()) {
                    Red aux = new Red(rs.getInt("id"), rs.getInt("tipo"), rs.getString("nombreRed"), rs.getString("tipoCifrado"),
                            rs.getString("usuario"), rs.getString("contrasena"));
                    listPedi.add(aux);
                }

            } catch (SQLException ex) {
                System.out.println("Error obtener: " + ex.getMessage());
            } finally {
                cnx.cerrarConexion();
            }
        }
        return listPedi;
    }

    public boolean update(Red item) {
        try {
            PreparedStatement ps;
            ps = cnx.getcnn().prepareStatement(SQL_UPDATE);

            ps.setString(1, item.getNombreRed());
            ps.setInt(2, item.getTipo());
            ps.setString(3, item.getTipoCifrado());
            ps.setString(4, item.getUsuario());
            ps.setString(5, item.getContrasena());
            ps.setInt(6, item.getId());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RedDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cnx.cerrarConexion();
        }
        return false;
    }

    public Red read(Red index) {
        Red pro = null;
        PreparedStatement psnt;

        if (cnx != null) {
            try {
                psnt = cnx.getcnn().prepareStatement(SQL_READ);
                System.out.println(index.getId());
                psnt.setInt(1, index.getId());
                ResultSet rs = psnt.executeQuery();

                if (rs.next()) {
                    pro = new Red(rs.getInt("id"), rs.getInt("tipo"), rs.getString("nombreRed"), rs.getString("tipoCifrado"),
                            rs.getString("usuario"), rs.getString("contrasena"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(RedDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                cnx.cerrarConexion();
            }
        }
        return pro;
    }
}
