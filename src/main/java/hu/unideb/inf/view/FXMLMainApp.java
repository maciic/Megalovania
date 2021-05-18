/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.ConsoleHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class FXMLMainApp implements Initializable {

    ObservableList<String> cb_orderByCostList = FXCollections.observableArrayList("---", "Növekvő", "Csökkenő");

    @FXML private Label lb_itemsInBasket;
    @FXML private ChoiceBox cb_orderByCostBox;
    @FXML private ChoiceBox cb_orderByNameBox;
    @FXML private TextField txf_searchField;

    @FXML
    private void ac_addToBasket() {
        int szam = Integer.parseInt(lb_itemsInBasket.getText());
        szam++;
        lb_itemsInBasket.setText(String.valueOf(szam));
    }

    @FXML
    private void ac_openBasket(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/FXMLUserOrder.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ac_logout(){
        try {
            Stage currentStage = (Stage) lb_itemsInBasket.getScene().getWindow();
            currentStage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/FXMLUserLogin.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ac_search(){
        String search;
        if(txf_searchField.getText() != ""){
            search = txf_searchField.getText();
            System.out.println(search);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cb_orderByCostBox.setValue("---");
        cb_orderByCostBox.setItems(cb_orderByCostList);

        cb_orderByNameBox.setValue("Növekvő");
        cb_orderByNameBox.setItems(cb_orderByCostList);
    }
}
