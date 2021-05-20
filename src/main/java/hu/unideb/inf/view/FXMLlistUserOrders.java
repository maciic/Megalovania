package hu.unideb.inf.view;

import hu.unideb.inf.model.OrdersModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import javax.swing.text.LabelView;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class FXMLlistUserOrders implements Initializable {

    @FXML private AnchorPane ap_listOfOrders;
    ArrayList<HashMap<String,String>> data = OrdersModel.getInstance().getOrderList();
    double latestHeight = 20;
    int n = 0;

    private AnchorPane addItem(int count, HashMap<String,String> map){

        AnchorPane ap_item = new AnchorPane();
        Label lb_itemName = new Label(map.get("product_name"));
        Label lb_itemPrice = new Label(map.get("product_price"));

        lb_itemName.setFont(Font.font(16));
        lb_itemName.setLayoutX(50);
        lb_itemName.setLayoutY(15);

        lb_itemPrice.setFont(Font.font(16));
        lb_itemPrice.setLayoutX(400);
        lb_itemPrice.setLayoutY(15);

        ap_item.setStyle("-fx-background-color: #none; -fx-border-color: #000; -fx-background-radius: 2em; -fx-border-radius: 2em;");
        ap_item.setPrefHeight(50);
        ap_item.setPrefWidth(500);
        ap_item.setLayoutX(500);
        ap_item.setLayoutY(count + 10);

        ap_item.getChildren().add(lb_itemName);
        ap_item.getChildren().add(lb_itemPrice);

        latestHeight += ap_item.getPrefHeight();

        return ap_item;
    }

    private AnchorPane createPane(HashMap<String,String> map, int orderID, int sorszam){

        int totalCost = 0;
        AnchorPane ap_order = new AnchorPane();

        Label lb_orderID = new Label();
        Label lb_bill = new Label("Számlázás: " + map.get("order_name") + ", " + map.get("order_city_name") + ", " + map.get("order_postcode") + ", " + map.get("order_address") + ", " + map.get("order_house_door_num"));
        Label lb_transport = new Label("Szállítás: " + map.get("invoices_name") + ", " + map.get("invoices_city_name") + ", " + map.get("invoices_postcode") + ", " + map.get("invoices_address") + ", " + map.get("invoices_house_door_num"));
        Label lb_totalCost = new Label();

        lb_orderID.setFont(Font.font(20));
        lb_orderID.setLayoutX(10);
        lb_orderID.setLayoutY(10);
        lb_orderID.setText((sorszam+1) + " rendelés");
        lb_orderID.setUnderline(true);

        lb_bill.setFont(Font.font(16));
        lb_bill.setLayoutX(10);
        lb_bill.setLayoutY(50);

        lb_transport.setFont(Font.font(16));
        lb_transport.setLayoutX(10);
        lb_transport.setLayoutY(70);

        lb_totalCost.setFont(Font.font(20));
        lb_totalCost.setLayoutX(130);
        lb_totalCost.setLayoutY(10);

        ap_order.setStyle("-fx-background-color: #ffeab3; -fx-background-radius: 2em; -fx-border-radius: 2em;");
        ap_order.setPrefHeight(70);
        ap_order.setPrefWidth(1100);
        ap_order.setLayoutX(45);
        ap_order.setLayoutY(latestHeight);

        for(int j = 0; j < data.size(); j++){
            if(Integer.parseInt(data.get(j).get("order_id")) == orderID){
                ap_order.getChildren().add(addItem(n*60, data.get(j)));
                totalCost += Integer.parseInt(data.get(j).get("product_price"));
                n++;
            }
        }

        lb_totalCost.setText("Összesen fizetendő: " + totalCost + " Ft-");

        ap_order.getChildren().add(lb_orderID);
        ap_order.getChildren().add(lb_bill);
        ap_order.getChildren().add(lb_transport);
        ap_order.getChildren().add(lb_totalCost);

        latestHeight += ap_order.getPrefHeight();

        return ap_order;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

        ArrayList<Integer> ordersInMap = new ArrayList<>();

        for(int j = 0; j < data.size(); j++){
            if(!ordersInMap.contains(Integer.parseInt(data.get(j).get("order_id")))){
                ordersInMap.add(Integer.parseInt(data.get(j).get("order_id")));
            }
        }

        for(int i = 0; i < ordersInMap.size(); i++){
            for(int j = 0; j < data.size(); j++){
                try {
                    if(Integer.parseInt(data.get(j).get("order_id")) == ordersInMap.get(i)){
                        n = 0;
                        ap_listOfOrders.getChildren().add(createPane(data.get(j), ordersInMap.get(i), i));
                        ap_listOfOrders.setPrefHeight(latestHeight);
                        i++;
                    }
                } catch (Exception e){
                    break;
                }

            }
        }
        ap_listOfOrders.setPrefHeight(ap_listOfOrders.getPrefHeight());
    }
}
