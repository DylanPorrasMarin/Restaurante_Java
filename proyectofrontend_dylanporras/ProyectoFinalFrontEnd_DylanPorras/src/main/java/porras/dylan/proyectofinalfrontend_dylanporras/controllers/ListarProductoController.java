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
import javafx.stage.Stage;
import porras.dylan.bl.entities.producto.Producto;
import porras.dylan.bl.services.GestorProducto;

import java.net.URL;
import java.util.ResourceBundle;

public class ListarProductoController implements Initializable {
    @FXML
    private TableView<Producto> tableProductos;
    @FXML
    private TableColumn<Producto,String> colCodigo;
    @FXML
    private TableColumn<Producto,String> colDescripcion;
    @FXML
    private TableColumn<Producto,String>colCantidad;
    @FXML
    private TableColumn<Producto,String> colTipo;
    @FXML
    private TableColumn<Producto,String>colPrecio;
    private GestorProducto gestorProducto = new GestorProducto();
    private ObservableList<Producto> productos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            mostrarProductos();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    @FXML
    protected void onBtnAceptar(ActionEvent event) {
        cambiarEscena(event);
    }


    protected void mostrarProductos() throws Exception {
        gestorProducto.listarProductos().forEach(Producto -> productos.addAll(Producto));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tableProductos.setItems(productos);
        tableProductos.refresh();
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
