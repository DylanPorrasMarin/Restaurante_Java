package porras.dylan.bl.services;

import porras.dylan.bl.entities.mesa.Mesa;
import porras.dylan.bl.entities.mesa.SQLServerMesaDao;

import java.util.ArrayList;

public class GestorMesa {



    public GestorMesa (){
    }

    public Mesa registrarMesa(String id, int capacidad){
        SQLServerMesaDao insertar = new SQLServerMesaDao();
        Mesa mesa = new Mesa(id,capacidad);
        return insertar.insertarMesa(mesa);
    }



    public ArrayList<Mesa> listarMesas(){
        SQLServerMesaDao sqlServerMesa = new SQLServerMesaDao();
        return sqlServerMesa.listarMesas();
    }



    public ArrayList<Mesa> eliminarMesa(Mesa mesa) throws Exception{
        SQLServerMesaDao eliminarMesa = new SQLServerMesaDao();
        return eliminarMesa.eliminarMesa(mesa);
    }
}
