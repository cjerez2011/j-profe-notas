package modelo;

public class Nota {

    private int id;
    private float nota;
    private int porcentaje;
    private String descripcion;
    private Alumno alumno;
    private Curso curso;

    public Nota() {
    }

    public Nota(int id, float nota, int porcentaje, String descripcion) {
        this.id = id;
        this.nota = nota;
        this.porcentaje = porcentaje;
        this.descripcion = descripcion;
    }
    
    

    public Nota(float nota, int porcentaje, String descripcion, Alumno alumno, Curso curso) {
        this.nota = nota;
        this.porcentaje = porcentaje;
        this.alumno = alumno;
        this.curso = curso;
    }

    public Nota(int id, float nota, String descripcion, int porcentaje, Alumno alumno, Curso curso) {
        this.id = id;
        this.nota = nota;
        this.porcentaje = porcentaje;
        this.alumno = alumno;
        this.curso = curso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return nota + "" + porcentaje + "" + descripcion + "" + alumno + "" + curso;
    }

}
