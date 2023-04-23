package porras.dylan.proyectofinalfrontend_dylanporras.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import porras.dylan.bl.entities.persona.Mesero;
import porras.dylan.bl.services.GestorMesero;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Button btnRegistrarse;
    @FXML
    private TextField txtId;
    @FXML
    private PasswordField txtClave;
    private GestorMesero gestor = new GestorMesero();

    private static Mesero meseroSesion;

    @FXML
    protected void onBtnIngresar(ActionEvent event) {

     try{
         inicioSesion(event);

     } catch (Exception ex) {
         Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
         alert.setHeaderText("");
         alert.showAndWait();

     }

    }
    protected void inicioSesion(ActionEvent event){
        try{
            String id = txtId.getText();
            String clave = txtClave.getText();

            if (!validarCampos(clave,id)) {

                meseroSesion = gestor.buscarMesero(clave,id);

                if(meseroSesion!=null){
                    cambiarEscena(meseroSesion,event);
                }else{
                    mensaje();
                }
            }

        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        }
    }


    protected boolean validarCampos(String clave, String id) {
        boolean error = false;

        if (!id.matches("[0-9]*")) { //PERMITE SOLO USAR NUMEROS EN UNA CADENA DE STRING
            Alert alert = new Alert(Alert.AlertType.ERROR, "LA IDENTIFICACION SOLO PUEDE SER NUMEROS ENTEROS: (12345)");
            alert.setHeaderText(null);
            alert.showAndWait();
            error = true;

        }else if(id.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "INGRESE LA IDENTIFICACIÓN!");
            alert.setHeaderText(null);
            alert.showAndWait();
            error = true;

        }else if(clave.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "INGRESE LA CLAVE!");
            alert.setHeaderText(null);
            alert.showAndWait();
            error = true;
        }
        return error;
    }


    protected void cambiarEscena(Mesero mesero, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/MenuRestaurante-view.fxml"));
            Parent parentScene = loader.load();
            Scene newScene = new Scene(parentScene);
            MenuRestauranteController controller = loader.getController();
            Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.setTitle("MENU");
            window.centerOnScreen();
            window.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR DE INICIO DE SESION: "+ e.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    public void onBtnRegistrarse(ActionEvent event) throws IOException {
        Parent parentScene = FXMLLoader.load(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/RegistrarMesero-view.fxml"));
        Scene newScene = new Scene(parentScene);
        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.centerOnScreen();
        window.show();
    }

    public void mensaje(){
        Alert alert = new Alert(Alert.AlertType.ERROR, "USUARIO NO ENCONTRADO EN BASE DE DATOS");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static void setMesero(Mesero mesero) {
        meseroSesion = mesero;
    }

    public static Mesero getMesero() {
        return meseroSesion;
    }
}


