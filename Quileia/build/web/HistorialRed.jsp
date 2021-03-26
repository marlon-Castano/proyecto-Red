<%-- 
    Document   : HistorialRed
    Created on : 26-Mar-2021, 13:32:33
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Historial Red</title>
    </head>
    <body>
        <h1>Historial Red</h1>
        <div class="container">
            <div class="row col-xl-6 col-xl-offset-2 custyle">
                <table class="table table-striped custab">
                    
                    <thead>
                        <h2>Dispotivos Conectados en el momento</h2>
                    </thead>
                    <thead>
                    <tr>
                        <th>Mac</th>
                        <th>Tipo</th>
                        <th>Ip</th>
                        <th>Conexión Actual</th>
                        <th>Estado conexion</th>
                        <th>Fecha</th>
                    </tr>
                    </thead>
                    <c:forEach var="objR" items="${listaRedH}">
                        <tr>

                            <td>${objR.getMac()}</td>
                            <td>${objR.getTipo()}</td>
                            <td>${objR.getIp()}</td>
                        <c:forEach var="objl" items="${listasRed}">
                            <c:if test="${objl.getId()==objR.getConexionActual()}">
                                <td>${objl.getNombreRed()}</td>
                            </c:if>
                        </c:forEach>
                        <c:if test="${objR.isConexionActiva()==true}">
                            <td>activa</td>
                        </c:if>
                        <c:if test="${objR.isConexionActiva()==false}">
                            <td>desactiva</td>
                        </c:if>
                        <td>${objR.getFecha()}</td>    

                        </tr>
                    </c:forEach>
                </table>
                <table class="table table-striped custab">       
                <thead>
                    <h2>Historial Dispotivos conectados</h2>
                </thead>
                <thead>
                <tr>
                    <th>Mac</th>
                    <th>Tipo</th>
                    <th>Ip</th>
                    <th>Conexión Actual</th>
                    <th>Estado conexion</th>
                    <th>Fecha</th>
                </tr>
                </thead>
                <c:forEach var="objR" items="${listaTotal}">
                    <tr>

                        <td>${objR.getMac()}</td>
                        <td>${objR.getTipo()}</td>
                        <td>${objR.getIp()}</td>
                    <c:forEach var="objl" items="${listasRed}">
                        <c:if test="${objl.getId()==objR.getConexionActual()}">
                            <td>${objl.getNombreRed()}</td>
                        </c:if>
                    </c:forEach>
                    <c:if test="${objR.isConexionActiva()==true}">
                        <td>activa</td>
                    </c:if>
                    <c:if test="${objR.isConexionActiva()==false}">
                        <td>desactiva</td>
                    </c:if>
                    <td>${objR.getFecha()}</td>    

                    </tr>
                </c:forEach>
                </table>
            </div>
        </div>
    </body>
   
<% boolean estado= Boolean.parseBoolean(request.getParameter("alerta"));
    if(estado==true){
  %>
        <script>alert( "no hay dispositivos con esta red")</script>
  <% } %>
</html>
