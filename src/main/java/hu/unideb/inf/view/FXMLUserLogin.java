package hu.unideb.inf.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import hu.unideb.inf.model.UsersModel;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLUserLogin implements Initializable {

    @FXML private TextField txf_userName;
    @FXML private TextField txf_userPassword;
    @FXML private Label label_WrongPassword;
    @FXML public int Glob_userID;


    @FXML
    private void ac_login(){
        if((!txf_userName.getText().isEmpty()) && (!txf_userPassword.getText().isEmpty())){
            if(UsersModel.getUser(txf_userName.getText())!=null)
                if(UsersModel.getUser(txf_userName.getText()).get(2).equals(txf_userPassword.getText())){




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
            }else label_WrongPassword.setText("Helytelen jelszó!");
        } else {
            if(txf_userName.getText().isEmpty()){
                txf_userName.setPromptText("Add meg a felasználó neved!");
            }
            if(txf_userPassword.getText().isEmpty()){
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
