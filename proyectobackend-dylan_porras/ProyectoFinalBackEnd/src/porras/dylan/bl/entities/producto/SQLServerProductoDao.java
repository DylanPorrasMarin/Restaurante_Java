package porras.dylan.bl.entities.producto;

import porras.dylan.bl.entities.mesa.Mesa;
import porras.dylan.bl.entities.orden.Orden;
import porras.dylan.bl.entities.persona.Cliente;
import porras.dylan.db.ConexionSQL;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLServerProductoDao {

    public Producto insertar(Producto producto){
            try {
                ConexionSQL conexion = new ConexionSQL();
                Connection conn = conexion.getConexion();

                // Buscar la mesa correspondiente en la base de datos
                String queryMesa = "SELECT codigo FROM Producto WHERE codigo = ?";
                PreparedStatement psMesa = conn.prepareStatement(queryMesa);
                psMesa.setString(1, producto.getCodigo());
                ResultSet rsProducto = psMesa.executeQuery();

                // Si el producto  existe, mostrar mensaje de error
                if (rsProducto.next()) {
                    JOptionPane.showMessageDialog(null, "El PRODUCTO YA EXISTE, INGRESE OTRO CODIGO","ERROR",JOptionPane.INFORMATION_MESSAGE);
                    return null;
                    // Si la mesa se encuentra en false, quiere decir que esta ocupada
                }
                else{
                    String query = "INSERT INTO Producto (codigo,descripcion,cantidad,tipo,precio) VALUES (?, ?, ?,?,?)";
                    PreparedStatement psProducto = conn.prepareStatement(query);
                    psProducto.setString(1,producto.getCodigo());
                    psProducto.setString(2,producto.getDescripcion());
                    psProducto.setInt(3,producto.getCantidad());
                    psProducto.setString(4,producto.getTipo());
                    psProducto.setDouble(5,producto.getPrecio());
                    psProducto.execute();
                    return producto;
                }
                // Insertar el cliente en la base de datos

            } catch (SQLException ex) {
                System.out.println("Error de SQL: " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Error general: " + ex.getMessage());
            }
            return null;
        }
    public ArrayList<Producto> listarProducto(){
        ArrayList<Producto> productos = new ArrayList<>();
        try {
            ConexionSQL conexion = new ConexionSQL();
            Connection conn = conexion.getConexion();
            String query = "SELECT codigo,descripcion,cantidad,tipo,precio FROM Producto";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String codigoCol=rs.getString("codigo");
                String descripcionCol=rs.getString("descripcion");
                int cantidadCol = rs.getInt("cantidad");
                String tipoCol=rs.getString("tipo");
                double precioCol = rs.getDouble("precio");
                Producto producto = new Producto(codigoCol,descripcionCol,cantidadCol,tipoCol,precioCol);
                productos.add(producto);
            }

            return (ArrayList<Producto>) productos.clone();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Producto buscarProductoPorCodigo(String codigo) {
        try {
            ConexionSQL conexion = new ConexionSQL();
            Connection conn = conexion.getConexion();
            String query = "SELECT * FROM Producto WHERE codigo = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String descripcion = rs.getString("descripcion");
                int cantidad = rs.getInt("cantidad");
                String tipo = rs.getString("tipo");
                double precio = rs.getDouble("precio");
                return new Producto(codigo, descripcion, cantidad, tipo, precio);
            }
        } catch (SQLException ex) {
            System.out.println("Error de SQLPRODUCTO: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        }
        return null;
    }
}


