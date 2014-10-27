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
    private List<Curso> cursos;

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

    public List listaJavaWeb(int id) {
        int num = 0;

        String select = "select a.rut, a.nombre, a.ape_pat, a.ape_mat, b.nota,b.descripcion, c.nombre as 'Curso'  from alumno a, notas b, curso c where b.alumno = a.id  and b.curso = c.id and c.id = " + id + "";
        List<CursoAlumno> lista = new ArrayList<>();
        try {

            con.sentencia = con.conexion.createStatement();
            con.tablaResultado = con.sentencia.executeQuery(select);
            while (con.tablaResultado.next()) {
                String rut = con.tablaResultado.getString("rut");
                String nombre = con.tablaResultado.getString("nombre");
                String apeP = con.tablaResultado.getString("ape_pat");
                String apeM = con.tablaResultado.getString("ape_mat");
                int nota = con.tablaResultado.getInt("nota");
                String descri = con.tablaResultado.getString("descripcion");
                String curso = con.tablaResultado.getString("curso");
                CursoAlumno c = new CursoAlumno(rut, nombre, apeP, apeM, nota, descri, curso);
                lista.add(c);
            }
            con.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    //metodo para insertar notas
    public void insertarNota(Nota n) {
        Nota no = new Nota();
        try {
            String insert = "insert into notas values(null,'" + no.getNota() + "','" + no.getPorcentaje() + "','" + no.getAlumno().getId() + "','" + no.getCurso().getId() + "')";

            con.sentencia = con.conexion.createStatement();
            con.sentencia.execute(insert);
            con.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    
    
    }


