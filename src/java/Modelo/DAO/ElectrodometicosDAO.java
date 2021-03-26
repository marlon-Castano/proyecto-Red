/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Conexion.Conexion;
import Modelo.Electrodomesticos;
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
public class ElectrodometicosDAO {

    private static final String SQL_READ = "SELECT * FROM electrodomesticos WHERE mac = ?";
    private static final String SQL_READ_CONEXION = "SELECT * FROM electrodomesticos WHERE conexionActual = ?";
    private static final String SQL_READ_FECHA = "SELECT * from electroh WHERE fecha=(SELECT max(fecha) FROM electroh WHERE mac=?)";
    private static final String SQL_VIEW_READ = "SELECT * FROM electroh WHERE mac = ?";
    private static final String SQL_READ_ALL = "select * from electrodomesticos ";
    private static final String SQL_READ_ALL_ELECTROH = "SELECT * FROM electroh where conexionActual=? ";
    private static final String SQL_INSERT = "INSERT INTO electrodomesticos (mac,tipo, ip, conexionActual) VALUES (?, ?, ?, ?)";
    private static final String SQL_INSERT_HISTORIAL = "INSERT INTO historial (mac,conectadoRed, fecha) VALUES (?, ?,null)";
    private static final String SQL_UPDATE = "UPDATE electrodomesticos SET   tipo=?, ip=?, conexionActual=? WHERE mac= ?";
    private static final String SQL_VIEW_READ_ALL = "SELECT * FROM electroh";
    private static final Conexion cnx = Conexion.getInstance();

    public boolean create(Electrodomesticos nuevo) {
        PreparedStatement ps;
        try {
            ps = cnx.getcnn().prepareStatement(SQL_INSERT);
            ps.setString(1, nuevo.getMac());
            ps.setString(2, nuevo.getTipo());
            ps.setString(3, nuevo.getIp());
            ps.setInt(4, nuevo.getConexionActual());
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

    public boolean createHistorial(Electrodomesticos nuevo) {
        PreparedStatement ps;
        try {
            ps = cnx.getcnn().prepareStatement(SQL_INSERT_HISTORIAL);
            ps.setString(1, nuevo.getMac());
            ps.setBoolean(2, nuevo.isConexionActiva());
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

    public List<Electrodomesticos> getElectrodomesticos() {
        List<Electrodomesticos> listElec = null;
        if (cnx.getcnn() != null) {
            PreparedStatement psmt;
            try {
                psmt = cnx.getcnn().prepareStatement(SQL_READ_ALL);
                ResultSet rs = psmt.executeQuery();
                listElec = new ArrayList<>();
                while (rs.next()) {
                    Electrodomesticos aux = new Electrodomesticos(rs.getString("mac"), rs.getInt("conexionActual"), rs.getString("tipo"), rs.getString("ip"));
                    listElec.add(aux);
                }

            } catch (SQLException ex) {
                System.out.println("Error obtener: " + ex.getMessage());
            } finally {
                cnx.cerrarConexion();
            }
        }
        return listElec;
    }

    public List<Electrodomesticos> getElectroConexion(Electrodomesticos index) {
        List<Electrodomesticos> listElec = null;
        if (cnx.getcnn() != null) {
            PreparedStatement psmt;
            try {
                psmt = cnx.getcnn().prepareStatement(SQL_READ_CONEXION);
                psmt.setInt(1, index.getConexionActual());
                ResultSet rs = psmt.executeQuery();
                listElec = new ArrayList<>();
                while (rs.next()) {
                    Electrodomesticos aux = new Electrodomesticos(rs.getString("mac"), rs.getInt("conexionActual"), rs.getString("tipo"), rs.getString("ip"));
                    listElec.add(aux);
                }

            } catch (SQLException ex) {
                System.out.println("Error obtener: " + ex.getMessage());
            } finally {
                cnx.cerrarConexion();
            }
        }
        return listElec;
    }

    public List<Electrodomesticos> getElectroHISTRIAL(Electrodomesticos index) {
        List<Electrodomesticos> listElec = null;
        if (cnx.getcnn() != null) {
            PreparedStatement psmt;
            try {
                psmt = cnx.getcnn().prepareStatement(SQL_READ_ALL_ELECTROH);
                psmt.setInt(1, index.getConexionActual());
                ResultSet rs = psmt.executeQuery();
                listElec = new ArrayList<>();
                while (rs.next()) {
                    Electrodomesticos aux = new Electrodomesticos(rs.getString("mac"), rs.getInt("conexionActual"), rs.getString("tipo"), rs.getString("ip"),
                            rs.getBoolean("conectadoRed"), rs.getString("fecha"));
                    listElec.add(aux);
                }

            } catch (SQLException ex) {
                System.out.println("Error obtener: " + ex.getMessage());
            } finally {
                cnx.cerrarConexion();
            }
        }
        return listElec;
    }

    public List<Electrodomesticos> getElectroh() {
        List<Electrodomesticos> listElec = null;
        if (cnx.getcnn() != null) {
            PreparedStatement psmt;
            try {
                psmt = cnx.getcnn().prepareStatement(SQL_VIEW_READ_ALL);
                ResultSet rs = psmt.executeQuery();
                listElec = new ArrayList<>();
                while (rs.next()) {
                    Electrodomesticos aux = new Electrodomesticos(rs.getString("mac"), rs.getInt("conexionActual"), rs.getString("tipo"), rs.getString("ip"),
                            rs.getBoolean("conectadoRed"), rs.getString("fecha"));
                    listElec.add(aux);
                }

            } catch (SQLException ex) {
                System.out.println("Error obtener: " + ex.getMessage());
            } finally {
                cnx.cerrarConexion();
            }
        }
        return listElec;
    }

    public List<Electrodomesticos> getElectrohUnique(Electrodomesticos index) {
        List<Electrodomesticos> listElec = null;
        if (cnx.getcnn() != null) {
            PreparedStatement psmt;
            try {
                psmt = cnx.getcnn().prepareStatement(SQL_VIEW_READ);
                psmt.setString(1, index.getMac());
                ResultSet rs = psmt.executeQuery();
                listElec = new ArrayList<>();
                while (rs.next()) {
                    Electrodomesticos aux = new Electrodomesticos(rs.getString("mac"), rs.getInt("conexionActual"), rs.getString("tipo"), rs.getString("ip"),
                            rs.getBoolean("conectadoRed"), rs.getString("fecha"));
                    listElec.add(aux);
                }

            } catch (SQLException ex) {
                System.out.println("Error obtener: " + ex.getMessage());
            } finally {
                cnx.cerrarConexion();
            }
        }
        return listElec;
    }

    public boolean update(Electrodomesticos item) {
        try {
            PreparedStatement ps;
            ps = cnx.getcnn().prepareStatement(SQL_UPDATE);

            ps.setString(1, item.getTipo());
            ps.setString(2, item.getIp());
            ps.setInt(3, item.getConexionActual());
            ps.setString(4, item.getMac());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ElectrodometicosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cnx.cerrarConexion();
        }
        return false;
    }

    public Electrodomesticos read(Electrodomesticos index) {
        Electrodomesticos pro = null;
        PreparedStatement psnt;

        if (cnx != null) {
            try {
                psnt = cnx.getcnn().prepareStatement(SQL_READ);
                psnt.setString(1, index.getMac());
                ResultSet rs = psnt.executeQuery();

                if (rs.next()) {
                    pro = new Electrodomesticos(rs.getString("mac"), rs.getInt("conexionActual"), rs.getString("tipo"), rs.getString("ip"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ElectrodometicosDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                cnx.cerrarConexion();
            }
        }
        return pro;
    }

    public Electrodomesticos readFecha(Electrodomesticos index) {
        Electrodomesticos pro = null;
        PreparedStatement psnt;

        if (cnx != null) {
            try {
                psnt = cnx.getcnn().prepareStatement(SQL_READ_FECHA);
                psnt.setString(1, index.getMac());
                ResultSet rs = psnt.executeQuery();

                if (rs.next()) {
                    pro = new Electrodomesticos(rs.getString("mac"), rs.getInt("conexionActual"), rs.getString("tipo"), rs.getString("ip"),
                            rs.getBoolean("conectadoRed"), rs.getString("fecha"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ElectrodometicosDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                cnx.cerrarConexion();
            }
        }
        return pro;
    }

    public Electrodomesticos readView(Electrodomesticos index) {
        Electrodomesticos pro = null;
        PreparedStatement psnt;

        if (cnx != null) {
            try {
                psnt = cnx.getcnn().prepareStatement(SQL_VIEW_READ);
                psnt.setString(1, index.getMac());
                ResultSet rs = psnt.executeQuery();

                if (rs.next()) {
                    pro = new Electrodomesticos(rs.getString("mac"), rs.getInt("conexionActual"), rs.getString("tipo"), rs.getString("ip"),
                            rs.getBoolean("conectadoRed"), rs.getString("fecha"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ElectrodometicosDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                cnx.cerrarConexion();
            }
        }
        return pro;
    }

}
