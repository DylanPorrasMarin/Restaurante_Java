package porras.dylan.bl.entities.orden;

import porras.dylan.bl.entities.mesa.Mesa;
import porras.dylan.bl.entities.persona.Cliente;
import porras.dylan.bl.entities.persona.Mesero;
import porras.dylan.bl.entities.producto.Producto;
import porras.dylan.bl.services.GestorOrden;
import porras.dylan.db.ConexionSQL;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;


public class SQLServerOrdenDao {

        public GestorOrden gestorOrden;
        private int idOrden;

    public Orden insertarOrden(Orden orden) {
        try {
            ConexionSQL conexion = new ConexionSQL();
            Connection conn = conexion.getConexion();
            String query;

            query = "SELECT numero_mesa FROM cliente WHERE id = ?";
            PreparedStatement psMesaC = conn.prepareStatement(query);
            psMesaC.setString(1, orden.getClienteOrden().getId());
            ResultSet rsMesaC = psMesaC.executeQuery();

            String mesaC = "";
            if (rsMesaC.next()) {
                mesaC = rsMesaC.getString(1);
            }

            query = "SELECT id FROM cliente WHERE id = ?";
            PreparedStatement psCliente = conn.prepareStatement(query);
            psCliente.setString(1, orden.getClienteOrden().getId());
            ResultSet rsCliente = psCliente.executeQuery();

            if(!rsCliente.next()){
                JOptionPane.showMessageDialog(null, "NO EXISTE EL CLIENTE, INTENTE DE NUEVO");
                return null;
            }

            //TODO: HACER LA CUENTA CON SUS RESPECTIVAS ORDENES

            // Insertar la cuenta

            // Buscar una cuenta abierta para el cliente
            query = "SELECT id FROM Cuenta WHERE id_cliente = ? AND pagada=0";
            PreparedStatement psCuentaAbierta = conn.prepareStatement(query);
            psCuentaAbierta.setString(1, orden.getClienteOrden().getId());
            ResultSet rsCuentaAbierta = psCuentaAbierta.executeQuery();

            int idCuenta;
            if (rsCuentaAbierta.next()) {
                // Si se encuentra una cuenta abierta, usar el ID de la cuenta existente
                idCuenta = rsCuentaAbierta.getInt("id");
            } else {
                // Si no se encuentra una cuenta abierta, crear una nueva cuenta
                PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO Cuenta (id_cliente) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                stmt1.setString(1, orden.getClienteOrden().getId());
                stmt1.executeUpdate();

                ResultSet rs1 = stmt1.getGeneratedKeys();
                if (rs1.next()) {
                    idCuenta = rs1.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID de la cuenta recién creada");
                }
                rs1.close();
                stmt1.close();
            }

            rsCuentaAbierta.close();
            psCuentaAbierta.close();


            query = "INSERT INTO Orden (id_cliente, numero_mesa, id_mesero,id_cuenta) VALUES (?, ?, ?,?)";
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, orden.getClienteOrden().getId());
            ps.setString(2, mesaC);
            ps.setString(3, orden.getMesero().getId());
            ps.setInt(4,idCuenta);
            ps.executeUpdate();

            int idOrden = 0;
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idOrden = rs.getInt(1);
            }
            rs.close();
            ps.close();

            query = "SELECT codigo, cantidad FROM Producto WHERE codigo = ?";
            PreparedStatement psP = conn.prepareStatement(query);

            // INSERTAR PRODUCTOS A LA ORDEN
            query = "INSERT INTO Productos_Ordenes (id_orden, codigo, cantidad) VALUES (?, ?, ?)";
            PreparedStatement psInsert = conn.prepareStatement(query);

            for (Producto producto : orden.getProductosCliente()) {
                // Verificar que hay suficiente cantidad de producto disponible en el stock
                psP.setString(1, producto.getCodigo());
                ResultSet rsP = psP.executeQuery();
                if (rsP.next()) {
                    int cantidadStock = rsP.getInt("cantidad");
                    if (cantidadStock < producto.getCantidad()) {
                        JOptionPane.showMessageDialog(null, "NO HAY LA CANTIDAD SOLICITADA DEL PRODUCTO CON EL CODIGO: " + " '"+producto.getCodigo()+"' ");
                        continue;
                    }else if(cantidadStock==0 || producto.getCantidad()==0){
                        JOptionPane.showMessageDialog(null, "NO HAY INVENTARIO DE PRODUCTO, REGISTRE MAS CANTIDAD: " + " '"+producto.getCodigo()+"' ");
                        continue;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "EL PRODUCTO " + producto.getCodigo() + " NO EXISTE");
                    continue;
                }
                rsP.close();

                // Insertar una fila en la tabla "orden_productos" por cada producto de la orden
                psInsert.setInt(1, idOrden);
                psInsert.setString(2, producto.getCodigo());
                psInsert.setInt(3, producto.getCantidad());
                psInsert.executeUpdate();
                JOptionPane.showMessageDialog(null,"PRODUCTO REGISTRADO EN LA ORDEN!");

                // Actualizar la cantidad de stock del producto
                query = "UPDATE Producto SET cantidad = cantidad - ? WHERE codigo = ?";
                PreparedStatement psUpdate = conn.prepareStatement(query);
                psUpdate.setInt(1, producto.getCantidad());
                System.out.println(producto.getCodigo());
                psUpdate.setString(2, producto.getCodigo());
                psUpdate.executeUpdate();
                psUpdate.close();
            }
            psInsert.close();

            return orden;
        } catch (SQLException ex) {
            System.out.println("Error SQLORDEN: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        }
        return null;
    }

    public ArrayList<Orden> listarOrdenes(){
        ArrayList<Orden> ordenes = new ArrayList<>();
        try {
            ConexionSQL conexion = new ConexionSQL();
            Connection conn = conexion.getConexion();
            String query = "SELECT  o.id Orden ,cu.id cuenta, c.nombre nombre_cliente,c.numero_mesa mesa_cliente,m.nombre nombre_mesero, p.cantidad cantidad_producto, pr.tipo tipo_producto, pr.codigo codigo_producto, pr.precio FROM Cliente c JOIN Orden o ON (c.id = o.id_cliente)JOIN Cuenta cu ON(c.id = cu.id_cliente) JOIN Mesero m ON (m.identificacion = o.id_mesero) JOIN Productos_Ordenes p ON (p.id_orden = o.id) JOIN Producto pr ON (p.codigo = pr.codigo)";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

            int idOrden=rs.getInt("Orden");
            String nombreC = rs.getString("nombre_cliente");
            String nombreM = rs.getString("nombre_mesero");
            int cantidadP = rs.getInt("cantidad_producto");
            String tipoP = rs.getString("tipo_producto");
            String codigoP = rs.getString("codigo_producto");
            double precioP = rs.getDouble("precio");
            String numeroM = rs.getString("mesa_cliente");
            int idCuenta = rs.getInt("cuenta");
            Mesa mesa = new Mesa();
            mesa.setNumeroMesa(numeroM);
            ArrayList<Producto>productos= new ArrayList<>();
            Producto p = new Producto(codigoP,cantidadP,tipoP,precioP);
            productos.add(p);
            Cliente c = new Cliente();
            c.setNombre(nombreC);
            c.setMesa(mesa);
            Mesero m = new Mesero();
            m.setNombre(nombreM);
            Orden o = new Orden();
            o.setIdOrden(idOrden);
            o.setMesero(m);
            o.setClienteOrden(c);
            o.setProductosCliente(productos);
            o.setIdCuenta(idCuenta);
            ordenes.add(o);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (ArrayList<Orden>) ordenes.clone();
    }

    public ArrayList<Orden> buscarOrdenesCliente(String nombreCliente) {
        ArrayList<Orden> ordenes = new ArrayList<>();
        try {
            ConexionSQL conexion = new ConexionSQL();
            Connection conn = conexion.getConexion();
            String query = "SELECT  o.id Orden ,cu.id cuenta, c.nombre nombre_cliente,c.numero_mesa mesa_cliente,m.nombre nombre_mesero, p.cantidad cantidad_producto, pr.tipo tipo_producto, pr.codigo codigo_producto,pr.descripcion descripcion, pr.precio FROM Cliente c JOIN Orden o ON (c.id = o.id_cliente)JOIN Cuenta cu ON(c.id = cu.id_cliente) JOIN Mesero m ON (m.identificacion = o.id_mesero) JOIN Productos_Ordenes p ON (p.id_orden = o.id) JOIN Producto pr ON (p.codigo = pr.codigo) where c.nombre= ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, nombreCliente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nombreC = rs.getString("nombre_cliente");
                int idOrden = rs.getInt("Orden");
                String descripcion = rs.getString("descripcion");
                String nombreM = rs.getString("nombre_mesero");
                int cantidadP = rs.getInt("cantidad_producto");
                String tipoP = rs.getString("tipo_producto");
                String codigoP = rs.getString("codigo_producto");
                double precioP = rs.getDouble("precio");
                String numeroM = rs.getString("mesa_cliente");
                int idCuenta = rs.getInt("cuenta");
                Mesa mesa = new Mesa();
                mesa.setNumeroMesa(numeroM);
                ArrayList<Producto> productos = new ArrayList<>();
                Producto p = new Producto(codigoP, cantidadP, tipoP, precioP);
                p.setDescripcion(descripcion);
                productos.add(p);
                Cliente cli = new Cliente();
                cli.setMesa(mesa);
                cli.setNombre(nombreCliente);
                Mesero m = new Mesero();
                m.setNombre(nombreM);
                Orden o = new Orden();
                o.setIdOrden(idOrden);
                o.setMesero(m);
                o.setClienteOrden(cli);
                o.setProductosCliente(productos);
                o.setIdCuenta(idCuenta);
                ordenes.add(o);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordenes;
    }




}
