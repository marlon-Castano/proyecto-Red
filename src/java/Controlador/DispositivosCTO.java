/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Electrodomesticos;
import Modelo.Red;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "DispositivosCTO", urlPatterns = {"/DispositivosCTO"})
public class DispositivosCTO extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        Facade objF = new Facade();
        if (menu.equalsIgnoreCase("Electrinicos")) {
            switch (accion) {

                case "Listar":
                    List<Electrodomesticos> listas = null;
                    listas = objF.traerElectrodomesticos();
                    List<Red> listaR = null;
                    listaR = objF.traerRed();
                    request.setAttribute("list", listaR);
                    request.setAttribute("listas", listas);
                    request.getRequestDispatcher("DispositivosElectronicos.jsp").forward(request, response);
                    break;
                case "AgregarD":

                    List<Red> lista = null;
                    lista = objF.traerRed();
                    if (lista != null) {
                       
                         for (int i = 0; i < lista.size(); i++) {
                            if (objF.ValidarRed(lista.get(i)) == false) {

                                lista.remove(i);
                            }
                        }
                    }
                    request.setAttribute("list", lista);
                    request.getRequestDispatcher("AgregarDispositivos.jsp").forward(request, response);
                    break;
                case "Agergar":
                    String mac = request.getParameter("txt_Mac");
                    String tipo = request.getParameter("txt_tipo");
                    String ip = request.getParameter("txt_Ip");
                    int conexionActual = Integer.parseInt(request.getParameter("select_ConexionActual"));
                    boolean conectadoRed = true;

                    Electrodomesticos nuevoE = new Electrodomesticos(mac, conexionActual, tipo, ip, conectadoRed, null);
                    boolean recibido = true;
                    if (objF.ingresarElectrodomestico(nuevoE)) {
                        PrintWriter out = response.getWriter();
                        out.println("<script>  alert('se agrego correctamente') </script>");
                        request.getRequestDispatcher("DispositivosCTO?menu=Electrinicos&accion=Listar").forward(request, response);
                    } else {
                        PrintWriter out = response.getWriter();
                        out.println("<script>  alert('no se puedo agregar la nueva red') </script>");
                        request.getRequestDispatcher("DispositivosCTO?menu=Electrinicos&accion=AgregarD").forward(request, response);
                    }
                    break;
                case "Editar":
                    Electrodomesticos elec = new Electrodomesticos();
                    elec.setMac(request.getParameter("id"));
                    elec = objF.traerElectrodomestico(elec);
                    request.setAttribute("dis", elec);
                    boolean estado = true;
                    request.setAttribute("estado", estado);
                    List<Red> lista2 = null;
                    lista2 = objF.traerRed();
                    request.setAttribute("list", lista2);
                    request.getRequestDispatcher("AgregarDispositivos.jsp?estado=true").forward(request, response);
                    break;
                case "Actulizar":
                    String mac2 = request.getParameter("txt_Mac1");
                    String tipo2 = request.getParameter("txt_tipo");
                    String ip2 = request.getParameter("txt_Ip");
                    int conexionActual2 = Integer.parseInt(request.getParameter("select_ConexionActual"));
                    boolean conectadoRed2 = true;

                    Electrodomesticos nuevoE2 = new Electrodomesticos(mac2, conexionActual2, tipo2, ip2, conectadoRed2, null);
                    boolean recibido2 = true;
                    if (objF.gupdateDispositivo(nuevoE2)) {
                        PrintWriter out = response.getWriter();
                        out.println("<script>  alert('se agrego correctamente') </script>");
                        request.getRequestDispatcher("DispositivosCTO?menu=Electrinicos&accion=Listar").forward(request, response);
                    } else {
                        PrintWriter out = response.getWriter();
                        out.println("<script>  alert('no se puedo agregar la nueva red') </script>");
                        request.getRequestDispatcher("DispositivosCTO?menu=Electrinicos&accion=AgregarD").forward(request, response);
                    }
                    break;
                case "Estado":
                    Electrodomesticos elec2 = new Electrodomesticos();
                    elec2.setMac(request.getParameter("id"));
                    elec2 = objF.traerView(elec2);
                    elec2.setConexionActiva(Boolean.parseBoolean(request.getParameter("estadoD")));
                    if (objF.cambiarEstado(elec2)) {
                        request.getRequestDispatcher("DispositivosCTO?menu=Electrinicos&accion=HistoriaD&mac=" + elec2.getMac()).forward(request, response);
                    } else {
                        request.getRequestDispatcher("DispositivosElectronicos.jsp?poblema=true").forward(request, response);
                    }
                    break;
                case "HistoriaD":
                    Electrodomesticos elec3 = new Electrodomesticos();
                    elec3.setMac(request.getParameter("mac"));
                    System.out.println("histporialD " + elec3.getMac());
                    List<Electrodomesticos> listas2 = null;
                    listas2 = objF.traerElectroHUnique(elec3);
                    List<Red> listaR2 = null;
                    listaR2 = objF.traerRed();
                    request.setAttribute("list", listaR2);
                    request.setAttribute("listas", listas2);
                    request.getRequestDispatcher("HistoricoElectrinicos.jsp").forward(request, response);
                    break;
            }

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
