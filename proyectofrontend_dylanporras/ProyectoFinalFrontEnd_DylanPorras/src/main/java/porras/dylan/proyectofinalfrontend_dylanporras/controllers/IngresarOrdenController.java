package porras.dylan.proyectofinalfrontend_dylanporras.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import porras.dylan.bl.entities.orden.Orden;
import porras.dylan.bl.entities.persona.Mesero;
import porras.dylan.bl.services.GestorOrden;

import javax.swing.*;
import java.util.Optional;


public class IngresarOrdenController {
    @FXML
    private TextField txtProductoOrden;
    @FXML
    private TextField txtCantidadOrden;
    @FXML
    private TextField txtIdCliente;
   // private Mesero meseroSesion;


    public void AgregarOrden(ActionEvent event){
        String codigoP = txtProductoOrden.getText();
        String idCliente = txtIdCliente.getText();


        GestorOrden gestorOrden = new GestorOrden();

        if(!validarCampos(codigoP,idCliente,txtCantidadOrden.getText())){
        int cantidadP = Integer.parseInt(txtCantidadOrden.getText());
        Orden orden = gestorOrden.agregarOrden(LoginController.getMesero().getId(),idCliente,codigoP,cantidadP);

        if(orden!=null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "CLIENTE REGISTRADO CON EXITO");
            alert.setHeaderText(null);
            alert.setTitle("Confirmación");
            alert.setContentText("¿DESEA REGISTRAR OTRO PRODUCTO?");

            // Obtener los botones y cambiar el texto
            Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            okButton.setText("Sí");

            Button cancelButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
            cancelButton.setText("No");

            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() != ButtonType.OK) {
                cambiarEscena(event);
            } else {
                txtCantidadOrden.setText("");
                txtProductoOrden.setText("");
            }
          }
        }
    }


    protected boolean validarCampos(String codigoP,String idCliente, String cantidadP ) {
        boolean error = false;

        if (!idCliente.matches("[0-9]*")) { //PERMITE SOLO USAR NUMEROS EN UNA CADENA DE STRING
            Alert alert = new Alert(Alert.AlertType.ERROR, "EL NUMERO DE MESA SOLO PUEDE SER NUMEROS ENTEROS: (12345)");
            alert.setHeaderText(null);
            alert.showAndWait();
            error = true;

        }else if(codigoP.equals("") || idCliente.equals("") || cantidadP.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "ESPACIO/S SIN LLENAR, INTENTE DE NUEVO");
            alert.setHeaderText(null);
            alert.showAndWait();
            error = true;

        }

        return error;
    }

    protected void cambiarEscena( ActionEvent event) {
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


    @FXML
    protected void verClientes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/ListarClienteOrden-view.fxml"));
            Parent parentScene = loader.load();
            ListarClienteOrdenController controller = loader.getController();
            Scene newScene = new Scene(parentScene);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR"+e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    @FXML
    protected void verProductos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/ListarProductoOrden-view.fxml"));
            Parent parentScene = loader.load();
            ListarProductoOrdenController controller = loader.getController();
            Scene newScene = new Scene(parentScene);
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR"+e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }

    }

    public void volverAMenu(ActionEvent event) {
        try {
            Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/MenuRestaurante-view.fxml"));
            Parent parentScene = loader.load();
            MenuRestauranteController controller = loader.getController();
            Scene newScene = new Scene(parentScene);
            window.setScene(newScene);
            window.centerOnScreen();
            window.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR"+e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }


}
