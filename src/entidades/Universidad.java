
package entidades;

import entidades.*;
import entidades.Materia;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;
import modelo.AlumnoData;
import modelo.Conexion;
import modelo.InscripcionData;
import modelo.MateriaData;



public class Universidad {

   
    public static void main(String[] args) {
        
         Conexion c=new Conexion();
        
       /*  Materia quimica = new Materia("Quimica");
         Materia lab4 = new Materia("Laboratorio 4");
         MateriaData md=new MateriaData(c);
         md.agregarMateria(quimica);
         md.agregarMateria(lab4);
         
         md.agregarMateria(lab4);
         md.modificarMateria(quimica,7804, "Modificado", true);
         
         
         md.agregarMateria(quimica);
         md.quitarMateria(1);
         AlumnoData ad= new AlumnoData(c);
         ad.borrarAlumno(5);
         ad.borrarAlumno(5);
         md.quitarMateria(1);
         */
      
         Materia tecnologia = new Materia("Tecnologia");
         Materia eda = new Materia("Estructura de datos");
         Materia info = new Materia("Informatica");
        
       //  md.agregarMateria(tecnologia);
       //  md.agregarMateria(eda);
      //   md.agregarMateria(info);
        
         Alumno sergio=new Alumno(3,"Alfredo Ramirez",LocalDate.of(1998, Month.OCTOBER, 8),true);
      //   ad.guardarAlumno(sergio);
      //  ad.modificarAlumno(sergio, 1124, "Ariel Lucero", LocalDate.of(1900, Month.APRIL, 1), true);
         Alumno Gonzalo=new Alumno("Gonzalo Flores",LocalDate.of(1990, Month.JULY, 1),true);
         Alumno Florencia=new Alumno("Florencia Perez",LocalDate.of(1995, Month.DECEMBER, 1),true);
        
       
        
        Inscripcion inscripcion = new Inscripcion(sergio,tecnologia);
        InscripcionData id = new InscripcionData(c); 
       // id.calificar(1, 6.97);
        id.altaInscripcion(inscripcion);
        
        AlumnoData alumnoData = new AlumnoData(c);
        alumnoData.buscarAlumnos();
        
    }
    
}
