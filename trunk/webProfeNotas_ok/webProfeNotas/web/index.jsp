<%-- 
    Document   : index
    Created on : 09-10-2014, 05:35:44 PM
    Author     : Fco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' type='text/css' href='css/css.css'>
        <title>ProfeNotas</title>
    </head>
    <body>
        <div class="container">
            <div class="login">
                <h1>Iniciar Sesión</h1>
                <form action="validateProfe.do" method="post">
                    <p><input type="text" name="txtRun" value="" placeholder="Rut" required="required"></p>
                    <p><input type="password" name="txtPass" value="" placeholder="Password" required="required"></p>
                    <p class="remember_me">
                        <label>
                            <input type="checkbox" name="remember_me" id="remember_me">
                            Recordarme en esta computadora
                        </label>
                    </p>
                    <p class="submit"><input type="submit" name="commit" value="Login"></p>
                </form>
            </div>

            <div class="login-help">
                <p>¿Olvidó su contraseña? <a href="#">Click aquí para resetearla</a>.</p>
            </div>
        </div>

    </body>
</html>
