package hu.unideb.inf;

import hu.unideb.inf.view.FXMLMainApp;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/FXMLUserLogin.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Megalovania");

        stage.setScene(scene);

        stage.setResizable(false);          //Nem engedi hogy átméretezzék az ablakot
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
