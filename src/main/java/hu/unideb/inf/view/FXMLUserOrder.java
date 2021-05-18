package hu.unideb.inf.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLUserOrder implements Initializable {

    @FXML private Tab tab_basket;
    @FXML private Tab tab_transport;
    @FXML private Tab tab_orderSummary;

    @FXML private AnchorPane ap_transport;
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


    @FXML
    private void ac_goTransportTab(){
        tab_transport.setDisable(false);
        tab_transport.getTabPane().getSelectionModel().select(1);
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
        System.out.println("Megrendelés véglegesítve");
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tab_transport.setDisable(true);
        tab_orderSummary.setDisable(true);
    }
}