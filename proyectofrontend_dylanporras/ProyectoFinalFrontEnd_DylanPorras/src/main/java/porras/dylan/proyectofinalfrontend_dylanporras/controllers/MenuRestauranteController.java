package porras.dylan.proyectofinalfrontend_dylanporras.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import porras.dylan.bl.entities.persona.Mesero;


import java.net.URL;
import java.util.ResourceBundle;

public class MenuRestauranteController implements Initializable {
    @FXML
    private Label lblBienvenida;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblBienvenida.setText("EN SESION: "+ LoginController.getMesero().getNombre());
    }
    @FXML
    protected void listarMesas(ActionEvent event) {
        cambiarEscena(event);
    }

    protected void cambiarEscena(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/ListarMesa-view.fxml"));
            Parent parentScene = loader.load();
            Scene newScene = new Scene(parentScene);
            Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.setTitle("Listar Mesas");
            window.centerOnScreen();
            window.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR"+e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    @FXML
    protected void RegistrarMesa(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/RegistrarMesa-view.fxml"));
            Parent parentScene = loader.load();
            Scene newScene = new Scene(parentScene);
            Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.setTitle("Registro Mesas");
            window.centerOnScreen();
            window.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR"+e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    @FXML
    protected void mostrarRegistrarCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/RegistrarCliente-view.fxml"));
            Parent parentScene = loader.load();
            Scene newScene = new Scene(parentScene);
            Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.setTitle("Registro Clientes");
            window.centerOnScreen();
            window.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR"+e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    @FXML
    protected void mostrarbtnRegistrarProducto(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/RegistrarProducto-view.fxml"));
            Parent parentScene = loader.load();
            Scene newScene = new Scene(parentScene);
            Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.setTitle("Registro Productos");
            window.centerOnScreen();
            window.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR"+e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    @FXML
    protected void mostrarProductos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/ListarProducto-view.fxml"));
            Parent parentScene = loader.load();
            Scene newScene = new Scene(parentScene);
            Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.setTitle("Listar Productos");
            window.centerOnScreen();
            window.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR"+e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    public void MostrarClientes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/ListarCliente.fxml"));
            Parent parentScene = loader.load();
            Scene newScene = new Scene(parentScene);
            Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.setTitle("Listar Clientes");
            window.centerOnScreen();
            window.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR"+e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    public void MostrarIngresarOrden(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/IngresarOrden-view.fxml"));
            Parent parentScene = loader.load();
            Scene newScene = new Scene(parentScene);
            IngresarOrdenController controller = loader.getController();
            Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.setTitle("Ingreso Orden");
            window.centerOnScreen();
            window.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR"+e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    public void listarOrdenes(ActionEvent event) {
        try {
            Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/ListarOrden-view.fxml"));
            Parent parentScene = loader.load();
            Scene newScene = new Scene(parentScene);
            ListarOrdenController controller = loader.getController();
            window.setScene(newScene);
            window.setTitle("Listar Ordenes");
            window.centerOnScreen();
            window.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR"+e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    public void mostrarCuenta(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/Cuenta-view.fxml"));
            Parent parentScene = loader.load();
            Scene newScene = new Scene(parentScene);
            Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.setTitle("Cuenta");
            window.centerOnScreen();
            window.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR"+e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }
}

