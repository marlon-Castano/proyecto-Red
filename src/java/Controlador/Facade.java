/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.RedDAO;
import Modelo.DAO.ElectrodometicosDAO;
import Modelo.Red;
import Modelo.Electrodomesticos;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author ASUS
 */
public class Facade {

    public boolean ingresarRed(Red nuevo) {
        RedDAO consulta = new RedDAO();
        return consulta.create(nuevo);
    }

    public List<Red> traerRed() {
        RedDAO consulta = new RedDAO();
        return consulta.getRedes();
    }

    public Red getRed(Red index) {
        RedDAO consulta = new RedDAO();
        return consulta.read(index);

    }

    public boolean gupdateRed(Red item) {
        RedDAO consulta = new RedDAO();
        return consulta.update(item);
    }

    public List<Electrodomesticos> traerElectrodomesticos() {
        ElectrodometicosDAO consulta = new ElectrodometicosDAO();
        return consulta.getElectrodomesticos();
    }

    public boolean ingresarElectrodomestico(Electrodomesticos nuevo) {
        ElectrodometicosDAO consulta = new ElectrodometicosDAO();
        if (consulta.create(nuevo)) {
            if (consulta.createHistorial(nuevo)) {
                return true;
            }
        }
        return false;
    }

    public List<Electrodomesticos> traerElectroH() {
        ElectrodometicosDAO consulta = new ElectrodometicosDAO();
        return consulta.getElectroh();
    }

    public List<Electrodomesticos> traerElectroHUnique(Electrodomesticos index) {
        ElectrodometicosDAO consulta = new ElectrodometicosDAO();
        return consulta.getElectrohUnique(index);
    }

    public Electrodomesticos traerView(Electrodomesticos index) {
        ElectrodometicosDAO consulta = new ElectrodometicosDAO();
        return consulta.readView(index);
    }

    public Electrodomesticos traerElectrodomestico(Electrodomesticos index) {
        ElectrodometicosDAO consulta = new ElectrodometicosDAO();
        return consulta.read(index);
    }

    public boolean gupdateDispositivo(Electrodomesticos item) {
        ElectrodometicosDAO consulta = new ElectrodometicosDAO();
        return consulta.update(item);
    }

    public boolean cambiarEstado(Electrodomesticos nuevo) {
        ElectrodometicosDAO consulta = new ElectrodometicosDAO();
        if (nuevo.isConexionActiva() == true) {
            nuevo.setConexionActiva(false);
            if (consulta.createHistorial(nuevo)) {
                return true;
            }
        } else {
            nuevo.setConexionActiva(true);
            if (consulta.createHistorial(nuevo)) {
                return true;
            }
        }
        return false;
    }

    public List<Electrodomesticos> TraerConectadosRed(Electrodomesticos index) {
        ElectrodometicosDAO consulta = new ElectrodometicosDAO();
        List<Electrodomesticos> lista1 = null;
        lista1 = consulta.getElectroConexion(index);
        List<Electrodomesticos> lista2 = new ArrayList<>();
        if (lista1 != null && lista1.size() > 0) {
            for (Electrodomesticos elec : lista1) {

                lista2.add(consulta.readFecha(elec));
            }
        }
        return lista2;
    }
    public List<Electrodomesticos> TraerElectroHISTRIAL(Electrodomesticos index) {
        ElectrodometicosDAO consulta = new ElectrodometicosDAO();
        List<Electrodomesticos> lista1 = null;
        lista1 = consulta.getElectroHISTRIAL(index);
          
        return lista1;
    }

    public boolean ValidarRed(Red index) {
        Electrodomesticos aux = new Electrodomesticos();
        aux.setConexionActual(index.getId());
        if (TraerConectadosRed(aux) != null) {
            List<Electrodomesticos> auxLista = new ArrayList<>();
            for (Electrodomesticos eletre : TraerConectadosRed(aux)) {
                if (eletre.isConexionActiva() == true) {
                    auxLista.add(eletre);
                }
            }
            int tamano = auxLista.size();
            if (tamano >= 3) {

                return false;
            }
        } else {
            return true;
        }

        return true;
    }

}
