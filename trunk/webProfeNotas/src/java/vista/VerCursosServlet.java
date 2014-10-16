package vista;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.DAO;
import modelo.Profesor;
import modelo.excepciones.URLException;

/**
 *
 * @author Fco
 */
@WebServlet(name = "VerCursosServlet", urlPatterns = {"/cursos.view"})
public class VerCursosServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            Profesor profeUp = (Profesor) session.getAttribute("profeUp");

            if (profeUp == null) {
                session.setAttribute("error", new URLException("Debe iniciar sesión para acceder."));
                request.getRequestDispatcher("error.view").forward(request, response);
            }
            DAO dao = new DAO();
            String nombre = dao.dameTuNombre(profeUp);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VerCursosServlet</title>");        
            out.println("<link rel='stylesheet' type='text/css' href='css/css3.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Cursos de: "+nombre+"</h1>");
            
            
            
            
            
//            if(nombre.equalsIgnoreCase("Patricio Nicolás Pérez")){ 
//            out.println("<form action='cursoJava.view' method='post'>");
//            out.println("<input type='submit' value='Java Web' class='botonCurso'/></br>");
//            
//            
//            out.println("</form>");
//            out.println("<form action='#' method='post'>");
//            out.println("<input type='submit' value='Base de datos I' class='botonCurso'/></br>");
//            out.println("</form>");    
//            out.println("<form action='#' method='post'>");
//            out.println("<input type='submit' value='SO' class='botonCurso'/></br>");
//            out.println("</form>");        
//            }else{
//            out.println("</form>");    
//            out.println("<form action='#' method='post'>");
//            out.println("<input type='submit' value='Introducción a la programación' class='botonCurso'/></br>");
//            out.println("</form>");  
//            out.println("</form>");    
//            out.println("<form action='algoritmos.view' method='post'>");
//            out.println("<input type='submit' value='Algoritmos' class='botonCurso'/></br>");
//            out.println("</form>");  
//            }
                
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException ex) {
            Logger.getLogger(VerCursosServlet.class.getName()).log(Level.SEVERE, null, ex);
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
