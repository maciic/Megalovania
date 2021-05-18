package hu.unideb.inf.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLUserLogin implements Initializable {

    @FXML private TextField txf_userName;
    @FXML private TextField txf_userPassword;

    @FXML
    private void ac_login(){
        if(txf_userName.getText() != "" && txf_userPassword.getText() != ""){
            String username = txf_userName.getText();
            String password = txf_userPassword.getText();

            try {
                Stage currentStage = (Stage) txf_userName.getScene().getWindow();
                currentStage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/FXMLMainApp.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if(txf_userName.getText() == ""){
                txf_userName.setPromptText("Add meg a felasználó neved!");
            }
            if(txf_userPassword.getText() == ""){
                txf_userPassword.setPromptText("Add meg a jelszavad!");
            }
        }

    }

    @FXML
    private void ac_registration(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/FXMLUserRegistration.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
