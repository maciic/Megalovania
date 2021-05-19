package hu.unideb.inf.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLlistUserOrders implements Initializable {

    @FXML private AnchorPane ap_listOfOrders;

    private AnchorPane addItem(int count){

        AnchorPane ap_item = new AnchorPane();
        Label lb_itemName = new Label("valami item");
        Label lb_itemPrice = new Label("10000");

        lb_itemName.setFont(Font.font(16));
        lb_itemName.setLayoutX(50);
        lb_itemName.setLayoutY(15);

        lb_itemPrice.setFont(Font.font(16));
        lb_itemPrice.setLayoutX(500);
        lb_itemPrice.setLayoutY(15);

        ap_item.setStyle("-fx-background-color: #none; -fx-border-color: #000; -fx-background-radius: 2em; -fx-border-radius: 2em;");
        ap_item.setPrefHeight(50);
        ap_item.setPrefWidth(650);
        ap_item.setLayoutX(400);
        ap_item.setLayoutY(count + 10);

        ap_item.getChildren().add(lb_itemName);
        ap_item.getChildren().add(lb_itemPrice);

        return ap_item;
    }

    private AnchorPane createPane(){

        String billName = "REvesz";
        String billCity = "Tuzser";
        String billPostalCode = "4233";
        String billAddress = "macilaci";
        String billHouseNumber = "5";

        String transportName = "Revesz";
        String transportCity = "élkj";
        String transportPostalCode = "1233";
        String transportAddress = "élkjasdf";
        String transportHouseNumber = "9";

        AnchorPane ap_order = new AnchorPane();
        Label lb_orderID = new Label();

        Label lb_bill = new Label("Számlázás: " + billName + ", " + billCity + ", " + billPostalCode + ", " + billAddress + ", " + billHouseNumber);
        Label lb_transport = new Label("Szállítás: " + transportName + ", " + transportCity + ", " + transportPostalCode + ", " + transportAddress + ", " + transportHouseNumber);

        lb_orderID.setFont(Font.font(20));
        lb_orderID.setLayoutX(10);
        lb_orderID.setLayoutY(10);
        lb_orderID.setText("Megrendelés szama");
        lb_orderID.setUnderline(true);

        lb_bill.setFont(Font.font(16));
        lb_bill.setLayoutX(10);
        lb_bill.setLayoutY(40);

        lb_transport.setFont(Font.font(16));
        lb_transport.setLayoutX(10);
        lb_transport.setLayoutY(60);

        ap_order.setStyle("-fx-background-color: #ffeab3; -fx-background-radius: 2em; -fx-border-radius: 2em;");
        ap_order.setPrefHeight(20);
        ap_order.setPrefWidth(1100);
        ap_order.setLayoutX(45);
        ap_order.setLayoutY(90);

        ap_order.getChildren().add(lb_orderID);
        ap_order.getChildren().add(lb_bill);
        ap_order.getChildren().add(lb_transport);

        for(int j = 0; j < 3; j++){
            ap_order.getChildren().add(addItem(j*60));
        }

        return ap_order;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        for(int i = 0; i < 1; i++){
            ap_listOfOrders.getChildren().add(createPane());
        }
    }
}
