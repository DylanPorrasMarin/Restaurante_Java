package porras.dylan.proyectofinalfrontend_dylanporras.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import porras.dylan.bl.entities.cuenta.Cuenta;

import porras.dylan.bl.services.GestorCuenta;




public class CuentaController {


    @FXML
    private TextField txtNombre;

    private static Cuenta cuenta = new Cuenta();
    private GestorCuenta gestorCuenta = new GestorCuenta();

    @FXML
    protected void generarCuenta(ActionEvent event) {
        String nombreC = txtNombre.getText();
        cuenta = gestorCuenta.registrarCuenta(nombreC);
        if(cuenta!=null){
            cambiarEscena(event);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "CUENTA NO REGISTRADA");
            alert.setHeaderText(null);
            alert.showAndWait();
        }


    }
    protected void cambiarEscena(ActionEvent event) {
        try {
            Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/CuentaGenerada-view.fxml"));
            Parent parentScene = loader.load();
            Scene newScene = new Scene(parentScene);
            CuentaGeneradaController controller = loader.getController();
            window.setScene(newScene);
            window.setTitle("Cuenta Generada Cliente");
            window.centerOnScreen();
            window.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERRORCIUENTA\n" + e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    protected static Cuenta getCuentagenerada() {
        return cuenta;
    }

    public void volverAMenu(ActionEvent event) {
        try {
        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/MenuRestaurante-view.fxml"));
        Parent parentScene = loader.load();
        Scene newScene = new Scene(parentScene);
        window.setScene(newScene);
        window.centerOnScreen();
        window.show();
    } catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "ERRORCIUENTA\n" + e.getMessage());
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    }
}
