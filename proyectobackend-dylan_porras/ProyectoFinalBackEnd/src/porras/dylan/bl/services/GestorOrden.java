package porras.dylan.bl.services;

import porras.dylan.bl.entities.orden.Orden;
import porras.dylan.bl.entities.orden.SQLServerOrdenDao;
import porras.dylan.bl.entities.persona.Cliente;
import porras.dylan.bl.entities.persona.Mesero;
import porras.dylan.bl.entities.persona.SQLServerClienteDao;
import porras.dylan.bl.entities.producto.Producto;

import java.util.ArrayList;

import porras.dylan.bl.entities.producto.SQLServerProductoDao;


import javax.swing.*;

public class GestorOrden {


    public GestorOrden() {

    }

    public Orden agregarOrden(String iDMesero, String idCliente, String codigo, int cantidad) {
        SQLServerProductoDao productoDao = new SQLServerProductoDao();
        SQLServerOrdenDao ordenDao = new SQLServerOrdenDao();
        ArrayList<Producto> productos = new ArrayList<>();
        Cliente cliente = new Cliente(idCliente);
        Mesero mesero = new Mesero(iDMesero);
        Producto p = productoDao.buscarProductoPorCodigo(codigo);
        if (p == null) {
            JOptionPane.showMessageDialog(null, "El código de producto ingresado no existe, intente de nuevo");
        } else {
            p.setCantidad(cantidad);
            productos.add(p);
            Orden orden = new Orden(cliente, mesero);
            orden.setProductosCliente(productos);
            return ordenDao.insertarOrden(orden);
        }
        return null;
    }

    public ArrayList<Orden> listarOrden(){
        SQLServerOrdenDao listarOrdenes = new SQLServerOrdenDao();
        return listarOrdenes.listarOrdenes();

    }
}