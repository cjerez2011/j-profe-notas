package vista;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.CursoAlumno;
import modelo.CursoProfe;
import modelo.DAO;
import modelo.Profesor;
import modelo.excepciones.URLException;

/**
 *
 * @author home
 */
@WebServlet(name = "CursosView", urlPatterns = {"/cursosview.view"})
public class CursosView extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int id = Integer.parseInt(request.getParameter("idCurso"));
            HttpSession session = request.getSession();
            Profesor profeUp = (Profesor) session.getAttribute("profeUp");

            session.setAttribute("curso", id);

            if (profeUp == null) {
                session.setAttribute("error", new URLException("Debe iniciar sesión para acceder."));
                request.getRequestDispatcher("error.view").forward(request, response);
            }
            String rut = profeUp.getRut();
            String nomb = "";

            DAO dao = new DAO();

            List<CursoAlumno> alumno = dao.curso(id);
            List<CursoProfe> cursos = dao.listaCursos(rut);
            for (CursoProfe c : cursos) {
                if (c.getId() == id) {
                    nomb = c.getNombre();
                }
            }

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>profeNotas</title>");
            out.println("<link rel='stylesheet' type='text/css' href='css/css4.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Curso:" + nomb + " <a href='InformeCursoServlet.view?id=" + id + "'>ver informe</a></h2>");

            out.println("<form action='agregarNota.do' method='post'>");
            out.println("<table border='1' align='center' class='tablaDatos'>");
            out.println("<tr>");
            out.println("<th class='titulosTabla'>id</th>");
            out.println("<th class='titulosTabla'>Rut</th>");
            out.println("<th class='titulosTabla'>Nombre</th>");
            out.println("<th class='titulosTabla'>Apellido paterno</th>");
            out.println("<th class='titulosTabla'>Apellido materno</th>");
            out.println("<th class='titulosTabla'>Nota % <input type='text' size='4' name='porcentaje' required='required'/></th>");
            out.println("</tr>");
            int cont = 0;
            for (CursoAlumno c : alumno) {
                out.println("<tr>");

                out.println("<td class='datos'>" + c.getId() + "<input type='hidden' name='idAlumno' value='" + c.getId() + "'/></td>");
                out.println("<td class='datos'>" + c.getRut() + "</td>");
                out.println("<td class='datos'>" + c.getNombre() + "</td>");
                out.println("<td class='datos'>" + c.getApePa() + "</td>");
                out.println("<td class='datos'>" + c.getApeMa() + "</td>");
                out.println("<td class='datos'><input type='number' name='" + c.getRut() + "' required='required' step='0.01'/></td>");
                cont++;
                out.println("</tr>");
            }
            out.println("<h3>Alumnos: " + cont + "</h3>");
            out.println("<table align='center'>");
            out.println("<tr>");
            out.println("</tr>");
            out.println("<td>");
            out.println("<h4>Agregue descripción: <img src='imagenes/Services.png'></h4>");
            out.println("</td>");
            out.println("<tr>");
            out.println("<td>");
            out.println("<textarea rows='5' cols='70' name='comentario' placeholder='ej:prueba1,taller1,etc...' style='border-color:black'>");
            out.println("</textarea>");
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>");
            out.println("<input type='submit' value='Agregar Nota' id='btn'/>");
            out.println("</td>");
            out.println("</tr>");
            out.println("</table>");

            out.println("</table>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException ex) {
            Logger.getLogger(CursosView.class.getName()).log(Level.SEVERE, null, ex);
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
