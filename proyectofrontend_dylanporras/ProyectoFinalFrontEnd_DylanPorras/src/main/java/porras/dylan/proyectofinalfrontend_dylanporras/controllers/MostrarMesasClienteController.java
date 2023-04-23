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
import porras.dylan.bl.services.GestorMesa;

import java.net.URL;
import java.util.ResourceBundle;

public class MostrarMesasClienteController implements Initializable {

    @FXML
    private TableColumn<Mesa,String> columnID;
    @FXML
    private TableColumn<Mesa,String> columnCapacidad;
    @FXML
    private TableColumn<Mesa,String> columnDisponible;
    @FXML
    private TableView<Mesa> tablaMesas;


    private GestorMesa gestor = new GestorMesa();

    ObservableList<Mesa> mesas = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundl) {
        try {
            mostrarMesas();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
    protected void mostrarMesas(){
        tablaMesas.getItems().clear();
        gestor.listarMesas().forEach(Mesa -> mesas.addAll(Mesa));
        columnID.setCellValueFactory(new PropertyValueFactory<>("numeroMesa"));
        columnCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        columnDisponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));
        tablaMesas.setItems(mesas);
        tablaMesas.refresh();
    }

    public void refrescar(MouseEvent event) {
        mostrarMesas();
    }

    public void onBtnVolverMenu( ActionEvent event) {
        try {
            cambiarEscena(event);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR"+e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }

    }

    protected void cambiarEscena(ActionEvent event) {
        try {
            Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/RegistrarCliente-view.fxml"));
            RegistrarClienteController controller = loader.getController();
            window.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR"+e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }
}
