package porras.dylan.bl.entities.orden;

import porras.dylan.bl.entities.cuenta.Cuenta;
import porras.dylan.bl.entities.mesa.Mesa;
import porras.dylan.bl.entities.persona.Cliente;
import porras.dylan.bl.entities.persona.Mesero;
import porras.dylan.bl.entities.producto.Producto;

import java.util.ArrayList;


public class Orden {
        private int idOrden;
        private int idCuenta;
        private Cliente clienteOrden;
        private Mesero mesero;
        private ArrayList<Producto> productosCliente;

        public Orden() {
        }



    public Orden(Cliente clienteOrden, Mesero mesero) {
        this.clienteOrden = clienteOrden;
        this.mesero = mesero;
    }

    public Orden(Mesa mesa, Cliente cliente, Mesero mesero) {
            this.clienteOrden = cliente;
            this.mesero = mesero;
            this.productosCliente = new ArrayList<Producto>();
        }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public void setProductosCliente(ArrayList<Producto> productosCliente) {
            this.productosCliente = productosCliente;
        }
        public ArrayList<Producto> getProductosCliente() {
            return productosCliente;
        }
        public void setClienteOrden(Cliente clienteOrden) {
            this.clienteOrden = clienteOrden;
        }
        public Cliente getClienteOrden() {
            return clienteOrden;
        }

        public void setMesero(Mesero mesero) {
            this.mesero = mesero;
        }
        public Mesero getMesero() {
            return mesero;
        }





    @Override
    public String toString() {
        return "Orden" +
                "\nID Orden= " + idOrden+
                "\nNombre Cliente= " + clienteOrden.getNombre()+
                "\nMesa Cliente= " + clienteOrden.getMesa().getNumeroMesa()+
                "\nmesero= " + mesero.getNombre()+
                "\nproductosCliente= " + productosCliente;
    }
}
