package porras.dylan.bl.entities.persona;

import porras.dylan.bl.entities.mesa.Mesa;

public class Cliente extends Persona {
    private int cantAcompaniantes;
    private Mesa mesa; //mesa asignada


    public Cliente() {
    }

    public Cliente(String id) {
        super(id);
    }

    public Cliente(String nombre, String clave, String identificacion, int cantAcompaniantes, Mesa mesa) {
        super(nombre, clave, identificacion);
        this.cantAcompaniantes = cantAcompaniantes;
        this.mesa = mesa;
    }



    public Cliente(String nombre, String id, int cantAcompaniantes, Mesa mesa) {
        super(nombre, id);
        this.cantAcompaniantes = cantAcompaniantes;
        this.mesa = mesa;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public void setCantAcompaniantes(int cantAcompaniantes) {
        this.cantAcompaniantes = cantAcompaniantes;
    }
    public int getCantAcompaniantes() {
        return cantAcompaniantes;
    }

    public String getNombre() {
        return nombre;
    }


     public  String datos (){
        return "Nombre: "+nombre;
    }


    @Override
    public String toString() {
        return " Cliente: " +
                "\nNombre: " +nombre+
                "\nCantidad de acompañantes = " + cantAcompaniantes +
                "\nMesa=" + mesa;
    }
}




