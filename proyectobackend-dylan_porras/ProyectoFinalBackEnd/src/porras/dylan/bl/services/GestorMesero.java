package porras.dylan.bl.services;

import porras.dylan.bl.entities.persona.Mesero;
import porras.dylan.bl.entities.persona.SQLServerMeseroDao;

public class GestorMesero {



    public GestorMesero(){
    }


    public Mesero insertarMesero(String nombre, String clave,String id) {
        SQLServerMeseroDao m = new SQLServerMeseroDao();
        Mesero mesero = new Mesero();
        mesero.setNombre(nombre);
        mesero.setId(id);
        mesero.setClave(clave);
        return m.insertar(mesero);

    }


    public Mesero buscarMesero(String clave,String identificacion){
        SQLServerMeseroDao buscarMesero = new SQLServerMeseroDao();
        Mesero mesero = new Mesero();
        mesero.setClave(clave);
        mesero.setId(identificacion);
        return buscarMesero.iniciarSesion(mesero);
    }





}
