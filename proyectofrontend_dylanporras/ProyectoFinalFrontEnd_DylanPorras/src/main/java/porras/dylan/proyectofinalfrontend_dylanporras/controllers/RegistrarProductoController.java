package porras.dylan.proyectofinalfrontend_dylanporras.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import porras.dylan.bl.entities.producto.Producto;
import porras.dylan.bl.services.GestorProducto;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegistrarProductoController implements Initializable {

    public ToggleGroup tipoProductoGroup;
    @FXML
    private RadioButton platilloRadioButton;
    @FXML
    private RadioButton bebidaRadioButton;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtCantidad;
    @FXML
    private TextArea txtDescripcion;

    private GestorProducto gestorProducto = new GestorProducto();
    private Producto producto;
    private String seleccion;

    @FXML
    protected void onBtnRegistrarProducto(ActionEvent event) {

        String codigo = txtCodigo.getText();
        String descripcion = txtDescripcion.getText();


        if(!validarCampos(codigo,descripcion,txtCantidad.getText(),txtPrecio.getText())){
            int cantidad = Integer.parseInt(txtCantidad.getText());
            double precio = Double.parseDouble(txtPrecio.getText());
            producto= gestorProducto.registrarProducto(codigo,descripcion,cantidad,seleccion,precio);
            if(producto!=null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("PRODUCTO REGISTRADO CON EXITO");
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
                    txtCodigo.setText("");
                    txtDescripcion.setText("");
                    txtPrecio.setText("");
                    txtCantidad.setText("");
                }
            }

        }
    }

    public void volverAmenu(ActionEvent event) {
        cambiarEscena(event);
    }

    protected boolean validarCampos(String codigo,String descripcion, String cantidad,String precio) {
        boolean error = false;

        if (!cantidad.matches("[0-9]*")) { //PERMITE SOLO USAR NUMEROS EN UNA CADENA DE STRING
            Alert alert = new Alert(Alert.AlertType.ERROR, "EL NUMERO DE CANTIDAD SOLO PUEDE SER NUMEROS ENTEROS: (12345)");
            alert.setHeaderText(null);
            alert.showAndWait();
            error = true;

        }else if(codigo.equals("") || descripcion.equals("") || cantidad.equals("") ||precio.equals("") || seleccion==null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "ESPACIO/S SIN LLENAR, INTENTE DE NUEVO");
            alert.setHeaderText(null);
            alert.showAndWait();
            error = true;

        } else if (!precio.matches("\\d+(\\.\\d+)?")) { //PERMITE USAR NUMEROS ENTEROS Y DECIMALES EN UNA CADENA DE STRING
            Alert alert = new Alert(Alert.AlertType.ERROR, "EL PRECIO SOLO PUEDEN SER NUMEROS (12345.67)");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bebidaRadioButton.setToggleGroup(tipoProductoGroup);
        platilloRadioButton.setToggleGroup(tipoProductoGroup);
        tipoProductoGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (tipoProductoGroup.getSelectedToggle() != null) {
                seleccion = ((RadioButton) tipoProductoGroup.getSelectedToggle()).getText();
            }
        });
    }
}
