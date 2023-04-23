package porras.dylan.bl.entities.persona;

import porras.dylan.db.ConexionSQL;


import javax.swing.*;
import java.sql.*;

public class SQLServerMeseroDao {

    public Mesero insertar(Mesero mesero) {
        try {
            ConexionSQL conexion = new ConexionSQL();
            Connection conn = conexion.getConexion();
            String query = "SELECT COUNT(identificacion) FROM Mesero WHERE identificacion = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, mesero.getId());
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "No se puede registrar un mesero con una identificación, registrada anteriormente");
            } else {
                query = "INSERT INTO Mesero (nombre, clave, identificacion) VALUES (?, ?, ?)";
                ps = conn.prepareStatement(query);
                ps.setString(1, mesero.getNombre());
                ps.setString(2, mesero.getClave());
                ps.setString(3, mesero.getId());
                ps.executeUpdate();
                return mesero;

            }

        } catch (SQLException ex) {
            if (ex.getSQLState().startsWith("23")) {
                JOptionPane.showMessageDialog(null, "No se puede registrar un mesero con una identificación, registrada anteriormente");
            } else {
                System.out.println("Error de SQL: " + ex.getMessage());
            }
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        }
        return null;
    }


    public Mesero iniciarSesion(Mesero mesero) {
        try {
            ConexionSQL conexion = new ConexionSQL();
            Connection conn = conexion.getConexion();

            if (conn == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión");
                return null;
            }
            String query = "SELECT clave, identificacion ,nombre FROM Mesero WHERE clave = ? AND identificacion = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, mesero.getClave());
            ps.setString(2, mesero.getId());
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                String clave = rs.getString("clave");
                String id = rs.getString("identificacion");
                String nombre = rs.getString("nombre");
                if(mesero.getId().equals(id) && mesero.getClave().equals(clave)){
                    Mesero meseroEncontrado = new Mesero();
                    meseroEncontrado.setId(id);
                    meseroEncontrado.setClave(clave);
                    meseroEncontrado.setNombre(nombre);// se agrega el nombre del mesero
                    return meseroEncontrado;

                }else{
                    System.out.println("No existe un ususario con ese id o calve");
                }

            }

        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        }
        return null;
    }
}

