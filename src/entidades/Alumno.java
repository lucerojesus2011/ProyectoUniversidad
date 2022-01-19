
package entidades;

import java.time.LocalDate;

public class Alumno {
    private int id_alumno;
    private String nombre_alumno;
    private LocalDate fn_alumno;
    private boolean activo;

    public Alumno() {
    }

    public Alumno(String nombre_alumno, LocalDate fn_alumno, boolean activo) {
        this.nombre_alumno = nombre_alumno;
        this.fn_alumno = fn_alumno;
        this.activo = activo;
    }

    
    
    public Alumno(int id_alumno, String nombre_alumno, LocalDate fn_alumno, boolean activo) {
        this.id_alumno = id_alumno;
        this.nombre_alumno = nombre_alumno;
        this.fn_alumno = fn_alumno;
        this.activo = activo;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getNombre_alumno() {
        return nombre_alumno;
    }

    public void setNombre_alumno(String nombre_alumno) {
        this.nombre_alumno = nombre_alumno;
    }

    public LocalDate getFn_alumno() {
        return fn_alumno;
    }

    public void setFn_alumno(LocalDate fn_alumno) {
        this.fn_alumno = fn_alumno;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    

    @Override
    public String toString() {
        return id_alumno+" "+nombre_alumno+" "+fn_alumno+" "+activo;
    }
    
    

}   