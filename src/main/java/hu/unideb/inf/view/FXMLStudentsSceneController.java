/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.view;

import hu.unideb.inf.model.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author student
 */
public class FXMLStudentsSceneController implements Initializable {
    
    private Model model;

    public void setModel(Model model) {
        this.model = model;
    }
        
    @FXML
    private Label colorLabel;
    
    @FXML
    void handleButtonPushed() {
        System.out.println("Button!!!!");
        /*if (colorLabel.getText().equals("Black")) {
            colorLabel.setText("White");
        } else {
            colorLabel.setText("Black");
        }*/colorLabel.setText(model.getStudent().getName());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
