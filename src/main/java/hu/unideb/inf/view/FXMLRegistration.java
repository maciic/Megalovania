package hu.unideb.inf.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import hu.unideb.inf.model.UsersModel;
import javafx.stage.Stage;

public class FXMLRegistration implements Initializable {

    @FXML private TextField txf_RegistrateUserId;
    @FXML private TextField txf_RegistratePassword;
    @FXML private TextField txf_RegistratePassConfirm;
    @FXML private Label PasswordError;

    @FXML
    private void ac_RegistrateUserFunct() throws InterruptedException {
        if(txf_RegistrateUserId.getText().isEmpty()){
            PasswordError.setText("Nem adott meg felhasználó nevet!");
        } else if((txf_RegistratePassword.getText().compareTo(txf_RegistratePassConfirm.getText()))!=0){
            PasswordError.setText("A két jelszó nem egyezik meg!");
        } else{
            if(UsersModel.insertUser(txf_RegistrateUserId.getText(),txf_RegistratePassword.getText())==true) {
                PasswordError.setText("Regisztráció sikeres");
                //wait(500);
            }
            else
                PasswordError.setText("A felhasználónév már foglalt");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}