package porras.dylan.bl.services;

import porras.dylan.bl.entities.mesa.Mesa;
import porras.dylan.bl.entities.persona.Cliente;
import porras.dylan.bl.entities.persona.SQLServerClienteDao;

import javax.swing.*;
import java.util.ArrayList;

public class GestorCliente {



    public GestorCliente(){

    }

    public Cliente registrarCliente(String nombre, int cantidadAcompaniantes,String numeroMesa){
        SQLServerClienteDao insertarC = new SQLServerClienteDao();
        Mesa m = new Mesa(numeroMesa);
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setMesa(m);
        cliente.setCantAcompaniantes(cantidadAcompaniantes);
        return insertarC.insertar(cliente);
    }


    public ArrayList<Cliente> listarClientes(){
        SQLServerClienteDao listarC = new SQLServerClienteDao();
        return listarC.listarCliente();

    }


    public String eliminarCliente(String nombre) throws Exception{
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        return "Cliente eliminado";
    }

}
