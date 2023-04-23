package porras.dylan.bl.services;

import porras.dylan.bl.entities.producto.Producto;
import porras.dylan.bl.entities.producto.SQLServerProductoDao;

import java.util.ArrayList;

public class GestorProducto {

    public GestorProducto(){

    }

    public Producto registrarProducto(String codigo, String descripcion, int cantidad, String tipo, double precio) {
        SQLServerProductoDao sqlServerProductoDao = new SQLServerProductoDao();
        Producto nuevoProducto = new Producto(codigo, descripcion, cantidad, tipo, precio);
        return sqlServerProductoDao.insertar(nuevoProducto);
    }

    public ArrayList<Producto> listarProductos() throws Exception {
        SQLServerProductoDao sqlServerProductoDao = new SQLServerProductoDao();
        return sqlServerProductoDao.listarProducto();
    }


}
