package porras.dylan.proyectofinalfrontend_dylanporras.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import porras.dylan.bl.entities.mesa.Mesa;
import porras.dylan.bl.entities.orden.Orden;
import porras.dylan.bl.entities.persona.Cliente;
import porras.dylan.bl.entities.persona.Mesero;
import porras.dylan.bl.entities.producto.Producto;
import porras.dylan.bl.services.GestorMesa;
import porras.dylan.bl.services.GestorOrden;
import porras.dylan.proyectofinalfrontend_dylanporras.controllers.MenuRestauranteController;
import porras.dylan.proyectofinalfrontend_dylanporras.controllers.RegistrarClienteController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ListarOrdenController implements Initializable {
    public TableColumn<Orden,String> colCuenta;
    @FXML
    private TableColumn<Orden,String> colTipo;
    @FXML
    private TableColumn<Orden,String> colCanti;
    @FXML
    private TableColumn<Orden,String> colCodigo;
    @FXML
    private TableColumn<Orden,String> colPrecio;
    @FXML
    private TableView<Orden> tablaOrden;
    @FXML
    private TableColumn<Orden,String>colOrden;

    @FXML
    private TableColumn<Orden,String> colCliente;
    @FXML
    private TableColumn<Orden, String> colMesero;
    private GestorOrden gestor = new GestorOrden();

    ObservableList<Orden> ordenes = FXCollections.observableArrayList();


    public void initialize(URL url, ResourceBundle resourceBundl) {

        try {
            colTipo.setCellValueFactory(cellData -> {
                List<String> productos = cellData.getValue().getProductosCliente().stream()
                        .map(producto -> ""+ producto.getTipo())
                        .collect(Collectors.toList());
                String productosTexto = String.join(", ", productos);
                return new SimpleStringProperty(productosTexto);
            });

            colCanti.setCellValueFactory(cellData -> {
                List<String> productos = cellData.getValue().getProductosCliente().stream()
                        .map(producto -> ""+producto.getCantidad())
                        .collect(Collectors.toList());
                String productosTexto = String.join(", ", productos);
                return new SimpleStringProperty(productosTexto);
            });
            colCodigo.setCellValueFactory(cellData -> {
                List<String> productos = cellData.getValue().getProductosCliente().stream()
                        .map(producto ->""+producto.getCodigo())
                        .collect(Collectors.toList());
                String productosTexto = String.join(", ", productos);
                return new SimpleStringProperty(productosTexto);
            });

            colPrecio.setCellValueFactory(cellData -> {
                List<String> productos = cellData.getValue().getProductosCliente().stream()
                        .map(producto -> "₡ "+producto.getPrecio())
                        .collect(Collectors.toList());
                String productosTexto = String.join(", ", productos);
                return new SimpleStringProperty(productosTexto);
            });

            colCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClienteOrden().getNombre()));
            colOrden.setCellValueFactory(new PropertyValueFactory<>("idOrden"));
            colMesero.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMesero().getNombre()));
            colCuenta.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.valueOf(cellData.getValue().getIdCuenta()).toString()));

            mostrarOrdenes();

        }catch (Exception e){
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR-LISTAR-ORDEN"+e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }

    }

    protected void mostrarOrdenes(){
        gestor.listarOrden().forEach(Orden -> ordenes.addAll(Orden));
        tablaOrden.setItems(ordenes);
        tablaOrden.refresh();
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


    public void volverAMenu(ActionEvent event) {
        cambiarEscena(event);
    }
}
