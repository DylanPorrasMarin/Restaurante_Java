package porras.dylan.bl.entities.persona;

import java.util.Objects;

public class Persona {
    protected String nombre;
    protected String clave;
    protected String id;

    public Persona() {
    }

    public Persona(String nombre, String clave, String identificacion) {
        this.nombre = nombre;
        this.clave = clave;
        this.id = identificacion;
    }

    public Persona(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }

    public Persona(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




    @Override
    public String toString() {
        return "Persona: " +
                "\nnombre= " + nombre +
                "\nidentificacion='" + id;
    }
}
