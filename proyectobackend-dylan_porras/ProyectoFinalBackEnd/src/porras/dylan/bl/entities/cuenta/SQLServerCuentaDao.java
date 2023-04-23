package porras.dylan.bl.entities.cuenta;
import porras.dylan.bl.entities.persona.Cliente;
import porras.dylan.db.ConexionSQL;

import javax.swing.*;
import java.sql.*;


public class SQLServerCuentaDao {
    public Cuenta insertarCuenta(Cuenta cuenta) {
        try {
            ConexionSQL conexion = new ConexionSQL();
            Connection conn = conexion.getConexion();
            String query;


            query = "select cu.id_cliente, cu.id idCuenta from cuenta cu join cliente cli on (cu.id_cliente = cli.id) where nombre = ?";
            PreparedStatement psCliente = conn.prepareStatement(query);
            psCliente.setString(1, cuenta.getClienteCuenta().getNombre());
            ResultSet rsCliente = psCliente.executeQuery();

            if (rsCliente.next()) {
                int idCuenta = rsCliente.getInt("idCuenta");
                query = "UPDATE Cuenta SET fecha = ? , impuestoVentas = ?, impuestoServicio=?,subTotal=?,total=? Where id=?";
                PreparedStatement psUpdateCuenta = conn.prepareStatement(query);
                psUpdateCuenta.setString(1, cuenta.getFechaH());
                psUpdateCuenta.setDouble(2, cuenta.getImpuestosVenta());
                psUpdateCuenta.setDouble(3, cuenta.getImpuestoServicio());
                psUpdateCuenta.setDouble(4,cuenta.getSubTotal());
                psUpdateCuenta.setDouble(5,cuenta.getTotal());
                psUpdateCuenta.setInt(6,idCuenta);
                psUpdateCuenta.executeUpdate();
                psUpdateCuenta.close();

            }else{
              return null;
            }

            return cuenta;
        } catch (SQLException ex) {
            System.out.println("Error SQLCUENTA: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        }
        return null;
    }

    public Cuenta pagarCuenta(Cuenta cuenta) {
        try {
            ConexionSQL conexion = new ConexionSQL();
            Connection conn = conexion.getConexion();
            String query = "SELECT cu.id_cliente, cu.id idCuenta FROM cuenta cu JOIN cliente cli ON (cu.id_cliente = cli.id) WHERE cli.nombre = ?";
            PreparedStatement psCliente = conn.prepareStatement(query);
            psCliente.setString(1, cuenta.getClienteCuenta().getNombre());
            ResultSet rsCliente = psCliente.executeQuery();

            if (rsCliente.next()) {
                int idCuenta = rsCliente.getInt("idCuenta");
                query = "UPDATE cuenta SET pagada = ?, pago = ? WHERE id = ?";
                PreparedStatement psUpdateCuenta = conn.prepareStatement(query);
                psUpdateCuenta.setBoolean(1, true);
                psUpdateCuenta.setString(2, cuenta.getPago());
                psUpdateCuenta.setInt(3, idCuenta);
                int rowsAffected = psUpdateCuenta.executeUpdate();
                psUpdateCuenta.close();
                if(rowsAffected > 0) {
                    String queryMesaUpdate = "UPDATE mesa SET disponible = ? WHERE numero_mesa = ?";
                    PreparedStatement psMesaUpdate = conn.prepareStatement(queryMesaUpdate);
                    psMesaUpdate.setBoolean(1, true);
                    psMesaUpdate.setString(2, cuenta.getClienteCuenta().getMesa().getNumeroMesa());
                    int rowsAffected2 = psMesaUpdate.executeUpdate();
                    psMesaUpdate.close();
                    if(rowsAffected2 > 0) {
                        return cuenta;
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo actualizar la mesa, inténtelo de nuevo.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar la cuenta, inténtelo de nuevo.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No existe el cliente con tal cuenta, inténtelo de nuevo.");
            }
        } catch (SQLException ex) {
            System.out.println("Error SQLPAGARCUENTA: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error GENERALPAGARCUENTA: " + ex.getMessage());
        }
        return null;
    }
    public Cuenta buscarCuentaCliente(String nombreCliente) {
        Cuenta cuenta = new Cuenta();
        try {
            ConexionSQL conexion = new ConexionSQL();
            Connection conn = conexion.getConexion();
            String query = "SELECT cu.id cuenta, c.nombre nombre_cliente,cu.fecha fecha,cu.impuestoVentas impuestosV,cu.impuestoServicio impuestoServicio,cu.subTotal subTotal,cu.total total,cu.pago pago,cu.pagada pagada FROM Cliente c JOIN Cuenta cu ON(c.id = cu.id_cliente)   where c.nombre= ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, nombreCliente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre_cliente");
                String fecha = rs.getString("fecha");
                double impuestoV = rs.getDouble("impuestosV");
                double impuestoS = rs.getDouble("impuestoServicio");
                double subTotal = rs.getDouble("subTotal");
                double total = rs.getDouble("total");
                String pago = rs.getString("pago");
                boolean pagada = rs.getBoolean("pagada");
                Cliente c = new Cliente();
                c.setNombre(nombre);
                cuenta.setClienteCuenta(c);
                cuenta.setFechaH(fecha);
                cuenta.setImpuestoServicio(impuestoS);
                cuenta.setImpuestoVentas(impuestoV);
                cuenta.setSubTotal(subTotal);
                cuenta.setTotal(total);
                cuenta.setPagada(pagada);
                cuenta.setPago(pago);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cuenta;
    }



}
