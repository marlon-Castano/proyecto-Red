<%-- 
    Document   : DispositivosElectronicos
    Created on : 25-Mar-2021, 11:19:57
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
        <title>Dispositivos Electronicos</title>
    </head>
    <body>
        <div class="container">
            <div class="row col-xl-6 col-xl-offset-2 custyle">
                <table class="table table-striped custab">
                    <thead>
                    <a href="DispositivosCTO?menu=Electrinicos&accion=AgregarD" class="btn btn-primary btn-xs pull-right"><b>+</b>Nuevo Disposito Electronico</a>
                    <tr>
                        <th>Mac</th>
                        <th>Tipo</th>
                        <th>Ip</th>
                        <th>Conexión Actual</th>
                        <th class="text-center">Action</th>
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
                            <td class="text-center"><a class='btn btn-info btn-xs' href="DispositivosCTO?menu=Electrinicos&accion=Editar&id=${objR.getMac()}"><span class="glyphicon glyphicon-edit"></span> Edit</a> 
                                
                                <a class='btn btn-primary btn-xs' href="DispositivosCTO?menu=Electrinicos&accion=HistoriaD&mac=${objR.getMac()}"> 
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-calendar" viewBox="0 0 16 16">
                                    <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z"/>
                                    </svg> Historico Dispositivo electronico
                                </a>
                            </td>

                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
    <%
        boolean problema=Boolean.parseBoolean(request.getParameter("problema"));
        if(problema==true){
    %>
    <script>
        alert("cambio de estado no realizado");
    </script>    
    <%    
        }
    %>
</html>
