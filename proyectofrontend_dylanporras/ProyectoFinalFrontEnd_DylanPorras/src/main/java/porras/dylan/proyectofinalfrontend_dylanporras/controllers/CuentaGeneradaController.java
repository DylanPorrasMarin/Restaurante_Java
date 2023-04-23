package porras.dylan.proyectofinalfrontend_dylanporras.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import porras.dylan.bl.entities.cuenta.Cuenta;
import porras.dylan.bl.services.GestorCuenta;

import java.net.URL;
import java.util.ResourceBundle;

public class CuentaGeneradaController implements Initializable {

    @FXML
    private Label lblNumeroCuenta;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblImpuestoVenta;
    @FXML
    private Label lblImpuestoServicio;
    @FXML
    private Label lblSubtotal;
    @FXML
    private Label lblTotal;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            rellenarCuenta();
        }catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR-CUENTA-GENERADA\n" + ex.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();

        }

    }
    public void rellenarCuenta(){

        lblNumeroCuenta.setText(GestorCuenta.generarNumeroFactura());
        lblFecha.setText(CuentaController.getCuentagenerada().getFechaH());
        lblImpuestoVenta.setText("%"+CuentaController.getCuentagenerada().getImpuestoVentas()+"");
        lblImpuestoServicio.setText("%"+CuentaController.getCuentagenerada().getImpuestoServicio()+"");
        lblSubtotal.setText("₡"+CuentaController.getCuentagenerada().getSubTotal()+"");
        lblTotal.setText("₡"+CuentaController.getCuentagenerada().getTotal()+"");
    }


    public void volverAMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/MenuRestaurante-view.fxml"));
            Parent parentScene = loader.load();
            Scene newScene = new Scene(parentScene);
            Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.centerOnScreen();
            window.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR DE INICIO DE SESION: "+ e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }

    }

}

