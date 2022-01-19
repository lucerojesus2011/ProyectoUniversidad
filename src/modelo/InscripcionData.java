/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Alumno;
import entidades.Inscripcion;
import entidades.Materia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class InscripcionData {

    private Connection con;

    public InscripcionData(Conexion conexion) {
        con = conexion.getConnection();
    }

    public void altaInscripcion(Inscripcion inscripcion) {
        try {
            if (con.isClosed()) {

                con = new Conexion().getConnection();
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        String sql = "INSERT into inscripcion (id_alumno, id_materia, fecha) "
                + "VALUES(?, ?, ?);";

        try {

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, inscripcion.getAlumno().getId_alumno());
            ps.setInt(2, inscripcion.getMateria().getIdMateria());
            ps.setDate(3, Date.valueOf(LocalDate.now()));

            ps.executeQuery();

            ResultSet rs = ps.getGeneratedKeys();

            con.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void calificar(int id, double calificacion) {

        try {
            if (con.isClosed()) {

                con = new Conexion().getConnection();
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        String sql = "UPDATE inscripcion SET nota = ? WHERE id_inscripcion = ?";

        try {

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, calificacion);
            ps.setInt(2, id);
            ps.executeQuery();

            ResultSet rs = ps.getGeneratedKeys();

            con.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void bajaInscripcion(int idInscripcion) {
        try {
            if (con.isClosed()) {

                con = new Conexion().getConnection();
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        String sql = "DELETE from inscripcion WHERE id_inscripcion = ?";

        try {

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idInscripcion);

            ps.executeQuery();

            ResultSet rs = ps.getGeneratedKeys();

            con.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public List<Alumno> ListarPorMateria(int id) {
        try {
            if (con.isClosed()) {

                con = new Conexion().getConnection();
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        Alumno alumno;
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM inscripcion i, alumno u WHERE id_materia = ? and i.id_alumno = u.id_alumno";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                alumno = new Alumno();
                alumno.setId_alumno(rs.getInt("id_alumno"));
                alumno.setNombre_alumno(rs.getString("nombre_alumno"));
                alumno.setFn_alumno(rs.getDate("fn_alumno").toLocalDate());
                alumno.setActivo(rs.getBoolean("activo"));
                System.out.println(alumno.getNombre_alumno());
                alumnos.add(alumno);

            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return alumnos;
    }

    public List<Materia> ListarAlumnosPorMateria(int id) {
        try {
            if (con.isClosed()) {

                con = new Conexion().getConnection();
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        Materia materia;
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM inscripcion i, materia u WHERE id_alumno = ? and i.id_materia = u.id_materia";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                materia = new Materia();
                materia.setIdMateria(rs.getInt("id_materia"));
                materia.setNombreMateria(rs.getString("nombre_materia"));
                System.out.println(materia.getNombreMateria());
                materias.add(materia);

            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return materias;
    }
}
