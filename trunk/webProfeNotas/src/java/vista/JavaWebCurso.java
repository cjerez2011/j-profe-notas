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
import modelo.CursoAlumno;
import modelo.DAO;

/**
 *
 * @author Fco
 */
@WebServlet(name = "JavaWebCurso", urlPatterns = {"/cursoJava.view"})
public class JavaWebCurso extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DAO dao = new DAO();
            int id=0;
            
            List<CursoAlumno>lista = dao.listaJavaWeb(id);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet JavaWebCurso</title>");            
            out.println("</head>");
            out.println("<body>");
            
            
            out.println("<table align='center' class='tablaDatos'>");    
            out.println("<tr>");
            out.println("<th class='titulosTabla'>Rut</th>");
            out.println("<th class='titulosTabla'>Nombre</th>");
            out.println("<th class='titulosTabla'>Apellido paterno</th>");
            out.println("<th class='titulosTabla'>Apellido materno</th>");
            out.println("<th class='titulosTabla'>Notas</th>");
            out.println("<th class='titulosTabla'>Curso</th>");
            out.println("</tr>");
            
          for(CursoAlumno c : lista){
                out.println("<tr>");
                out.println("<td class='datos'>" + c.getRut() + "</td>");
                out.println("<td class='datos'>" + c.getNombre() + "</td>");
                out.println("<td class='datos'>" + c.getApePa() + "</td>");
                out.println("<td class='datos'>" + c.getApeMa() + "</td>");
                out.println("<td class='datos'>" + c.getNota() + "</td>");
                out.println("<td class='datos'>" + c.getCurso() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException ex) {
            Logger.getLogger(JavaWebCurso.class.getName()).log(Level.SEVERE, null, ex);
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
