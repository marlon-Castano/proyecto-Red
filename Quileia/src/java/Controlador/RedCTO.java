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
import java.util.ArrayList;
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
@WebServlet(name = "RedCTO", urlPatterns = {"/RedCTO"})
public class RedCTO extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        Facade objF = new Facade();
        if (menu.equalsIgnoreCase("Red")) {
            switch (accion) {

                case "Listar":
                    List<Red> list = null;
                    list = objF.traerRed();
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("RED.jsp").forward(request, response);
                    break;
                case "Agergar":
                    String nombre = request.getParameter("txt_nombreRed");
                    int tipo = Integer.parseInt(request.getParameter("select_tipo"));
                    String tipoC;
                    if (tipo == 1) {
                        tipoC = request.getParameter("select_tipoC");
                    } else {
                        tipoC = "LAN";
                    }
                    String usuario = request.getParameter("txt_usuario");
                    String clave = request.getParameter("txt_contrasena");
                    Red nuevaRed = new Red(tipo, nombre, tipoC, usuario, clave);
                    boolean recibido = true;
                    if (objF.ingresarRed(nuevaRed)) {

                         response.getWriter().print("<script>  alert('se agrego correctamente') </script>");
                        request.getRequestDispatcher("RedCTO?accion=Listar").forward(request, response);
                    } else {
                         response.getWriter().print("<script>  alert('no se puedo agregar la nueva red') </script>");
                        request.getRequestDispatcher("AgregarRed.jsp").forward(request, response);
                    }
                    break;
                case "Editar":
                    Red red = new Red();
                    red.setId(Integer.parseInt(request.getParameter("id")));
                    red=objF.getRed(red);
                    request.setAttribute("red", red);
                    boolean estado=true;
                    request.setAttribute("estado", estado);
                    request.getRequestDispatcher("AgregarRed.jsp?estado=true").forward(request, response);
                    break;
                case "Actulizar":
                    int id= Integer.parseInt(request.getParameter("txt_id"));
                    String nombre2 = request.getParameter("txt_nombreRed");
                    int tipo2 = Integer.parseInt(request.getParameter("select_tipo"));
                    String tipoC2;
                    if (tipo2 == 1) {
                        tipoC2 = request.getParameter("select_tipoC");
                    } else {
                        tipoC2 = "LAN";
                    }
                    String usuario2 = request.getParameter("txt_usuario");
                    String clave2 = request.getParameter("txt_contrasena");
                    Red nuevaRed2 = new Red(id,tipo2, nombre2, tipoC2, usuario2, clave2);
                    boolean recibido2 = true;
                    if (objF.gupdateRed(nuevaRed2)) {
                        PrintWriter out = response.getWriter();
                         out.println("<script>  alert('se agrego correctamente') </script>");
                        request.getRequestDispatcher("RedCTO?accion=Listar").forward(request, response);
                    } else {
                        PrintWriter out = response.getWriter();
                         out.println("<script>  alert('no se puedo agregar la nueva red') </script>");
                         request.setAttribute("red", nuevaRed2);
                        request.getRequestDispatcher("AgregarRed.jsp?estado=true").forward(request, response);
                    }
                    break;
                case "Historial":
                    Electrodomesticos elec = new Electrodomesticos();
                    elec.setConexionActual(Integer.parseInt(request.getParameter("id")));
                    List<Electrodomesticos> listaRedH=objF.TraerConectadosRed(elec);
                    if(listaRedH!=null && listaRedH.size()>0){
                        List<Electrodomesticos> aux = new ArrayList<>();
                         List<Electrodomesticos> listaTotal = objF.TraerElectroHISTRIAL(elec);
                        for(Electrodomesticos eletre: listaRedH){
                            if(eletre.isConexionActiva()==true){
                                aux.add(eletre);
                            }
                        } 
                        List<Red> listasRed = null;
                        listasRed = objF.traerRed();
                        request.setAttribute("listaRedH", aux);
                        request.setAttribute("listaTotal",  listaTotal);
                        request.setAttribute("listasRed", listasRed);
                        request.getRequestDispatcher("HistorialRed.jsp").forward(request, response);
                    }else{
                        
                        request.getRequestDispatcher("HistorialRed.jsp?alerta=true").forward(request, response);
                    }    
                    
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
