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
import modelo.Alumno;
import modelo.Curso;
import modelo.CursoAlumno;
import modelo.CursoProfe;
import modelo.DAO;
import modelo.Nota;

@WebServlet(name = "InformeNotas", urlPatterns = {"/informenotas.view"})
public class InformeNotas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String rut = request.getParameter("rutAlumno");

            int idCurso = Integer.parseInt(request.getParameter("idCurso"));
            String nombr = request.getParameter("curso");
            DAO da = new DAO();

            List<CursoAlumno> lista = da.listaJavaWeb(idCurso, null);
            List<Nota> notas = da.listaNotas(rut, idCurso);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel='stylesheet' type='text/css' href='css/css4.css'/>");
            out.println("<title>Servlet InformeNotas</title>");
            out.println("</head>");
            out.println("<body>");

            for (CursoAlumno c : lista) {

                if (rut.equals(c.getRut())) {

                    out.println("<h1>Informe Notas Alumno</h1>");
                    out.println("<h1>" + c.getNombre() + " " + c.getApePa() + " " + c.getApeMa() + "</h1>");
                    out.println("<h1>Curso</h1>");

                }
            }
            out.println("<h1>" + nombr + "</h1>");
            List<CursoProfe> cursos = da.listaCursos(rut);
//            for(CursoProfe c : cursos){
//                if(c.getId() == idCurso){
//               String    nomb = c.getNombre();
//                
//                out.println("<h1>" + nomb + "</h1>");
//              
//                }
//            }

            out.println("<table  border='1' align='center' class='tablaDatos'>");
            out.println("<tr>");
            out.println("<th class='titulosTabla' width='30'>N°</th>");
            out.println("<th class='titulosTabla' width='30' >Porcentaje</th>");
            out.println("<th class='titulosTabla' width='30'>Nota</th>");
            out.println("<th class='titulosTabla' width='300'>Comentario</th>");
            out.println("<th class='titulosTabla' width='100'>Editar</th>");

            out.println("</tr>");

            for (Nota n : notas) {
                //   out.println("<table align='center' class='tablaNotas'>");
                out.println("<tr>");
                out.println("<th>" + n.getId() + "</th>");
                out.println("<th>" + n.getPorcentaje() + "</th>");
                out.println("<th>" + n.getNota() + "</th>");
                out.println("<th>" + n.getDescripcion() + "</th>");
                out.println("<th><a href=cambiarnota.view?idNota=" + n.getId() + "&idCurso=" + idCurso + "&rutAlumno=" + rut + "&nomcur="+nombr+">Editar</a></th>");

                out.println("</tr>");

            }
            out.println("</table>");

            //   out.println("<h1>Alumno Sin Notas en Curso</h1>");
            out.println("<th><a href=cursosview.view?idCurso=" + idCurso + ">Volver Al Curso</a>");

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
            Logger.getLogger(InformeNotas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(InformeNotas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
