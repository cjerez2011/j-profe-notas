package controlador;

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
import modelo.excepciones.SessionFailException;

/**
 *
 * @author Fco
 */
@WebServlet(name = "ValidarSesionProfe", urlPatterns = {"/validateProfe.do"})
public class ValidarSesionProfe extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String run, pass;
            run = request.getParameter("txtRun");
            pass = request.getParameter("txtPass");
            Profesor up = new Profesor(run,pass);
            DAO dao = new DAO();
//            int conexion = dao.exist(run, pass);
            Profesor profe = dao.exist(up);
            HttpSession session = request.getSession();
            session.setAttribute("profeUp", profe);
            request.getRequestDispatcher("menu.view").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ValidarSesionProfe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SessionFailException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex);
            request.getRequestDispatcher("error.view").forward(request, response);
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
