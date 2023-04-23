package porras.dylan.bl.entities.persona;

import porras.dylan.bl.entities.mesa.Mesa;
import porras.dylan.bl.entities.producto.Producto;
import porras.dylan.db.ConexionSQL;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLServerClienteDao {

    public Cliente insertar(Cliente cliente) {
        try {
            ConexionSQL conexion = new ConexionSQL();
            Connection conn = conexion.getConexion();

            // Buscar la mesa correspondiente en la base de datos
            String queryMesa = "SELECT numero_mesa,disponible, capacidad FROM Mesa WHERE numero_mesa = ?";
            PreparedStatement psMesa = conn.prepareStatement(queryMesa);
            psMesa.setString(1, cliente.getMesa().getNumeroMesa());
            ResultSet rsMesa = psMesa.executeQuery();


            // Si la mesa no existe, mostrar mensaje de error
            if (!rsMesa.next()) {
                JOptionPane.showMessageDialog(null, "LA MESA NO SE ENCUENTRA REGISTRADA","ERROR",JOptionPane.INFORMATION_MESSAGE);
                return null;
            // Si la mesa se encuentra en false, quiere decir que esta ocupada
            }else if(!rsMesa.getBoolean("disponible")){
                JOptionPane.showMessageDialog(null, "LA MESA SE ENCUENTRA OCUPADA","ERROR",JOptionPane.INFORMATION_MESSAGE);
                return null;
            //Se hace la insercion del cliente en la BD
            }else if(cliente.getCantAcompaniantes() > rsMesa.getInt("capacidad") || cliente.getCantAcompaniantes() < rsMesa.getInt("capacidad")  ){
                JOptionPane.showMessageDialog(null, "LA CANTIDAD DE ACOMPAÑANTES TIENE QUE SER LA MISMA CAPACIDAD DE LA MESA, INTENTE DE NUEVO","ERROR",JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
            else{
                String queryCliente = "INSERT INTO Cliente (nombre, cantidad_acompaniantes, numero_mesa) VALUES (?, ?, ?)";
                PreparedStatement psCliente = conn.prepareStatement(queryCliente);
                psCliente.setString(1, cliente.getNombre());
                psCliente.setInt(2, cliente.getCantAcompaniantes());
                psCliente.setInt(3, rsMesa.getInt("numero_mesa"));
                psCliente.execute();

                //ACTUALIZAR DISPONIBLE MESA A FALSE
                String queryMesaUpdate = "UPDATE Mesa SET disponible = ? WHERE numero_mesa = ?";
                PreparedStatement psMesaUpdate = conn.prepareStatement(queryMesaUpdate);
                psMesaUpdate.setBoolean(1, false);
                psMesaUpdate.setString(2, cliente.getMesa().getNumeroMesa());
                psMesaUpdate.executeUpdate();
                return cliente;
            }
            // Insertar el cliente en la base de datos

        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        }
        return null;
    }

    public ArrayList<Cliente> listarCliente(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            ConexionSQL conexion = new ConexionSQL();
            Connection conn = conexion.getConexion();
            String query = "SELECT id, nombre,cantidad_acompaniantes,numero_mesa FROM Cliente";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String idCliente = rs.getString("id");
                String nombreCol=rs.getString("nombre");
                int cantidadAcompCol = rs.getInt("cantidad_acompaniantes");
                String numeroMesaCol=rs.getString("numero_mesa");
                Mesa mesa = new Mesa(numeroMesaCol,cantidadAcompCol);
                Cliente cliente = new Cliente(nombreCol,idCliente,cantidadAcompCol,mesa);
                clientes.add(cliente);
            }

            return (ArrayList<Cliente>) clientes.clone();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
