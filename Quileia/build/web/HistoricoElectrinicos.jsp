<%-- 
    Document   : HistoricoElectrinicos
    Created on : 25-Mar-2021, 11:50:37
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
        <title>Historico Dispositivos Electronico</title>
    </head>
    <body>
        <h1>HISTORIAL DISPOSITIVO ELECTRONICO</h1>
    <c:if test="${listas.get(listas.size()-1).isConexionActiva()==true}">
        <h2>El Dispositivo Se Encuntra Activo</h2>
    </c:if>
    <c:if test="${listas.get(listas.size()-1).isConexionActiva()==false}">
        <h2>El Dispositivo Se Encuntra Desactivo</h2>
    </c:if>

    
    <div class="container">
        <div class="row col-xl-6 col-xl-offset-2 custyle">
            <table class="table table-striped custab">
                <thead>
                <a class='btn btn-warning btn-xs pull-right' href="DispositivosCTO?menu=Electrinicos&accion=Estado&id=${listas.get(0).getMac()}&estadoD=${listas.get(listas.size()-1).isConexionActiva()}"> 
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-circle" viewBox="0 0 16 16">
                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                    <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                    </svg> Cambiar Estado De Conexion</a>
                <tr>
                    <th>Mac</th>
                    <th>Tipo</th>
                    <th>Ip</th>
                    <th>Conexión Actual</th>
                    <th>Estado conexion</th>
                    <th>Fecha</th>
                </tr>
                </thead>
                <c:forEach var="objR" items="${listas}">
                    <tr>

                        <td>${objR.getMac()}</td>
                        <td>${objR.getTipo()}</td>
                        <td>${objR.getIp()}</td>
                        <c:forEach var="objl" items="${list}">
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
</html>
