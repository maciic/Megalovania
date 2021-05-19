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
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FXMLMainApp implements Initializable {

    ObservableList<String> cb_orderByCostList = FXCollections.observableArrayList("---", "Növekvő", "Csökkenő");

    @FXML private Label lb_itemsInBasket;
    @FXML private ChoiceBox cb_orderByCostBox;
    @FXML private ChoiceBox cb_orderByNameBox;
    @FXML private TextField txf_searchField;
    @FXML private Button bt_loginName;

    @FXML private AnchorPane prod_list;

    @FXML
    private void ac_addToBasket() {
        lb_itemsInBasket.setText(String.valueOf(Integer.parseInt(lb_itemsInBasket.getText())+1));

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
    private void ac_listUserOrders(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/FXMLlistUserOrders.fxml"));
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

        bt_loginName.setText("Felhasználó");

        int counter = 0;
        int x = 0;
        int y = 0;


        String name;
        int id;
        int price = 250;

        for(int i = 0; i < 40; i++)
        {
            if(i*50*i > prod_list.getPrefHeight())
            {
                prod_list.setPrefHeight((i)*50+(i));
            }

            if(x < 5)
            {
                prod_list.getChildren().add(createPane(x*220, y * 230, "pofone", 3500, price));
                x++;
            }
            else
            {
                y++;
                x = 0;
                prod_list.getChildren().add(createPane(x*220, y * 230, "pofone", 3500, price));
            }

        }
    }

    private AnchorPane createPane(int xpadd, int ypadd, String name, int id, int cost){

        AnchorPane ap_item = new AnchorPane();
        Label lb_name = new Label(name);
        Button bt_buyitem = new Button("Vásárlás");
        Label lb_cost = new Label(String.valueOf(cost) + "ft");

        bt_buyitem.setLayoutX(70);
        bt_buyitem.setLayoutY(140);
        bt_buyitem.setStyle("-fx-background-color: #E1DFD; -fx-border-color: #000; -fx-background-radius: .5em; -fx-border-radius: .5em;");
        bt_buyitem.setCursor(Cursor.HAND);
        bt_buyitem.setAlignment(Pos.CENTER);
        bt_buyitem.setOnAction(actionEvent -> ac_addToBasket());

        lb_name.setFont(javafx.scene.text.Font.font ("System", 30));
        lb_name.setLayoutX(55);
        lb_name.setLayoutY(40);
        lb_name.setAlignment(Pos.CENTER);

        lb_cost.setFont(Font.font ("System", 30));
        lb_cost.setLayoutX(74);
        lb_cost.setLayoutY(80);
        lb_name.setAlignment(Pos.CENTER);

        ap_item.setStyle("-fx-background-color: #D37D6A; -fx-background-radius: .5em; -fx-border-radius: 3em;");
        ap_item.setPrefHeight(200);
        ap_item.setPrefWidth(200);
        ap_item.setLayoutX(xpadd + 40);
        ap_item.setLayoutY(ypadd + 20);

        ap_item.getChildren().add(lb_name);
        ap_item.getChildren().add(bt_buyitem);
        ap_item.getChildren().add(lb_cost);

        return ap_item;
    }

}
