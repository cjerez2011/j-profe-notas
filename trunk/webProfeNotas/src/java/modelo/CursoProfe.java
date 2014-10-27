package modelo;

/**
 *
 * @author Fco
 */
public class CursoProfe {
    private int id;
    private String nombre;

    public CursoProfe(String nombre) {
        this.nombre = nombre;
    }

    public CursoProfe(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return  id + "" + nombre;
    }
    
    
}

