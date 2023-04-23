package porras.dylan.bl.services;

import porras.dylan.bl.entities.cuenta.Cuenta;
import porras.dylan.bl.entities.cuenta.SQLServerCuentaDao;
import porras.dylan.bl.entities.mesa.Mesa;
import porras.dylan.bl.entities.orden.Orden;
import porras.dylan.bl.entities.orden.SQLServerOrdenDao;
import porras.dylan.bl.entities.persona.Cliente;
import porras.dylan.bl.entities.producto.Producto;

import javax.swing.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class GestorCuenta {



    public GestorCuenta(){
    }


    public Cuenta registrarCuenta(String nombreC){
        SQLServerCuentaDao cuentaDao = new SQLServerCuentaDao();
        SQLServerOrdenDao ordenDao = new SQLServerOrdenDao();
        ArrayList<Orden> ordenes = ordenDao.buscarOrdenesCliente(nombreC);
        Cliente c = new Cliente();
        c.setNombre(nombreC);
        Cuenta cuenta = new Cuenta();
        cuenta.setFechaH(getFecha());
        cuenta.setClienteCuenta(c);
        cuenta.setOrdenesCliente(ordenes);
        cuenta.setImpuestoServicio(cuenta.getImpuestoServicio());
        cuenta.setImpuestoVentas(cuenta.getImpuestosVenta());
        cuenta.setSubTotal(calcularSubtotal(cuenta));
        cuenta.setTotal(calcularTotalVentas(cuenta));

        return cuentaDao.insertarCuenta(cuenta);
    }

    public String getFecha(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return now.format(format);
    }

    public double calcularTotalVentas(Cuenta cuenta) {
        double totalVentas = 0;
        for (Orden orden : cuenta.getOrdenesCliente()) {
            for (Producto producto : orden.getProductosCliente()) {
                totalVentas += producto.getPrecio() * producto.getCantidad();
            }
        }

        double impuestoVentasTotal = totalVentas * cuenta.getImpuestosVenta();
        double impuestoServicioTotal = totalVentas * cuenta.getImpuestoServicio();

        totalVentas += impuestoVentasTotal + impuestoServicioTotal;

        return totalVentas;
    }
    public double calcularSubtotal(Cuenta cuenta) {
        double subtotal = 0;
        for (Orden orden : cuenta.getOrdenesCliente()) {
            for (Producto producto : orden.getProductosCliente()) {
                subtotal += producto.getPrecio() * producto.getCantidad();
            }
        }
        return subtotal;
    }

    public Cuenta pagarCuenta(String nombreC,String pago, double monto) {
        SQLServerCuentaDao cuentaDao = new SQLServerCuentaDao();
        Cuenta cuenta = cuentaDao.buscarCuentaCliente(nombreC);
        double total = cuenta.getTotal();
        double vuelto = 0;

        if (pago.equalsIgnoreCase("Efectivo")) {
            vuelto = monto - total;
        } else if (pago.equalsIgnoreCase("Tarjeta")) {
            vuelto = monto - total;
        }

        if (monto<total) {
            JOptionPane.showMessageDialog(null,"Dinero Insuficiente");// El monto pagado es insuficiente
        } else {
            // Actualizamos el estado de la cuenta y retornamos el vuelto

            cuenta.setPagada(true);
            cuenta.setPago(pago);
            Cliente c = new Cliente();
            cuenta.setClienteCuenta(c);

            return cuentaDao.pagarCuenta(cuenta);
        }
        return null;
    }

    public static String generarNumeroFactura() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(9999) + 1; // Genera un número aleatorio de 1 a 999

        String numeroFormateado = String.format("%d-%d-%d", 1, random.nextInt(9999), numeroAleatorio); // Formatea el número generado con ceros y sin guiones

        return numeroFormateado;
    }
    }







