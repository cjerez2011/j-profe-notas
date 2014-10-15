

package modelo;


public class Alumno {
    
  private  int id, edad;
  private   String rut,nombre,ape_pat,ape_mat,sexo;
  private Nota nota;
  private Curso curso;
  
  
  
    public Alumno(String rut, String nombre, String ape_pat, String ape_mat, Nota nota, Curso curso) {
        this.rut = rut;
        this.nombre = nombre;
        this.ape_pat = ape_pat;
        this.ape_mat = ape_mat;
        this.nota = nota;
        this.curso = curso;
    }

    public Alumno( String rut, String nombre, String ape_pat, String ape_mat, String sexo,int edad) {
        this.edad = edad;
        this.rut = rut;
        this.nombre = nombre;
        this.ape_pat = ape_pat;
        this.ape_mat = ape_mat;
        this.sexo = sexo;
    }

    public Alumno(int id, int edad, String rut, String nombre, String ape_pat, String ape_mat, String sexo) {
        this.id = id;
        this.edad = edad;
        this.rut = rut;
        this.nombre = nombre;
        this.ape_pat = ape_pat;
        this.ape_mat = ape_mat;
        this.sexo = sexo;
    }

    
    
    

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

  

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
  
  
  
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe_pat() {
        return ape_pat;
    }

    public void setApe_pat(String ape_pat) {
        this.ape_pat = ape_pat;
    }

    public String getApe_mat() {
        return ape_mat;
    }

    public void setApe_mat(String ape_mat) {
        this.ape_mat = ape_mat;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    
    
}
