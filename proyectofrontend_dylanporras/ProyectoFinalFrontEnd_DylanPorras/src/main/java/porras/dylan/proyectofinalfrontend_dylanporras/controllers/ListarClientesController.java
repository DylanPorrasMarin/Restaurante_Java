package porras.dylan.proyectofinalfrontend_dylanporras.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import porras.dylan.bl.entities.mesa.Mesa;
import porras.dylan.bl.entities.persona.Cliente;
import porras.dylan.bl.services.GestorCliente;

import java.net.URL;
import java.util.ResourceBundle;

public class ListarClientesController  implements Initializable {
    @FXML
    private TableColumn<Cliente,String> colNombre;
    @FXML
    private TableColumn<Cliente,String>  colCantidadAcomp;
    @FXML
    private TableColumn<Cliente,String>  colNumeroMesa;
    @FXML
    private TableColumn<Cliente,String>  colID;
    @FXML
    private TableView<Cliente>  tablaClientes;
    private GestorCliente gestorCliente = new GestorCliente();
    ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    public void onBtnAceptar(ActionEvent event) {
        cambiarEscena(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundl) {
        try {
            mostrarClientes();
        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }

    protected void mostrarClientes() {
        tablaClientes.getItems().clear();
        gestorCliente.listarClientes().forEach(Cliente-> clientes.addAll(Cliente));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCantidadAcomp.setCellValueFactory(new PropertyValueFactory<>("cantAcompaniantes"));
        colNumeroMesa.setCellValueFactory(new PropertyValueFactory<>("mesa"));
        tablaClientes.setItems(clientes);
        tablaClientes.refresh();
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
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR" + e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

}
