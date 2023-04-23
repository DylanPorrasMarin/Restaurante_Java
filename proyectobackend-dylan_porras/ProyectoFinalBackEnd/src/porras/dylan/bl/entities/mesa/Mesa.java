package porras.dylan.bl.entities.mesa;

public class Mesa {
    private String numeroMesa;
    private int capacidad;
    private boolean disponible;

    public Mesa() {
    }

    public Mesa(String numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Mesa(String numeroMesa, int capacidad ) {
        this.numeroMesa = numeroMesa;
        this.capacidad = capacidad;
        this.disponible = true;
    }
    public Mesa(String numeroMesa, int capacidad, boolean disponible) {
        this.numeroMesa = numeroMesa;
        this.capacidad = capacidad;
        this.disponible = disponible;
    }

    public String getNumeroMesa() {
        return numeroMesa;
    }



    public void setNumeroMesa(String numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return  "Numero: " + numeroMesa +
                "\ncapacidad: " + capacidad;
    }
}
