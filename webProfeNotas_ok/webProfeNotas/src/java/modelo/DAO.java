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
    private List<CursoProfe> cursos;

    public DAO() throws SQLException {
        con = new Conexion("localhost", "colegio", "root", "");
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

    public Profesor exist(Profesor profe) throws SessionFailException {
        String select = "select * from profesor where clave = AES_ENCRYPT('" + profe.getClave() + "','llave') and rut = '" + profe.getRut() + "'";
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

    public String dameTuNombre(Profesor p) {
        String nombre;
        for (Profesor profe : profes) {
            if (p.getRut().equalsIgnoreCase(profe.getRut())) {
                return nombre = profe.getNombre() + " " + profe.getApellidoPaterno();
            }
        }
        return null;
    }

    public String dameTuRut(Profesor p) {
        String id;
        for (Profesor profe : profes) {
            if (p.getRut().equalsIgnoreCase(profe.getRut())) {
                return id = profe.getRut();
            }
        }
        return null;
    }

    public List listaJavaWeb(int id, String filtro) {
        String  select;
        if(filtro != null){
            select = "select a.id, a.rut, a.nombre, a.ape_pat, a.ape_mat from alumno a, curso c, curso_alumno ca where c.id = ca.curso and ca.alumno = a.id  and c.id = '" + id + "' and (a.nombre like '%"+filtro+"%' or a.rut like '%"+filtro+"%');";
        }else{
            select = "select a.id, a.rut, a.nombre, a.ape_pat, a.ape_mat from alumno a, curso c, curso_alumno ca where c.id = ca.curso and ca.alumno = a.id  and c.id = '" + id + "';";
        }
        
        
        List<CursoAlumno> lista = new ArrayList<>();
        try {

            con.sentencia = con.conexion.createStatement();
            con.tablaResultado = con.sentencia.executeQuery(select);
            while (con.tablaResultado.next()) {
                int idd = con.tablaResultado.getInt("id");
                String rut = con.tablaResultado.getString("rut");
                String nombre = con.tablaResultado.getString("nombre");
                String apeP = con.tablaResultado.getString("ape_pat");
                String apeM = con.tablaResultado.getString("ape_mat");

                CursoAlumno c = new CursoAlumno(idd, rut, nombre, apeP, apeM);
                lista.add(c);
            }
            con.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List CargarCursos(String rut) {

        String select = "select curso.id, curso.nombre "
                + "from curso, profesor "
                + "where curso.profesor = profesor.rut and "
                + "profesor.rut = '" + rut + "'";
        List<Curso> cursos = new ArrayList<>();
        try {

            con.sentencia = con.conexion.createStatement();
            con.tablaResultado = con.sentencia.executeQuery(select);
            while (con.tablaResultado.next()) {
                int idCurso = con.tablaResultado.getInt("id");
                String nombreCurso = con.tablaResultado.getString("nombre");

                Curso cur = new Curso(idCurso, nombreCurso);
                cursos.add(cur);
            }
            con.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursos;
    }

    public int promedioCurso(int id) {
        int promedio = 0;
        try {
            String select = " select   round(sum(nota)/count(nota))      from   notas "
                    + " where  notas.curso = " + id + "";
            con.sentencia = con.conexion.createStatement();
            con.tablaResultado = con.sentencia.executeQuery(select);
            while (con.tablaResultado.next()) {
                promedio = con.tablaResultado.getInt("round(sum(nota)/count(nota))");
            }
            con.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return promedio;
    }

    public int promedioAlumno() {
        return 0;

    }

    public void cargarAlumnos() {
        alumnos = new ArrayList<>();
        String rut, nombre, apePaterno, apeMaterno, sexo;
        int edad, id;
        try {
            String select = "SELECT * FROM alumno;";

            con.sentencia = con.conexion.createStatement();
            con.tablaResultado = con.sentencia.executeQuery(select);

            while (con.tablaResultado.next()) {
                id = con.tablaResultado.getInt("id");
                rut = con.tablaResultado.getString("rut");
                nombre = con.tablaResultado.getString("nombre");
                apePaterno = con.tablaResultado.getString("ape_pat");
                apeMaterno = con.tablaResultado.getString("ape_mat");
                edad = con.tablaResultado.getInt("edad");
                sexo = con.tablaResultado.getString("sexo");

                Alumno alu = new Alumno(id, rut, nombre, apePaterno, apeMaterno, edad, sexo);
                alumnos.add(alu);
            }
            con.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //metodo para Cambiar notas
    public void CambiarNota(Nota n) {
//        Nota no = new Nota();
        try {
            String update = "update notas set nota=?, porcentaje_nota=?, descripcion=?, alumno=?, curso=? where id=1";

            con.sentencia = con.conexion.createStatement();
            con.sentencia.execute(update);
            con.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List curso(int id) {
        String select = "select a.id, a.rut, a.nombre, a.ape_pat, a.ape_mat from alumno a, curso c, curso_alumno ca where c.id = ca.curso and ca.alumno = a.id  and c.id = '" + id + "';";
        List<CursoAlumno> lista = new ArrayList<>();
        try {

            con.sentencia = con.conexion.createStatement();
            con.tablaResultado = con.sentencia.executeQuery(select);
            while (con.tablaResultado.next()) {
                int idd = con.tablaResultado.getInt("id");
                String rut = con.tablaResultado.getString("rut");
                String nombre = con.tablaResultado.getString("nombre");
                String apeP = con.tablaResultado.getString("ape_pat");
                String apeM = con.tablaResultado.getString("ape_mat");

                CursoAlumno c = new CursoAlumno(idd, rut, nombre, apeP, apeM);
                lista.add(c);
            }
            con.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<CursoProfe> listaCursos(String rut) {
        String select = "select curso.id,curso.nombre from curso, profesor where curso.profesor = profesor.rut and profesor.rut = '" + rut + "'";
        cursos = new ArrayList<>();
        try {
            con.sentencia = con.conexion.createStatement();
            con.tablaResultado = con.sentencia.executeQuery(select);
            while (con.tablaResultado.next()) {
                int id = con.tablaResultado.getInt("id");
                String nombre = con.tablaResultado.getString("nombre");
                CursoProfe p = new CursoProfe(id, nombre);
                cursos.add(p);
            }
            con.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursos;

    }

    public void addNota(Nota nuevo) throws SQLException {
        String insert = "insert into notas "
                + "values("
                + "null,"
                + "'" + nuevo.getNota() + "',"
                + "'" + nuevo.getPorcentaje() + "',"
                + "'" + nuevo.getDescripcion() + "',"
                + "'" + ((nuevo.getNota()*nuevo.getPorcentaje()))/100 + "',"
                + "'" + nuevo.getCurso() + "',"
                + "'" + nuevo.getAlumno() + "')";
        con.sentencia = con.conexion.createStatement();
        con.sentencia.execute(insert);
        con.sentencia.close();
    }

    public List<CursoAlumno> FiltroXNombre(String nombre) {
        List<CursoAlumno> lista = new ArrayList<>();

        try {
            con.sentencia = con.conexion.createStatement();
            /*"
            
                               select a.id, a.rut, a.nombre, a.ape_pat, a.ape_mat from alumno a, curso c, curso_alumno ca where c.id = ca.curso and ca.alumno = a.id  and c.id = '" + id + "';"*/
            String consulta = "select a.rut, a.nombre, a.ape_pat, a.ape_mat, b.nota,b.descripcion, c.nombre as 'Curso'  from alumno a, notas b, curso c where a.nombre like '%" + nombre + "%'";
            
            
            con.tablaResultado = con.sentencia.executeQuery(consulta);
            while (con.tablaResultado.next()) {
                String rut = con.tablaResultado.getString("rut");
                String nomb = con.tablaResultado.getString("nombre");
                String apeP = con.tablaResultado.getString("ape_pat");
                String apeM = con.tablaResultado.getString("ape_mat");
                int nota = con.tablaResultado.getInt("nota");
                CursoAlumno c = new CursoAlumno(rut, nombre, apeP, apeM, nota);
                lista.add(c);
            }
            con.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public List listaNotas(String rut, int id) {
        String select = "select a.id, a.nota, a.porcentaje_nota, a.descripcion, b.id as id_Alumno, b.rut from notas a, alumno b "
                + "where b.rut ='" + rut + "' and a.curso = " + id + " and a.alumno = b.id";
        List<Nota> notas = new ArrayList<>();
        try {
            con.sentencia = con.conexion.createStatement();
            con.tablaResultado = con.sentencia.executeQuery(select);
            while (con.tablaResultado.next()) {
                int idNota = con.tablaResultado.getInt("id");
                int not = (int) con.tablaResultado.getFloat("nota");
                int porc = con.tablaResultado.getInt("porcentaje_nota");
                String desc = con.tablaResultado.getString("descripcion");
                Nota n = new Nota(idNota, not, porc, desc);
                notas.add(n);
            }
            con.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notas;
    }
    
    public float getPromedio(int idAlumno,int idCurso) throws SQLException{
       float promedio=0;
       /*select sum(notaPonderada) from notas
where notas.alumno ='2' and notas.curso='2';*/
        String select="select sum(notaponderada) from notas where "
                + "notas.alumno ='"+idAlumno+"' and notas.curso='"+idCurso+"'";
        
        System.out.println(select);
        con.sentencia = con.conexion.createStatement();
            con.tablaResultado = con.sentencia.executeQuery(select);
            while (con.tablaResultado.next()) {
            
            promedio = con.tablaResultado.getInt("sum(notaponderada)");
            }
            con.sentencia.close();
        
        return promedio;
       
    }
    
    
    public int idAlumno(String rut) throws SQLException{
        int idAl=0;
        
        String select="select id from alumno where alumno.rut='"+rut+"'";
        
        System.out.println(select);
     con.sentencia = con.conexion.createStatement();
            con.tablaResultado = con.sentencia.executeQuery(select);
            while (con.tablaResultado.next()) {
            
            idAl = con.tablaResultado.getInt("id");
            }
            con.sentencia.close();
        
        return idAl;
        }
   
  }
