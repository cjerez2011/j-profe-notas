

package modelo;


public class Curso {
    
    private int id;
    private String nombre;
    private Profesor profe;

    public Curso(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Curso(int id, String nombre, Profesor profe) {
        this.id = id;
        this.nombre = nombre;
        this.profe = profe;
    }

    public Curso(String nombre) {
        this.nombre = nombre;
    }

    public Curso() {
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
    
    
    
    
}
