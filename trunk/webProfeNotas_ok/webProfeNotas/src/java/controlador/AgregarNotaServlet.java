/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

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
import modelo.DAO;
import modelo.Nota;

/**
 *
 * @author Fco
 */
@WebServlet(name = "AgregarNotaSerlvlet", urlPatterns = {"/agregarNota.do"})
public class AgregarNotaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
             HttpSession session = request.getSession();
          int cursoId = (int)session.getAttribute("curso");
          int nota;
        try {
            DAO dao = new DAO();
            List<CursoAlumno> curso = dao.curso(cursoId);
            for(CursoAlumno a : curso){
                nota = Integer.parseInt(request.getParameter(a.getRut()));
                int porcent = Integer.parseInt(request.getParameter("porcentaje"));
                String comentario = request.getParameter("comentario");
                int idAlumno = a.getId();
               
                Nota n = new Nota(nota,porcent,comentario,cursoId,idAlumno);
                System.out.println(n);
                dao.addNota(n);
               
            }
             request.getRequestDispatcher("cursos.view").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AgregarNotaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
