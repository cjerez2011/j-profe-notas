package modelo;

/**
 *
 * @author Fco
 */
public class Curso {
    private int id;
    private String nombre;
    private Profesor profe;

    public Curso(int id, String nombre, Profesor profe) {
        this.id = id;
        this.nombre = nombre;
        this.profe = profe;
    }

    public Curso(String nombre, Profesor profe) {
        this.nombre = nombre;
        this.profe = profe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Profesor getProfe() {
        return profe;
    }

    public void setProfe(Profesor profe) {
        this.profe = profe;
    }

    @Override
    public String toString() {
        return nombre + "" + profe;
    }

}
