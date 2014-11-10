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
import modelo.CursoProfe;
import modelo.DAO;
import modelo.Profesor;
import modelo.excepciones.URLException;

@WebServlet(name = "InformeCurso", urlPatterns = {"/InformeCursoServlet.view"})
public class InformeCurso extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAO dao = new DAO();

//                        String nomb = request.getParameter("nombre");
//                        int id = 0;
            
            String buscarxnombre = request.getParameter("txtBuscarxNombre");
            int id = Integer.parseInt(request.getParameter("id"));
            HttpSession session = request.getSession();
            Profesor profeUp = (Profesor) session.getAttribute("profeUp");
            session.setAttribute("curso", id);
            if (profeUp == null) {
                session.setAttribute("error", new URLException("Debe iniciar sesión para acceder."));
                request.getRequestDispatcher("error.view").forward(request, response);
            }
            String rut = profeUp.getRut();
            String nomb = "";
            List<Curso> curs = new ArrayList<Curso>();
            curs = dao.CargarCursos(rut);
            List<CursoAlumno> lista = dao.listaJavaWeb(id, null);
            
            List<CursoProfe> cursos = dao.listaCursos(rut);
            for (CursoProfe c : cursos) {
                if (c.getId() == id) {
                    nomb = c.getNombre();
                }
            }

            
            if (buscarxnombre != null) {
//                lista = dao.FiltroXNombre(buscarxnombre);
                lista = dao.listaJavaWeb(id, buscarxnombre);
            } else {
                lista = dao.listaJavaWeb(id, null);
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InformeCurso</title>");
            out.println("<link rel='stylesheet' type='text/css' href='css/css4.css'/>");
            out.println("</head>");
            out.println("<body>");
//            out.println("<h1 align='center'>"+buscarxnombre+"</h1>");
            
            out.println("<h1 align='center'>Informe Del Curso De: " + nomb + " </h1>");

          //  out.println("<h1>"+buscarxnombre+"</h1>");
//            out.println("<form  action='InformeCursoServlet.view' method='post'>");
//            out.println("<input type='text' required='required' placeholder='Buscar por nombre o por Rut' name='txtBuscarxNombre'/>");
//            out.println("<input type='submit' value='Buscar'/>");
//            out.println("</form>");
            out.println("<form  action='InformeCursoServlet.view?id="+id+"' method='post'>");
           out.println("<input type='text' required='required' placeholder='Ingrese Nombre' name='txtBuscarxNombre'/>");
            out.println("<input type='submit' value='Buscar por Nombre'/>");
            out.println("</form>");
            out.println("<form  action='InformeCursoServlet.view' method='post' >");
            out.println("<input type='text' required='required' placeholder='Ingrese Rut' name='txtBuscarxRUT'/>");
            out.println("<input type='submit' value='Buscar por RUT'/>");
            out.println("</ form>");
            out.println("<br>");
            out.println("</br>");
            out.println("<table  border='1'  class='tablaDatos'>");
            out.println("<tr>");
            out.println("<th class='titulosTabla' width='100'>RUT</th>");
            out.println("<th class='titulosTabla'  width='200'>Nombre</th>");
            out.println("<th class='titulosTabla'>Promedio</th>");
            out.println("</tr>");
    
            for (CursoAlumno a : lista) {
                out.println("<tr>");
                out.println("<td class='datos'><a href=informenotas.view?idNota=" + a.getId() + "&idCurso=" + id + "&rutAlumno=" + a.getRut() + "&curso=" + nomb + ">" + a.getRut() + "</a>");
                out.println("<td class='datos'>" + a.getNombre() + " " + a.getApePa() + " " + a.getApeMa() + "</td>");
              //  int idAlu=dao.idAlumno(a.getRut());
                out.println("<td class='datos'>" + dao.getPromedio(dao.idAlumno(a.getRut()), id) + "</td>");
                out.println("</tr>");
             //     out.println("<h1 align='center'>"+dao.idAlumno(a.getRut()) +"</h1>");
            }
            out.println("</table>");
            out.println("<br>");
            out.println("</br>");
            out.println("<a class='titulosTabla'  href='menu.view'>Volver al Menú</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(InformeCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(InformeCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}




    