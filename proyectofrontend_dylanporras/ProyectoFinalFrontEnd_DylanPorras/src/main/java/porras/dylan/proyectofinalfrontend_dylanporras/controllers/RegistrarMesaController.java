package porras.dylan.proyectofinalfrontend_dylanporras.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import porras.dylan.bl.entities.mesa.Mesa;
import porras.dylan.bl.services.GestorMesa;

public class RegistrarMesaController {
    @FXML
    private Button btnRegistrarMesa;
    @FXML
    private TextField txtNumeroMesa;
    @FXML
    private TextField txtCapacidad;

    private GestorMesa gestorMesa = new GestorMesa();



    @FXML
    protected void onBtnRegistrarMesa(ActionEvent event) {
        String numeroMesa = txtNumeroMesa.getText();
        Mesa mesa;

        if (!validarCampos(numeroMesa,txtCapacidad.getText())){ //Aquí, se agrega el segundo parámetro "txtCapacidad.getText()" en la llamada a validarCampos(), para pasar el valor del campo "capacidad". Si la validación es exitosa, el valor de "capacidad" se convierte en un entero y se registra la mesa, de lo contrario, no se realiza ninguna acción.
            int capacidad = Integer.parseInt(txtCapacidad.getText());
            mesa= gestorMesa.registrarMesa(numeroMesa, capacidad);
            if(mesa!=null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "MESA REGISTRADA CON EXITO");
                alert.setHeaderText(null);
                alert.initModality(Modality.WINDOW_MODAL);
                alert.showAndWait();
            }

            cambiarEscena(event);
       }
    }

    protected boolean validarCampos(String numeroMesa, String capacidad ) {
        boolean error = false;

        if (!numeroMesa.matches("[0-9]*")) { //PERMITE SOLO USAR NUMEROS EN UNA CADENA DE STRING
            Alert alert = new Alert(Alert.AlertType.ERROR, "EL NUMERO DE MESA SOLO PUEDE SER NUMEROS ENTEROS: (12345)");
            alert.setHeaderText(null);
            alert.showAndWait();
            error = true;

        }else if(numeroMesa.equals("") || capacidad.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "ESPACIO/S SIN LLENAR, INTENTE DE NUEVO");
            alert.setHeaderText(null);
            alert.showAndWait();
            error = true;
        } else  if (!capacidad.matches("\\d+")) { //PERMITE SOLO USAR NUMEROS ENTEROS EN UNA CADENA DE STRING
            Alert alert = new Alert(Alert.AlertType.ERROR, "EL NUMERO DE CAPACIDAD SOLO PUEDE SER NUMEROS ENTEROS: (12345)");
            alert.setHeaderText(null);
            alert.showAndWait();
            error = true;
        }

        return error;
    }

    protected void cambiarEscena(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/MenuRestaurante-view.fxml"));
            Parent parentScene = loader.load();
            Scene newScene = new Scene(parentScene);
            Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.centerOnScreen();
            window.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR"+e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    protected void RegistrarMesa(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/RegistrarMesa-view.fxml"));
            Parent parentScene = loader.load();
            Scene newScene = new Scene(parentScene);
            Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.centerOnScreen();
            window.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR"+e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }


    }

    @FXML
    protected void volverAMenu(ActionEvent event) {
        cambiarEscena(event);
    }
}
