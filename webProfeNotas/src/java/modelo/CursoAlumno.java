package modelo;

/**
 *
 * @author Fco
 */
public class CursoAlumno {
    private int id;
    private String rut;
    private String nombre;
    private String apePa;
    private String apeMa;
    private int nota;
    private String descripcion;
    private String curso;

    public CursoAlumno(String rut, String nombre, String apePa, String apeMa, int nota, String descripcion) {
        this.rut = rut;
        this.nombre = nombre;
        this.apePa = apePa;
        this.apeMa = apeMa;
        this.nota = nota;
        this.descripcion = descripcion;
    }

    CursoAlumno(int id,String rut, String nombre, String apePa, String apeMa) {
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.apePa = apePa;
        this.apeMa = apeMa;
    }

    public CursoAlumno(String rut, String nombre, String apePa, String apeMa, int nota) {
        this.rut = rut;
        this.nombre = nombre;
        this.apePa = apePa;
        this.apeMa = apeMa;
        this.nota = nota;
    }

    public CursoAlumno(String rut, String nombre, int nota){
        this.rut = rut;
        this.nombre = nombre;
        this.nota = nota;
    }
    public CursoAlumno(int id,String rut,int nota, String descripcion){
        this.id = id;
        this.rut = rut;
        this.nota = nota;
        this.descripcion = descripcion;
    }

    public CursoAlumno(int id, String rut, String nombre, String apePa, int nota, String descripcion) {
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.apePa = apePa;
        this.nota = nota;
        this.descripcion = descripcion;
    }



    public String getDescripcion() {
        return descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApePa() {
        return apePa;
    }

    public void setApePa(String apePa) {
        this.apePa = apePa;
    }

    public String getApeMa() {
        return apeMa;
    }

    public void setApeMa(String apeMa) {
        this.apeMa = apeMa;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return rut + "" + nombre + "" + apePa + "" + apeMa + "" + nota + "" + curso;
    }

}