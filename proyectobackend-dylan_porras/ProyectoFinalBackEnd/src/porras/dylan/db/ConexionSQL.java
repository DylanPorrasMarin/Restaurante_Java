package porras.dylan.db;

import javax.swing.*;
import java.sql.Connection;
import java.sql.*;

public class ConexionSQL {

     public  Connection getConexion(){
        String url = "jdbc:sqlserver://DYLAN-LAP;DatabaseName=proyectoRestaurante;user=sa;password=1234";
        try{
            return DriverManager.getConnection(url);
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo establecer la conexion, revisar el driver" + "" + e.getMessage(), "ERROR DE CONEXION", JOptionPane.ERROR_MESSAGE);
            return  null;
        }

    }
}
