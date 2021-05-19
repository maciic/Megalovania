/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.view;


import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import hu.unideb.inf.util.Cart;
import hu.unideb.inf.model.ProductsModel;


public class FXMLMainApp implements Initializable {


    @FXML private Label lb_itemsInBasket;
    @FXML private ChoiceBox cb_orderByCostBox;
    @FXML private ChoiceBox cb_orderByNameBox;
    @FXML private TextField txf_searchField;
    @FXML private Button bt_loginName;

    @FXML private AnchorPane prod_list;

    @FXML
    private void ac_addToBasket(String gombId) {
        System.out.println(gombId);
        HashMap<String,String> tmp = new HashMap<>(ProductsModel.getProduct(gombId));
        Cart.AddToCart(tmp);

        if(Cart.cartContent.size()!=0)
            lb_itemsInBasket.setText(String.valueOf(Cart.cartContent.size()));
        else
            lb_itemsInBasket.setText("0");


    }

    @FXML
    private void ac_openBasket(){
        for (var asd: ProductsModel.getProducts(ProductsModel.OrderByName.Descending,ProductsModel.OrderByPrice.None)) {
            System.out.println(asd);
        }
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
    @FXML
    private void orderRefresh(){
        ProductsModel.OrderByName temp1;

        if(cb_orderByNameBox.getValue().equals("---")){
            temp1 = ProductsModel.OrderByName.None;
        }else if(cb_orderByNameBox.getValue().equals("Növekvő")) {
            temp1 = ProductsModel.OrderByName.Ascending;
        } else
            temp1 = ProductsModel.OrderByName.Descending;

        ProductsModel.OrderByPrice temp2;

        if(cb_orderByCostBox.getValue().equals("---")){
            temp2 = ProductsModel.OrderByPrice.None;
        }else if(cb_orderByCostBox.getValue().equals("Növekvő")) {
            temp2 = ProductsModel.OrderByPrice.Ascending;
        } else
            temp2 = ProductsModel.OrderByPrice.Descending;

        int x = 0;
        int y = 0;
        int i=0;

        for (var elem : ProductsModel.getProducts(temp1,temp2))
        {
            if(i*50*i > prod_list.getPrefHeight())
            {
                prod_list.setPrefHeight((i)*50+(i));
            }

            if(x < 5)
            {
                prod_list.getChildren().add(createPane(x*220, y * 230,elem.get("name"),Integer.parseInt(elem.get("id")),Integer.parseInt(elem.get("price")),elem.get("id")));
                x++;
            }
            else
            {
                y++;
                x = 0;
                prod_list.getChildren().add(createPane(x*220, y * 230,elem.get("name"),Integer.parseInt(elem.get("id")),Integer.parseInt(elem.get("price")),elem.get("id")));
            }
            i++;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //ChoiceBox<String> cb_orderByNameBox = new ChoiceBox<>();
        cb_orderByNameBox.getItems().add("---");
        cb_orderByNameBox.getItems().add("Növekvő");
        cb_orderByNameBox.getItems().add("Csökkenő");
        cb_orderByNameBox.setValue("Növekvő");


        //ChoiceBox<String> cb_orderByCostBox = new ChoiceBox<>();
        cb_orderByCostBox.getItems().add("---");
        cb_orderByCostBox.getItems().add("Növekvő");
        cb_orderByCostBox.getItems().add("Csökkenő");
        cb_orderByCostBox.setValue("---");


        ProductsModel.OrderByName temp1;

        if(cb_orderByNameBox.getValue().equals("---")){
            temp1 = ProductsModel.OrderByName.None;
        }else if(cb_orderByNameBox.getValue().equals("Növekvő")) {
            temp1 = ProductsModel.OrderByName.Ascending;
        } else
            temp1 = ProductsModel.OrderByName.Descending;

        ProductsModel.OrderByPrice temp2;

        if(cb_orderByCostBox.getValue().equals("---")){
            temp2 = ProductsModel.OrderByPrice.None;
        }else if(cb_orderByCostBox.getValue().equals("Növekvő")) {
            temp2 = ProductsModel.OrderByPrice.Ascending;
        } else
            temp2 = ProductsModel.OrderByPrice.Descending;




        bt_loginName.setText("Felhasználó");

        int counter = 0;
        int x = 0;
        int y = 0;


        String name;
        int price = 250;
        int i=0;

        for (var elem : ProductsModel.getProducts(temp1,temp2))
        {
            if(i*50*i > prod_list.getPrefHeight())
            {
                prod_list.setPrefHeight((i)*50+(i));
            }

            if(x < 5)
            {
                prod_list.getChildren().add(createPane(x*220, y * 230,elem.get("name"),Integer.parseInt(elem.get("id")),Integer.parseInt(elem.get("price")),elem.get("id")));
                x++;
            }
            else
            {
                y++;
                x = 0;
                prod_list.getChildren().add(createPane(x*220, y * 230,elem.get("name"),Integer.parseInt(elem.get("id")),Integer.parseInt(elem.get("price")),elem.get("id")));
            }
        i++;
        }
    }

    private AnchorPane createPane(int xpadd, int ypadd, String name, int id, int cost,String buttonID){

        AnchorPane ap_item = new AnchorPane();
        Label lb_name = new Label(name);
        Button bt_buyitem = new Button("Vásárlás");
        Label lb_cost = new Label(String.valueOf(cost) + "ft");


        bt_buyitem.setId(buttonID);
        String tovabb = bt_buyitem.getId();
        bt_buyitem.setLayoutX(70);
        bt_buyitem.setLayoutY(140);
        bt_buyitem.setStyle("-fx-background-color: #E1DFD; -fx-border-color: #000; -fx-background-radius: .5em; -fx-border-radius: .5em;");
        bt_buyitem.setCursor(Cursor.HAND);
        bt_buyitem.setAlignment(Pos.CENTER);
        bt_buyitem.setOnAction(actionEvent -> ac_addToBasket(tovabb));

        lb_name.setFont(javafx.scene.text.Font.font ("System", 20));
        lb_name.setLayoutX(55);
        lb_name.setLayoutY(40);
        lb_name.setAlignment(Pos.CENTER);

        lb_cost.setFont(Font.font ("System", 20));
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
