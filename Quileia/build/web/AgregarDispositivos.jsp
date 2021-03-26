<%-- 
    Document   : AgregarDispositivos
    Created on : 25-Mar-2021, 12:31:55
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
        <title>Agregar Dispositivos</title>
    </head>
    <body onload="todo()">
        <div class="container">
            <div class="row">
                <div class="col-sm-14">
                    <h1 >Dispositivo</h1>
                </div>
            </div>
            <div class="row">
                <div class="card col-sm-12">
                    <div class="card-body">
                        <h2 >Ingresar Dispositivo Electronico</h2>
                        <form action ="DispositivosCTO?menu=Electrinicos" method="POST">
                            <div class="form-group">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="txt_nombre">MAC</label>
                                </div>
                                <% boolean estado = Boolean.parseBoolean(request.getParameter("estado"));

                                if (estado) {
                            %> 
                                <input type="text" class="form-control" name="txt_Mac1" required value="${dis.getMac()}" maxlength="17" placeholder="A2:3B:1D:54:6B:32" readonly>
                               <%
                                   }else{
                               %>
                               <input type="text" class="form-control" name="txt_Mac" required value="${dis.getMac()}" maxlength="17" placeholder="A2:3B:1D:54:6B:32" >
                               <%}%>
                            </div>    
                            

                            <div class="form-group">
                                <label class="inp" for="txt_tipo">Tipo</label>
                                <input type="text" value="${dis.getTipo()}" class="form-control" name="txt_tipo" id="txt_tipo" placeholder="Computador">
                            </div>
                             <div class="form-group">
                                <label class="inp" for="txt_ip">IP</label>
                                <input type="text" value="${dis.getIp()}" class="form-control" minlength="10" name="txt_Ip" id="txt_Ip" placeholder="196.5.4.44">
                            </div>
                            <div class="form-group" id='conexionA'>
                                <label class="inp" for="txt_tipoC">Conectarse</label>
                                <input type="hidden" value="${dis.getConexionActual()}" name="txt_ConexionActual" id="txt_ConexionActual">
                                <select name="select_ConexionActual" class="form-control"  id="ConexionActual" required >
                                    <c:forEach var="objD" items="${list}">
                                        <option value='${objD.getId()}' >${objD.getNombreRed()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <% 

                                if (estado) {
                            %>    
                            <input type="submit" class="btn btn-warning" name="accion" value="Actulizar">
                            <%} else {
                            %>
                            <input type="submit" class="btn btn-success" name="accion" value="Agergar">
                            <%}%>        

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
        <script>

        function todo() {
            tipo();
        }
        function tipo() {
            const matedb = document.getElementById('txt_ConexionActual').value
            const mate = document.getElementById('ConexionActual')
            for (var i = 0; i < mate.length; i++) {
                const opt = mate[i];
                if (opt.value == matedb) {

                    opt.selected = 'selected'
                }

            }
        }
    </script> 
</html>
