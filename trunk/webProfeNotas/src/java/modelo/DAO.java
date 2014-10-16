package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.excepciones.SessionFailException;

/**
 *
 * @author Fco
 */
public class DAO {

    private Conexion con;
    public List<Profesor> profes;
    private List<Alumno> alumnos;

    public DAO() throws SQLException {
        con = new Conexion("localhost", "colegio", "root", "mysql");
        cargarProfes();
    }

//METODO PARA CARGAR A LOS PROFESORES DE LA BASE DE DATOS
    public void cargarProfes() {
        profes = new ArrayList<>();
        String rut, nombre, apePaterno, apeMaterno, sexo, clave;
        int edad;

        try {
            String select = "SELECT * FROM profesor;";

            con.sentencia = con.conexion.createStatement();
            con.tablaResultado = con.sentencia.executeQuery(select);

            while (con.tablaResultado.next()) {
                rut = con.tablaResultado.getString("rut");
                nombre = con.tablaResultado.getString("nombre");
                apePaterno = con.tablaResultado.getString("ape_pat");
                apeMaterno = con.tablaResultado.getString("ape_mat");
                edad = con.tablaResultado.getInt("edad");
                sexo = con.tablaResultado.getString("sexo");
                clave = con.tablaResultado.getString("clave");

                Profesor profe = new Profesor(rut, nombre, apePaterno, apeMaterno, edad, sexo, clave);
                profes.add(profe);
            }
            con.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    // METODO PARA INICIAR SESION DEVUELVE UN ENTERO
//    public int exist(String rut,String pass) throws SessionFailException{
//        int val = 0;
//        try {
//String select = "select * from profesor where clave = AES_ENCRYPT('"+pass+"','llave') and rut = '"+rut+"'";
//            
//          
//            con.sentencia = con.conexion.createStatement();
//            con.tablaResultado = con.sentencia.executeQuery(select);
//             while (con.tablaResultado.next()) {
//                val++;
//            }
//             if(val!=0){
//                for(Profesor p : profes){
//                    if(p.getRut().equalsIgnoreCase(rut)){
//                        System.out.println(p.getNombre());
//                    }
//                }
//               return val;
//             }
//             con.sentencia.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        throw new SessionFailException("Sesion incorrecta");
//    }
    public Profesor exist(Profesor profe) throws SessionFailException{
String select = "select * from profesor where clave = AES_ENCRYPT('"+profe.getClave()+"','llave') and rut = '"+profe.getRut()+"'";
        try {
            
            con.sentencia = con.conexion.createStatement();
            con.tablaResultado = con.sentencia.executeQuery(select);
                 while (con.tablaResultado.next()) {
                 return profe;
            }
        con.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
           throw new SessionFailException("Sesion incorrecta");
    }
    public String dameTuNombre(Profesor p){
        String nombre;
        for(Profesor profe : profes){
            if(p.getRut().equalsIgnoreCase(profe.getRut())){
               return nombre = profe.getNombre()+" "+profe.getApellidoPaterno();
            }
        }
        return null;
    }
    public List listaJavaWeb(){
String select = "select a.rut, a.nombre, a.ape_pat, a.ape_mat, b.nota, c.nombre as 'Curso'  from alumno a, notas b, curso c where b.alumno = a.id  and b.curso = c.id and c.id = 1";
        List<CursoAlumno>lista = new ArrayList<>();
            try {
            
            con.sentencia = con.conexion.createStatement();
            con.tablaResultado = con.sentencia.executeQuery(select);
            while(con.tablaResultado.next()){
            String rut = con.tablaResultado.getString("rut");
            String nombre = con.tablaResultado.getString("nombre");
            String apeP = con.tablaResultado.getString("ape_pat");
            String apeM = con.tablaResultado.getString("ape_mat");
            int nota = con.tablaResultado.getInt("nota");
            String curso = con.tablaResultado.getString("curso");
            CursoAlumno c = new CursoAlumno(rut,nombre,apeP,apeM,nota,curso);
            lista.add(c);
            }
            con.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;    
    }
    public List listaAlgoritmos(){
        String select = "select a.rut, a.nombre, a.ape_pat, a.ape_mat, b.nota, c.nombre as 'Curso'  from alumno a, notas b, curso c where b.alumno = a.id  and b.curso = c.id and c.id = 5";
        List<CursoAlumno>lista = new ArrayList<>();
            try {
            
            con.sentencia = con.conexion.createStatement();
            con.tablaResultado = con.sentencia.executeQuery(select);
            while(con.tablaResultado.next()){
            String rut = con.tablaResultado.getString("rut");
            String nombre = con.tablaResultado.getString("nombre");
            String apeP = con.tablaResultado.getString("ape_pat");
            String apeM = con.tablaResultado.getString("ape_mat");
            int nota = con.tablaResultado.getInt("nota");
            String curso = con.tablaResultado.getString("curso");
            CursoAlumno c = new CursoAlumno(rut,nombre,apeP,apeM,nota,curso);
            lista.add(c);
            }
            con.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;  
    }
    
//    public List<Alumno> cargarAlumnosCurso(int curso) {
// 
//        alumnos = new ArrayList<>();
// 
//        String rut, nombre, apePaterno, apeMaterno ,cur;
// 
//        int  nota;
// 
//   Curso  curs=new Curso();
// 
//   Nota not=new Nota();
// 
//        try {
// 
//            String select = "select   a.rut, a.nombre, a.ape_pat, a.ape_mat, b.nota, c.nombre as 'Curso' \n"+
// 
//                    " from alumno a, notas b, curso c" +
// 
//"where b.alumno = a.id  and b.curso = c.id and c.id = "+curso+"";
// 
//
// 
//            con.sentencia = con.conexion.createStatement();
// 
//            con.tablaResultado = con.sentencia.executeQuery(select);
// 
//
// 
//            while (con.tablaResultado.next()) {
// 
//                rut = con.tablaResultado.getString("rut");
// 
//                nombre = con.tablaResultado.getString("nombre");
// 
//                apePaterno = con.tablaResultado.getString("ape_pat");
// 
//                apeMaterno = con.tablaResultado.getString("ape_mat");
// 
//                nota = con.tablaResultado.getInt("nota");
// 
//                cur = con.tablaResultado.getString("curso");
// 
//                
// 
//              curs.setNombre(cur);
// 
//              not.setNota(nota);
// 
//                   
// 
//                       
// 
//                Alumno alum = new Alumno(rut, nombre, select, select, not, curs);
// 
//                alumnos.add(alum);
// 
//            }
// 
//            con.sentencia.close();
// 
//        } catch (SQLException ex) {
// 
//            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
// 
//        }
//        return null;
// 
//
// 
//    }
// 

    
}



















































































































