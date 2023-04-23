package porras.dylan.proyectofinalfrontend_dylanporras.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import porras.dylan.bl.entities.persona.Cliente;
import porras.dylan.bl.services.GestorCliente;

import javax.swing.*;
import java.util.Optional;

public class RegistrarClienteController {
    @FXML
    private TextField txtNumeroMesa;
    @FXML
    private TextField txtNombreC;
    @FXML
    private TextField txtCantidadAcom;
    private GestorCliente gestorCliente = new GestorCliente();
    private Cliente cliente;

    @FXML
    protected void RegistrarCliente(ActionEvent event) {
        String nombre = txtNombreC.getText();
        String numeroMesa =txtNumeroMesa.getText();

        if (!validarCampos(nombre,numeroMesa,txtCantidadAcom.getText())){ //Aquí, se agrega el segundo parámetro "txtCapacidad.getText()" en la llamada a validarCampos(), para pasar el valor del campo "capacidad". Si la validación es exitosa, el valor de "capacidad" se convierte en un entero y se registra la mesa, de lo contrario, no se realiza ninguna acción.
            int cantidadAcom=Integer.parseInt(txtCantidadAcom.getText());
            cliente= gestorCliente.registrarCliente(nombre,cantidadAcom,numeroMesa);

            if(cliente!=null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "CLIENTE REGISTRADO CON EXITO");
                alert.setHeaderText(null);
                alert.setTitle("Confirmación");
                alert.setContentText("¿DESEA REGISTRAR OTRO CLIENTE?");

                // Obtener los botones y cambiar el texto
                Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                okButton.setText("Sí");

                Button cancelButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
                cancelButton.setText("No");

                Optional<ButtonType> action = alert.showAndWait();

                if (action.get() != ButtonType.OK) {
                    cambiarEscena(event);
                } else {
                    txtNumeroMesa.setText("");
                    txtCantidadAcom.setText("");
                    txtNombreC.setText("");
                }
            }
        }
    }

    protected boolean validarCampos(String nombre,String numeroMesa, String cantidadAcomp ) {
        boolean error = false;

        if (!numeroMesa.matches("[0-9]*")) { //PERMITE SOLO USAR NUMEROS EN UNA CADENA DE STRING
            Alert alert = new Alert(Alert.AlertType.ERROR, "EL NUMERO DE MESA SOLO PUEDE SER NUMEROS ENTEROS: (12345)");
            alert.setHeaderText(null);
            alert.showAndWait();
            error = true;

        }else if(numeroMesa.equals("") || cantidadAcomp.equals("") || nombre.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "ESPACIO/S SIN LLENAR, INTENTE DE NUEVO");
            alert.setHeaderText(null);
            alert.showAndWait();
            error = true;

        } else  if (!cantidadAcomp.matches("\\d+")) { //PERMITE SOLO USAR NUMEROS ENTEROS EN UNA CADENA DE STRING
            Alert alert = new Alert(Alert.AlertType.ERROR, "EL NUMERO DE ACOMPAÑTES SOLO PUEDE SER NUMEROS ENTEROS: (12345)");
            alert.setHeaderText(null);
            alert.showAndWait();
            error = true;
        }

        return error;
    }

    @FXML
    protected void MostrarMesas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/MostrarMesasCliente-view.fxml"));
            Parent parentScene = loader.load();
            MostrarMesasClienteController controller = loader.getController();
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
    protected void volverAMenu(ActionEvent event) {
        cambiarEscena(event);
    }
}
