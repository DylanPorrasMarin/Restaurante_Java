package porras.dylan.proyectofinalfrontend_dylanporras.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import porras.dylan.bl.entities.persona.Mesero;
import porras.dylan.bl.services.GestorMesero;

public class RegistrarMeseroController {
    @FXML
    private TextField txtIdentificacion;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtClave;
    private Mesero mesero;

    @FXML
    protected void RegistrarMesero(ActionEvent event) {

        try {
          insertarMesero(event);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "VALORES NO VALIDOS O CAMPOS SIN RELLENAR, INTENTE DE NUEVO");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

    }
    protected void cambiarEscena(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/Login-view.fxml"));
            Parent parentScene = loader.load();
            Scene newScene = new Scene(parentScene);
            Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.centerOnScreen();
            window.show();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR AL CAMBIAR LA VENTANA: " + e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }
    protected void insertarMesero(ActionEvent event){
        try{
            String id = txtIdentificacion.getText();
            String nombre = txtNombre.getText();
            String clave = txtClave.getText();

        if(!validarCampos(clave,nombre,id)){
            GestorMesero gestor = new GestorMesero();
            mesero = gestor.insertarMesero(nombre,clave,id);
            if(mesero!= null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "REGISTRO EXITOSO");
                alert.setHeaderText(null);
                alert.showAndWait();
                cambiarEscena(event);
            }

        }

    }catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        }
    }

    protected boolean validarCampos(String clave, String nombre, String id) {
        boolean error = false;
        if (!id.matches("[0-9]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "LA IDENTIFICACION SOLO PUEDE SER NUMEROS ENTEROS: (12345)");
            alert.setHeaderText(null);
            alert.showAndWait();
            error = true;

        }else if(clave.equals("") || id.equals("") || nombre.equals("") ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ESPACIO/S, SIN RELLENAR, INTENTE DE NUEVO!");
            alert.setHeaderText(null);
            alert.showAndWait();
            error = true;
        }
        return error;
    }

    @FXML
    protected void volverALogin(ActionEvent event) {
        cambiarEscena(event);
    }
}
