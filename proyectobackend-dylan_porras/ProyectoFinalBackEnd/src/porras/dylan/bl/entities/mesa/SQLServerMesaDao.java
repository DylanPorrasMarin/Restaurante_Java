package porras.dylan.bl.entities.mesa;

import porras.dylan.db.ConexionSQL;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class SQLServerMesaDao {

    public Mesa insertarMesa(Mesa mesa) {
        try {
            ConexionSQL conexion = new ConexionSQL();
            Connection conn = conexion.getConexion();

            // Buscar si la mesa ya existe en la base de datos
            String query = "SELECT COUNT(numero_mesa) FROM Mesa WHERE numero_mesa = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, mesa.getNumeroMesa());
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                // La mesa ya existe en la base de datos
                JOptionPane.showMessageDialog(null, "NO SE PUEDE REGISTRAR UNA MESA CON UN ID DE OTRA MESA,INTENTE DE NUEVO","ERROR",JOptionPane.ERROR_MESSAGE);
            } else {
                // Insertar la mesa en la base de datos
                query = "INSERT INTO Mesa (numero_mesa, capacidad) VALUES (?, ?)";
                ps = conn.prepareStatement(query);
                ps.setString(1, mesa.getNumeroMesa());
                ps.setInt(2, mesa.getCapacidad());
                ps.executeUpdate();
                return mesa;
                //JOptionPane.showMessageDialog(null, "MESA REGISTRADA CON EXITO","REGISTRO",JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        }
        return null;
    }

    public ArrayList<Mesa>listarMesas(){
        ArrayList<Mesa> mesas = new ArrayList<>();
        try {
            ConexionSQL conexion = new ConexionSQL();
            Connection conn = conexion.getConexion();
            String query = "SELECT numero_mesa, capacidad, disponible FROM MESA";
            PreparedStatement ps = null;
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String numero_mesaCol=rs.getString("numero_mesa");
                int capacidad = rs.getInt("capacidad");
                boolean disponible = rs.getBoolean("disponible");
                Mesa mesa = new Mesa(numero_mesaCol,capacidad,disponible);
                mesas.add(mesa);
            }


            return (ArrayList<Mesa>) mesas.clone();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Mesa> eliminarMesa(Mesa mesa) {
        ArrayList<Mesa> eliminarmesas = new ArrayList<>();
        try {
            ConexionSQL conexion = new ConexionSQL();
            Connection conn = conexion.getConexion();
            String query = "DELETE FROM MESA WHERE numero_mesa=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, mesa.getNumeroMesa());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                eliminarmesas.add(mesa);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return eliminarmesas;
    }
}
