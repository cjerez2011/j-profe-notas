/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Curso;
import modelo.CursoAlumno;
import modelo.DAO;
import modelo.Profesor;
import modelo.excepciones.URLException;

/**
 *
 * @author usuario
 */
@WebServlet(name = "InformeCurso", urlPatterns = {"/InformeCurso.view"})
public class InformeCurso extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            

 
            DAO dao = new DAO();
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
            
            List<Curso> cur = new ArrayList<Curso>();
 
            cur = dao.CargarCursos(rut);
 
            
            List<CursoAlumno> lista = dao.listaJavaWeb(id);
            
              String buscarxnombre = request.getParameter("txtBuscarxNombre");
              
              if(buscarxnombre !=null){
               lista = dao.FiltroXNombre(buscarxnombre);
           }else{
            lista = dao.listaJavaWeb(id);
            }
            
            
            
//            String nomb = request.getParameter("nombre");
//            int id = 0;


            
            
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InformeCurso</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>InformeCurso "+cur+" </h1>");
            
            
               out.println("<form  action='InformeCurso.view' method='post'>");
 
                    out.println("<input type='text' required='required' placeholder='Ingrese Nombre' name='txtBuscarxNombre'/>");
 
                    out.println("<input type='submit' value='Buscar por Nombre'/>");
 
                out.println("</form>");
                
                
                               out.println("<form  action='InformeCurso.view' method='post'>");
 
                    out.println("<input type='text' required='required' placeholder='Ingrese Rut' name='txtBuscarxRUT'/>");
 
                    out.println("<input type='submit' value='Buscar por RUT'/>");
 
                out.println("</form>");
            
            
            
             
                  out.println("<table  class='tablaDatos'>");
 
                    out.println("<tr>");
 
                        out.println("<th class='titulosTabla'>RUT</th>");
 
                        out.println("<th class='titulosTabla'>Nombre</th>");
 
                        out.println("<th class='titulosTabla'>Promedio</th>");
 
                    out.println("</tr>");
 
                    
 
                    
 
                    
 
                for(CursoAlumno a : lista){
 
                     out.println("<tr>");
 
                        out.println("<td class='datos'>"+a.getRut()+"</td>");
 
                        out.println("<td class='datos'>"+a.getNombre()+"</td>");
 
                        out.println("<td class='datos'>"+null+"</td>");
 
                       
 
                    out.println("</tr>");
 
                }
 
                out.println("</table>");
 
                out.println("<a href='menu.view'>Volver </a>");
 

            out.println("</body>");
            out.println("</html>");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(InformeCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(InformeCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
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
