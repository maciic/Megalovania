package hu.unideb.inf.model;

import hu.unideb.inf.util.Database;
import hu.unideb.inf.util.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class OrdersModel {
    private static Database db = Database.getInstance();
    private static OrdersModel om = null;
    private static String userId;

    private static String order_city_name;
    private static String order_postcode;
    private static String order_address;
    private static String order_house_num;
    private static String order_door_num;

    private static String invoice_city_name;
    private static String invoice_postcode;
    private static String invoice_address;
    private static String invoice_house_num;
    private static String invoice_door_num;

    private static ArrayList<HashMap<String,String>> product_list;
    private OrdersModel() {
        userId = Session.getUserId();
    }
    public static OrdersModel getInstance(){
        if(om == null){
            om = new OrdersModel();
        }
        return om;
    }
    public OrdersModel orderAddress(String city_name, String postcode, String address, String house_num, String door_num){
        order_city_name = city_name;
        order_postcode = postcode;
        order_address = address;
        order_house_num = house_num;
        order_door_num = door_num;
        return this;
    }
    public OrdersModel invoiceAddress(String city_name, String postcode, String address, String house_num, String door_num){
        invoice_city_name = city_name;
        invoice_postcode = postcode;
        invoice_address = address;
        invoice_house_num = house_num;
        invoice_door_num = door_num;
        return this;
    }
    public OrdersModel productList(ArrayList<HashMap<String,String>> productList){
        product_list = productList;
        return this;
    }
    public boolean placeOrder(){
        //order placement by userId
        System.out.println(userId = Session.getUserId());
        ArrayList tmp = new ArrayList();
        ArrayList result = new ArrayList();
        String orderId = "";
        int total_price = 0;
        tmp.add(userId);
        tmp.add(order_city_name);
        tmp.add(order_postcode);
        tmp.add(order_address);
        tmp.add(order_house_num);
        tmp.add(order_door_num);
        if (db.update("INSERT INTO user_orders (fk_user, city_name, postcode, address, house_num, door_num) VALUES (?,?,?,?,?,?);", tmp) != 1) {
            //belső sql hiba
            return false;
        }
        //order placement by userId
        //get orderId
        ArrayList tmp2 = new ArrayList();
        tmp2.add(userId);
        ResultSet rs = db.query("SELECT id FROM user_orders WHERE fk_user = ? ORDER BY id DESC LIMIT 1;",tmp2);
        try {
            if( rs != null && rs.next()){
                orderId = rs.getString("id");
                System.out.println(orderId);
            } else {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //get orderId
        //insert ordered products, calculate sum


        for (int i = 0; i < product_list.size(); i++) {
            ArrayList tmp3 = new ArrayList();
            tmp3.add("1");
            tmp3.add(product_list.get(i).get("id"));
            tmp3.add(orderId);
            db.update("INSERT INTO ordered_products (db, fk_product, fk_order) VALUES (?,?,?);", tmp3);
            total_price += Integer.parseInt(product_list.get(i).get("price"));
        }
        //insert ordered products, calculate sum
        //invoice with address and sum
        ArrayList tmp4 = new ArrayList();
        tmp4.add(orderId);
        tmp4.add(invoice_city_name);
        tmp4.add(invoice_postcode);
        tmp4.add(invoice_address);
        tmp4.add(invoice_house_num);
        tmp4.add(invoice_door_num);
        tmp4.add(Integer.toString(total_price));
        if (db.update("INSERT INTO invoices (fk_order, city_name, postcode, address, house_num, door_num, total) VALUES (?,?,?,?,?,?,?);", tmp4) != 1) {
            //belső sql hiba
            return false;
        }



        return true;
    }
}
