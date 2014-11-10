package modelo;

/**
 *
 * @author Fco
 */
public class Nota {
    private int id;
    private int nota;
    private int porcentaje;
    private String descripcion;
    private int alumno;
    private int curso;

    public Nota(int id, int nota, int porcentaje, String descripcion) {
        this.id = id;
        this.nota = nota;
        this.porcentaje = porcentaje;
        this.descripcion = descripcion;
    }
    
    

    public Nota(int id, int nota, int porcentaje, String descripcion, int alumno, int curso) {
        this.id = id;
        this.nota = nota;
        this.porcentaje = porcentaje;
        this.descripcion = descripcion;
        this.alumno = alumno;
        this.curso = curso;
    }

    public Nota(int nota, int porcentaje, String descripcion, int alumno, int curso) {
        this.nota = nota;
        this.porcentaje = porcentaje;
        this.descripcion = descripcion;
        this.alumno = alumno;
        this.curso = curso;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAlumno() {
        return alumno;
    }

    public void setAlumno(int alumno) {
        this.alumno = alumno;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return id+ " "+nota+" "+porcentaje+" "+descripcion+" "+alumno+" "+curso;
    }

    
}
