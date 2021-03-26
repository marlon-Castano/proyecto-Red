<%-- 
    Document   : AgregarRed
    Created on : 24-Mar-2021, 12:43:36
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%

%>
<!DOCTYPE html>
<html>
    <head>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Red</title>
    </head>
    <body onload="todo()">
        <div class="container">
            <div class="row">
                <div class="col-sm-14">
                    <h1 >Red</h1>
                </div>
            </div>
            <div class="row">
                <div class="card col-sm-12">
                    <div class="card-body">
                        <h2 >Ingresar la Red</h2>
                        <form action ="RedCTO?menu=Red" method="POST">
                            <div class="form-group">
                                <label class="inp" for="txt_nombre">Nombre de la red</label>
                                <input type="hidden" value="${red.getId()}" name="txt_id">
                                <input type="text" class="form-control" name="txt_nombreRed" required value="${red.getNombreRed()}" placeholder="Paco">
                            </div>

                            <div class="form-group">
                                <label class="inp" for="txt_tipo">tipo</label>
                                <input type="hidden" value="${red.getTipo()}" name="txt_tipo" id="txt_tipo">
                                <select class="form-control" name="select_tipo" id="tipo" onChange="validar()" required>
                                    <option value="1">Wifi</option>
                                    <option value="2">LAN</option>
                                </select>
                            </div>
                            <div class="form-group" id='tipoC'>
                                <label class="inp" for="txt_tipoC">Tipo de CIFRADO</label>
                                <input type="hidden" value="${red.getTipoCifrado()}" name="txt_tipoCs" id="txt_tipoC">
                                <select name="select_tipoC" class="form-control"  id="tipoc" required >
                                    <option value='WEP' >WEP</option>
                                    <option value="WPA">WPA</option>
                                    <option value="WPA2">WPA2</option>                                    
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="inp" for="txt_n_iden">usuario</label>
                                <input type="text" class="form-control" name="txt_usuario" required value="${red.getUsuario()}" placeholder="ramirez@correo">
                            </div>
                            <div class="form-group">
                                <label class="inp" for="txt_contrase">Contraseña</label>
                                <input  type="password" class="form-control" name="txt_contrasena" required value="${red.getContrasena()}" >
                            </div>
                            <% boolean estado =Boolean.parseBoolean(request.getParameter("estado")); 

                                if(estado){
                                %>    
                                <input type="submit" class="btn btn-warning" name="accion" value="Actulizar">
                            <%}else {
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
            tipoC();
        }
        function tipo() {
            const matedb = document.getElementById('txt_tipo').value
            const mate = document.getElementById('tipo')
            for (var i = 0; i < mate.length; i++) {
                const opt = mate[i];
                if (opt.value == matedb) {

                    opt.selected = 'selected'
                    if (opt.value == 2) {
                        validar();
                    }
                }

            }
        }
        function tipoC() {
            const matedb = document.getElementById('txt_tipoC').value
            const mate = document.getElementById('tipoc')
            for (var i = 0; i < mate.length; i++) {
                const opt = mate[i];
                if (opt.value == matedb) {

                    opt.selected = 'selected'
                }

            }
        }

        function validar() {
            var tipo = document.getElementById("tipo");
            let tipoC = document.getElementById("tipoC")
            if (tipo.value == 2) {
                tipoC.style.display = "none";
            } else {
                tipoC.style.display = "block";
            }
        }


    </script> 
</html>
