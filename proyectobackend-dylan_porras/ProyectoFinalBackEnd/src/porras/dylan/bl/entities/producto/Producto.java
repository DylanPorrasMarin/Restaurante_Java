package porras.dylan.bl.entities.producto;

import java.util.Objects;

public class Producto {
      private String codigo;
      private String descripcion;
      private int cantidad;
      private String tipo;
      private double precio;

    public Producto() {
    }

    public Producto(String codigo, String descripcion, int cantidad, String tipo, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.precio = precio;
    }

    public Producto(String codigo, int cantidad, String tipo, double precio) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(codigo, producto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return
                "\ncodigo= " + codigo +
                "\ndescripcion= " + descripcion +
                "\ncantidad= " + cantidad +
                "\ntipo= " + tipo +
                "\nprecio= " + precio;
    }
}
