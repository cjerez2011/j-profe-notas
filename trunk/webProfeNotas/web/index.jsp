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
        <title>ProfeNotas</title>
    </head>
    <body>
        <form action="validateProfe.do" method="post">
            <table  align="center" border="0" style="padding-top: 180px">
                <tr>
                    <td>
                        <input type="text" name="txtRun" placeholder="Run" required="required"/> 
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="password" name="txtPass" placeholder="Contraseña" required="required"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Iniciar sesión" id="btn"/>
                    </td>
                </tr>
            </table>
        </form>

    </body>
</html>
