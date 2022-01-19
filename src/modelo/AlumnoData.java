package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import entidades.Alumno;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlumnoData {

    private Connection con;

    public AlumnoData(Conexion conexion) {

        con = conexion.getConnection();
    }

    public void guardarAlumno(Alumno alumno) {

        try {
            if (con.isClosed()) {

                con = new Conexion().getConnection();
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        String sql = "INSERT into alumno (nombre_alumno, fn_alumno,activo) "
                + "VALUES(?, ?, ?);";

        try {

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alumno.getNombre_alumno());
            ps.setDate(2, Date.valueOf(alumno.getFn_alumno()));
            ps.setBoolean(3, alumno.isActivo());

            ps.executeQuery();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                alumno.setId_alumno(rs.getInt(1));
            } else {
                JOptionPane.showMessageDialog(null, "No puedo obtener id");
            }
            con.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        JOptionPane.showMessageDialog(null, "Alumno guardado correctamente con el id " + alumno.getId_alumno());
    }

    public Alumno buscarAlumno(int id) {
        try {
            if (con.isClosed()) {

                con = new Conexion().getConnection();
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        Alumno alumno = new Alumno();
        String sql = "SELECT * FROM alumno WHERE id_alumno=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                alumno.setId_alumno(rs.getInt("id_alumno"));
                alumno.setNombre_alumno(rs.getString("nombre_alumno"));
                alumno.setFn_alumno(rs.getDate("fn_alumno").toLocalDate());
                alumno.setActivo(rs.getBoolean("activo"));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "El id ingresado es incorrecto");
        }
        return alumno;
    }

    public void modificarAlumno(Alumno alumno, int id_alumno, String nombre_alumno, LocalDate fn_alumno, boolean activo) {

        try {
            if (con.isClosed()) {

                con = new Conexion().getConnection();
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        try {
            String sql = "UPDATE alumno SET id_alumno = ?, nombre_alumno = ?, fn_alumno = ?,  activo = ? WHERE id_alumno = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id_alumno);
            ps.setString(2, nombre_alumno);
            ps.setDate(3, Date.valueOf(fn_alumno));
            ps.setBoolean(4, activo);
            ps.setInt(5, alumno.getId_alumno());

            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void borrarAlumno(int id) {

        try {
            if (con.isClosed()) {

                con = new Conexion().getConnection();
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        String sql = "DELETE FROM alumno WHERE id_alumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

            ps.close();
            con.close();
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error al borrar Alumno");
        }

    }

    public ArrayList<Alumno> buscarAlumnos() {
        try {
            if (con.isClosed()) {

                con = new Conexion().getConnection();
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        ArrayList<Alumno> alumnos = new ArrayList<>();
        Alumno alumno;
        String sql = "SELECT * FROM alumno";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                alumno = new Alumno();
                alumno.setId_alumno(rs.getInt("id_alumno"));
                alumno.setNombre_alumno(rs.getString("nombre_alumno"));
                alumno.setFn_alumno(rs.getDate("fn_alumno").toLocalDate());
                alumno.setActivo(rs.getBoolean("activo"));
                
                alumnos.add(alumno);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            //Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, "Alumno no encontrado ", "Error!", JOptionPane.WARNING_MESSAGE);
        }
        return alumnos;
    }

}
