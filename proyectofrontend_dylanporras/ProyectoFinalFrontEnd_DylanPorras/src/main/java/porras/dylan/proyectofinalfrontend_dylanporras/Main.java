package porras.dylan.proyectofinalfrontend_dylanporras;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/porras/dylan/proyectofinalfrontend_dylanporras/Views/Login-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.centerOnScreen();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}