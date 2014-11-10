
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
import modelo.Alumno;
import modelo.DAO;
import modelo.Curso;
import modelo.CursoAlumno;
import modelo.CursoProfe;
import modelo.Nota;
import modelo.Profesor;
import modelo.excepciones.URLException;

/**
 *
 * @author home
 */
@WebServlet(name = "CambiarNota", urlPatterns = {"/cambiarnota.view"})
public class CambiarNota extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession session = request.getSession();
            Profesor profeUp = (Profesor) session.getAttribute("profeUp");

            if (profeUp == null) {
                session.setAttribute("error", new URLException("Debe iniciar sesión para acceder."));
                request.getRequestDispatcher("error.view").forward(request, response);
            }
            
            String rut=request.getParameter("rutAlumno");
            int idcurso=Integer.parseInt(request.getParameter("idCurso"));
            String nombr = request.getParameter("curso");
            int idnota=Integer.parseInt(request.getParameter("idNota"));
            String nomCurs=request.getParameter("nomcur").toString();
            DAO d = new DAO();

            List<CursoAlumno> lista = d.listaJavaWeb(idcurso, null);
            List<Nota> notas = d.listaNotas(rut, idnota);
                        
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel='stylesheet' type='text/css' href='css/css4.css'/>");
            out.println("<title>Servlet InformeNotas</title>");
            out.println("</head>");
            out.println("<body>");
            
          //    out.println("<h1>" + nomCurs + "</h1>");

            for (CursoAlumno c : lista) {

                if (rut.equals(c.getRut())) {

                    out.println("<h1>Cambiar Notas Alumno</h1>");
                    out.println("<h1>" + c.getNombre() + " " + c.getApePa() + " " + c.getApeMa() + "</h1>");
                    out.println("<h1>Curso </h1>");
                    

                }
            }
                    out.println("<h1>" + nomCurs + "</h1>");
                    //List<CursoProfe> cursos = d.listaCursos(rut);        
            for (Nota n : notas) {
                //out.println("<table align='center' class='tablaNotas'>");
                //out.println("<h2>Porcentaje</h2>");
                out.println("<h2>Porcentaje Nota: " + n.getPorcentaje() + "</h2>");
                out.println("<h3>Nota <input type='text' size='4' name='nota' required='required' placeholder='"+n.getNota()+"'></h2>");
                //out.println("<h3>" + n.getNota() + "</h3>");
                //out.println("<h4>" + n.getDescripcion() + "</h4>");
                out.println("<h4>Agregue comentario: <img src='imagenes/Services.png'></h4>");
                out.println("<textarea rows='5' cols='55' name='comentario' placeholder='Ingrese comentario, etc...' style='border-color:black'>");
                out.println("</textarea>");
                out.println("<input type='submit' value='Cambiar Nota' id='btn'/>");
                }
           
                //out.println("<a href=informenotas?idCurso=" + idcurso + ">Volver Al Informe de Notas</a>");
                //out.println("<a href=informenotas?idNota=" + idcurso + ">1&idCurso=2&rutAlumno=17687328-0&curso=Poo + ">Volver Al Informe de Notas</a>");
                
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
            Logger.getLogger(CambiarNota.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CambiarNota.class.getName()).log(Level.SEVERE, null, ex);
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
