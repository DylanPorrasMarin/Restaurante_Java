package porras.dylan.bl.entities.cuenta;

import porras.dylan.bl.entities.orden.Orden;
import porras.dylan.bl.entities.persona.Cliente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;

public class Cuenta {
    private int idCuenta;
    private Cliente clienteCuenta;
    private ArrayList<Orden> ordenesCliente;
    private String fechaH;
    private double impuestoVentas= 0.10;
    private double impuestoServicio= 0.05;
    private double subTotal;
    private double total;
    private String pago;
    private boolean pagada;


    public Cuenta() {
    }

    public int getIdCuenta() {
        return idCuenta;
    }



    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }


    public Cuenta(Cliente cliente, String pago, String fechaH) {
        this.fechaH = fechaH;
        this.clienteCuenta = cliente;
        this.ordenesCliente = new ArrayList<Orden>();
        this.subTotal = 0.0;
        this.impuestoVentas = 0.0;
        this.impuestoServicio = 0.0;
        this.total = 0.0;
        this.pagada = false;
        this.pago = pago;
    }


    public String getFechaH() {
        return fechaH;
    }

    public void setFechaH(String fechaH) {
        this.fechaH = fechaH;
    }

    public double getImpuestoVentas() {
        return impuestoVentas;
    }

    public void setImpuestoVentas(double impuestoVentas) {
        this.impuestoVentas = impuestoVentas;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public Cliente getClienteCuenta() {
        return clienteCuenta;
    }

    public void setClienteCuenta(Cliente clienteCuenta) {
        this.clienteCuenta = clienteCuenta;
    }

    public ArrayList<Orden> getOrdenesCliente() {
        return ordenesCliente;
    }

    public void setOrdenesCliente(ArrayList<Orden> ordenesCliente) {
        this.ordenesCliente = ordenesCliente;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getImpuestosVenta() {
        return impuestoVentas;
    }

    public void setImpuestosVenta(double impuestosVenta) {
        this.impuestoVentas = impuestosVenta;
    }

    public double getImpuestoServicio() {
        return impuestoServicio;
    }

    public void setImpuestoServicio(double impuestoServicio) {
        this.impuestoServicio = impuestoServicio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Cuenta: " +
                "\ncliente=" + clienteCuenta +
                "\norden=" + ordenesCliente +
                "\nsubTotal=" + subTotal +
                "\nimpuestosVenta=" + impuestoVentas +
                "\nimpuestoServicio=" + impuestoServicio +
                "\nTipo Pago=" + pago +
                "\ntotal=" + total+
                "\nPagada=" + pagada;
    }
}
