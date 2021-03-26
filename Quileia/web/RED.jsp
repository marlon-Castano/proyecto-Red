<%-- 
    Document   : RED
    Created on : 24-Mar-2021, 12:31:33
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row col-xl-6 col-xl-offset-2 custyle">
                <table class="table table-striped custab">
                    <thead>
                    <a href="AgregarRed.jsp" class="btn btn-primary btn-xs pull-right"><b>+</b>Nueva red</a>
                    <tr>
                        <th>ID</th>
                        <th>Nombre de la red</th>
                        <th>Tipo</th>
                        <th>Tipo de Cifrado</th>
                        <th>Usuario</th>
                        <th class="text-center">Action</th>
                    </tr>
                    </thead>
                    <c:forEach var="objR" items="${list}">
                        <tr>

                            <td>${objR.getId()}</td>
                            <td>${objR.getNombreRed()}</td>
                            <c:if test="${objR.getTipo()==1}">
                                <td>Wifi</td>
                            </c:if>
                            <c:if test="${objR.getTipo()==2}">
                                <td>LAN</td>
                            </c:if>
                            <td>${objR.getTipoCifrado()}</td>
                            <td>${objR.getUsuario()}</td>
                            <td class="text-center"><a class='btn btn-info btn-xs' href="RedCTO?menu=Red&accion=Editar&id=${objR.getId()}"><span class="glyphicon glyphicon-edit"></span> Edit</a>
                                <a class='btn btn-primary btn-xs' href="RedCTO?menu=Red&accion=Historial&id=${objR.getId()}"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-calendar" viewBox="0 0 16 16">
                                    <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z"/>
                                    </svg> Historico Red</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>
