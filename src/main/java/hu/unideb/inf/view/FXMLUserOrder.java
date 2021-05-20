package hu.unideb.inf.view;

import hu.unideb.inf.model.OrdersModel;
import hu.unideb.inf.model.ProductsModel;
import hu.unideb.inf.util.Cart;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;


import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class FXMLUserOrder implements Initializable {

    @FXML private Tab tab_transport;
    @FXML private Tab tab_orderSummary;

    @FXML private AnchorPane ap_transport;
    @FXML private AnchorPane ap_contentINCart;
    @FXML private CheckBox cb_billAndTransportAddressEQ;

    @FXML private TextField txf_billName;
    @FXML private TextField txf_billCity;
    @FXML private TextField txf_billPostalCode;
    @FXML private TextField txf_billAddress;
    @FXML private TextField txf_billHouseNumber;

    @FXML private TextField txf_transportName;
    @FXML private TextField txf_transportCity;
    @FXML private TextField txf_transportPostalCode;
    @FXML private TextField txf_transportAddress;
    @FXML private TextField txf_transportHouseNumber;

    @FXML private Label lb_titleError;
    @FXML private Label lb_finalizeOrderTotalCost;
    @FXML public Label lb_vegosszeg;
    @FXML private Label lb_contentInCartErrorBar;
    @FXML private Label lb_yourOrderError;

    @FXML private Label lb_billName;
    @FXML private Label lb_billCity;
    @FXML private Label lb_billPostalCode;
    @FXML private Label lb_billAddress;
    @FXML private Label lb_billHouseNumber;

    @FXML private Label lb_transportName;
    @FXML private Label lb_transportCity;
    @FXML private Label lb_transportPostalCode;
    @FXML private Label lb_transportAddress;
    @FXML private Label lb_transportHouseNumber;
    public static int cartId=0;


    @FXML
    private void ac_goTransportTab(){

        FXMLMainApp.deleteItemsFromCart();
        if(Cart.cartSize() != 0){
            tab_transport.setDisable(false);
            tab_transport.getTabPane().getSelectionModel().select(1);
        }else{
            lb_contentInCartErrorBar.setText("A kosár üres!");
        }
        totalCostCount();

    }

    @FXML
    private void ac_goOrderSummaryTab(){

        if(txf_billName.getText() != "" && txf_billCity.getText() != "" && txf_billPostalCode.getText() != "" && txf_billAddress.getText() != "" && txf_billHouseNumber.getText() != ""){

            if(cb_billAndTransportAddressEQ.isSelected() == false){

                if(txf_transportName.getText() != "" && txf_transportCity.getText() != "" && txf_transportPostalCode.getText() != "" && txf_transportAddress.getText() != "" && txf_transportHouseNumber.getText() != ""){
                    lb_titleError.setText("Szállítás, számlázás");
                    tab_orderSummary.setDisable(false);
                    tab_orderSummary.getTabPane().getSelectionModel().select(2);
                }else{
                    lb_titleError.setText("Töltsön ki minden mezőt");
                }

            }else{
                lb_titleError.setText("Szállítás, számlázás");
                tab_orderSummary.setDisable(false);
                tab_orderSummary.getTabPane().getSelectionModel().select(2);
            }

        } else{
            lb_titleError.setText("Töltsön ki minden mezőt");
        }

        if(cb_billAndTransportAddressEQ.isSelected() == true){

            lb_billName.setText(txf_billName.getText());
            lb_billCity.setText(txf_billCity.getText());
            lb_billPostalCode.setText(txf_billPostalCode.getText());
            lb_billAddress.setText(txf_billAddress.getText());
            lb_billHouseNumber.setText(txf_billHouseNumber.getText());

            lb_transportName.setText(txf_billName.getText());
            lb_transportCity.setText(txf_billCity.getText());
            lb_transportPostalCode.setText(txf_billPostalCode.getText());
            lb_transportAddress.setText(txf_billAddress.getText());
            lb_transportHouseNumber.setText(txf_billHouseNumber.getText());

        }else{

            lb_billName.setText(txf_billName.getText());
            lb_billCity.setText(txf_billCity.getText());
            lb_billPostalCode.setText(txf_billPostalCode.getText());
            lb_billAddress.setText(txf_billAddress.getText());
            lb_billHouseNumber.setText(txf_billHouseNumber.getText());

            lb_transportName.setText(txf_transportName.getText());
            lb_transportCity.setText(txf_transportCity.getText());
            lb_transportPostalCode.setText(txf_transportPostalCode.getText());
            lb_transportAddress.setText(txf_transportAddress.getText());
            lb_transportHouseNumber.setText(txf_transportHouseNumber.getText());
        }
    }

    @FXML
    private void ac_finalizeOrder(){
        OrdersModel om = OrdersModel.getInstance();
        om.orderAddress(txf_billCity.getText(),txf_billPostalCode.getText(),txf_billAddress.getText(),txf_billHouseNumber.getText(),txf_billName.getText())
                .invoiceAddress(txf_billCity.getText(),txf_billPostalCode.getText(),txf_billAddress.getText(),txf_billHouseNumber.getText(),txf_billName.getText())
                .productList(Cart.cartContent)
                .placeOrder();

        Cart.cartContent.clear();
        lb_yourOrderError.setText("Megrendelés véglegesítve!");
    }

    @FXML
    private void ac_billAndTransportAddressEQ(){
        if(cb_billAndTransportAddressEQ.isSelected() == true){
            txf_transportName.setText("");
            txf_transportCity.setText("");
            txf_transportPostalCode.setText("");
            txf_transportAddress.setText("");
            txf_transportHouseNumber.setText("");
            ap_transport.setDisable(true);
        }else {
            ap_transport.setDisable(false);
        }
    }

    private AnchorPane createPane(int count, String name, int cost){
        AnchorPane ap_item = new AnchorPane();
        Label lb_name = new Label(name);
        Label lb_cost = new Label(String.valueOf(cost));
        Button bt_deleteItem = new Button("X");

        bt_deleteItem.setId(String.valueOf(cartId));
        int tovabb = Integer.parseInt(bt_deleteItem.getId());
        bt_deleteItem.setLayoutX(750);
        bt_deleteItem.setLayoutY(35);
        bt_deleteItem.setStyle("-fx-background-color: none; -fx-border-color: #000; -fx-background-radius: 5em; -fx-border-radius: 5em;");
        bt_deleteItem.setCursor(Cursor.HAND);
        bt_deleteItem.setAlignment(Pos.CENTER);
        bt_deleteItem.setOnAction(actionEvent -> ac_deleteItem(bt_deleteItem.getParent(),tovabb));
        //bt_deleteItem.setOnAction(actionEvent ->ac_deleteItem(cartId));
        cartId++;

        lb_name.setFont(Font.font ("System", 30));
        lb_name.setLayoutX(160);
        lb_name.setLayoutY(25);
        lb_name.setAlignment(Pos.CENTER);

        lb_cost.setFont(Font.font ("System", 30));
        lb_cost.setLayoutX(650);
        lb_cost.setLayoutY(25);
        lb_name.setAlignment(Pos.CENTER);

        ap_item.setStyle("-fx-background-color: #ffeab3; -fx-background-radius: 3em; -fx-border-radius: 3em;");
        ap_item.setPrefHeight(100);
        ap_item.setPrefWidth(800);
        ap_item.setLayoutX(40);
        ap_item.setLayoutY(count + 20);

        ap_item.getChildren().add(lb_name);
        ap_item.getChildren().add(lb_cost);
        ap_item.getChildren().add(bt_deleteItem);

        return ap_item;
    }

    @FXML
    private void ac_deleteItem(Parent ap_currentAP,int ID){
        ap_currentAP.setDisable(true);
        FXMLMainApp.indexToDelete.add(ID);
    }

    private void totalCostCount(){
        int temp=0;
        for (var elem:Cart.cartContent) {
            temp+= Integer.parseInt(elem.get("price"));
        }
        lb_vegosszeg.setText(temp + " Ft-");
        lb_finalizeOrderTotalCost.setText(lb_vegosszeg.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tab_transport.setDisable(true);
        tab_orderSummary.setDisable(true);

        for(int i = 0; i < Cart.cartContent.size(); i++){
            if(i*120+20*i > ap_contentINCart.getPrefHeight()){
                ap_contentINCart.setPrefHeight((i)*120+20*(i));
            }
            ap_contentINCart.getChildren().add(createPane(i*120, Cart.cartContent.get(i).get("name"), Integer.parseInt(Cart.cartContent.get(i).get("price"))));
        }
        totalCostCount();
    }
}