//
//package vista;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import modelo.DAO;
//import modelo.CursoAlumno;
//import modelo.Nota;
//
///**
// *
// * @author cjerez
// */
//@WebServlet(name = "CambiarNota", urlPatterns = {"/cambiarnota.view"})
//public class CambiarNota extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     * @throws java.sql.SQLException
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException, SQLException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            
//            String rut = request.getParameter("rut");                       
//            int idCurso = Integer.parseInt(request.getParameter("idNota"));
//            
//            DAO d = new DAO();
//            List<CursoAlumno> lista = d.listaJavaWeb(idCurso);
//            List<Nota>notas=d.listaNotas(rut, idCurso);
//          
//                        
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CambiarNota</title>");            
//            out.println("</head>");
//            out.println("<body>");
//                   
//            (CursoAlumno c : lista) {
//                  
//                  if(rut.equals(c.getRut())){
//                   
//                out.println("<h1>Cambio de Notas</h1>");
//                out.println("<h1>" + c.getNombre() + " " + c.getApePa()+" " + c.getApeMa()+  "</h1>");
//                out.println("<h1>Curso</h1>");
//                out.println("<h1>" + c.getCurso() + "</h1>");
//               }
//            
//            
//            
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            processRequest(request, response);
//        } catch (SQLException ex) {
//            Logger.getLogger(CambiarNota.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            processRequest(request, response);
//        } catch (SQLException ex) {
//            Logger.getLogger(CambiarNota.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
