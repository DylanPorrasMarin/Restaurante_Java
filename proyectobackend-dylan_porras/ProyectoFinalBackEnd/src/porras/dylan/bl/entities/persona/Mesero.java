package porras.dylan.bl.entities.persona;

import java.util.Objects;

public class Mesero extends Persona {
    
    public Mesero() {
        super();
    }

    public Mesero(String id) {
        super(id);
    }

    public Mesero(String nombre, String clave, String identificacion) {
        super(nombre, clave, identificacion);
    }

    public Mesero(String clave, String identificacion){
        super(clave,identificacion);
    }


    @Override
    public String toString() {
        return "Mesero{" +
                "nombre='" + nombre + '\'' +
                ", clave='" + clave + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
