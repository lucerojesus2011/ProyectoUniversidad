package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import entidades.Materia;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MateriaData {

    private Connection con;
  
    
    public MateriaData(Conexion conexion) {
        
        con = conexion.getConnection();
    }

    public void agregarMateria(Materia materia) {

        try {
            if (con.isClosed()){
                
                con = new Conexion().getConnection();
            }
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        
        String sql = "INSERT into materia (nombre_materia)"
                + "VALUES(?);";

        try {

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombreMateria());
            ps.executeUpdate();
            

            ResultSet rs = ps.getGeneratedKeys();
           
            if (rs.next()) {
                materia.setIdMateria(rs.getInt(1));
            } else {
                JOptionPane.showMessageDialog(null, "No puedo obtener id");
            }
            ps.close();
            con.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
       JOptionPane.showMessageDialog(null, "Materia guardada correctamente con el id " + materia.getIdMateria());
    }

  
    public void quitarMateria(int id){
        try {
            if (con.isClosed()){
                
                con = new Conexion().getConnection();
            }
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
             
        
        
        String sql = "DELETE FROM materia WHERE id_materia=?";
        
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
          JOptionPane.showMessageDialog(null, "Materia Borrada");
            ps.close();
            con.close();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "No se pudo borrar la Materia", "Error!", JOptionPane.WARNING_MESSAGE);
        }
    }
         public void modificarMateria(Materia materia, int id, String nombre) {

             try {
            if (con.isClosed()){
                
                con = new Conexion().getConnection();
            }
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
             
        try {
            String sql = "UPDATE materia SET id_materia = ?, nombre_materia = ?, WHERE id_materia = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setInt(3, materia.getIdMateria());
            
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    
        
    }



