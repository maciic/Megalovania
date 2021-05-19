package hu.unideb.inf.model;

import hu.unideb.inf.util.Database;
import hu.unideb.inf.util.Session;

import java.sql.Array;
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
    public ArrayList<HashMap<String,String>> getOrderList(){
        ArrayList tmp = new ArrayList();
        ArrayList<HashMap<String,String>> result = new ArrayList<>();
        tmp.add(userId);
        //SELECT * FROM user_orders INNER JOIN ordered_products ON user_orders.id = ordered_products.fk_order INNER JOIN invoices ON invoices.fk_order = user_orders.id INNER JOIN users ON user_orders.fk_user = users.id INNER JOIN products ON products.id = ordered_products.fk_product WHERE users.username = "tesztjános";
        ResultSet rs = db.query(
                "SELECT user_orders.id, user_orders.city_name, user_orders.postcode, user_orders.address, user_orders.house_num, user_orders.door_num, " +
                "invoices.city_name, invoices.postcode, invoices.address, invoices.house_num, invoices.door_num, invoices.total, " +
                "products.price, products.name, products.description, products.in_stock_qty FROM user_orders " +
                "INNER JOIN ordered_products ON user_orders.id = ordered_products.fk_order " +
                "INNER JOIN invoices ON invoices.fk_order = user_orders.id " +
                "INNER JOIN users ON user_orders.fk_user = users.id " +
                "INNER JOIN products ON products.id = ordered_products.fk_product " +
                "WHERE users.id = ?;",tmp);
        System.out.println(rs);
        try {
            while(rs != null && rs.next()){
                HashMap<String,String> rstmp = new HashMap<>();

                rstmp.put("order_id",rs.getString(1));

                rstmp.put("order_city_name",rs.getString(2));
                rstmp.put("order_postcode",rs.getString(3));
                rstmp.put("order_address",rs.getString(4));
                rstmp.put("order_house_num",rs.getString(5));
                rstmp.put("order_door_num",rs.getString(6));

                rstmp.put("invoices_city_name",rs.getString(7));
                rstmp.put("invoices_postcode",rs.getString(8));
                rstmp.put("invoices_address",rs.getString(9));
                rstmp.put("invoices_house_num",rs.getString(10));
                rstmp.put("invoices_door_num",rs.getString(11));
                rstmp.put("invoices_total",rs.getString(12));

                rstmp.put("product_price",rs.getString(13));
                rstmp.put("product_name",rs.getString(14));
                rstmp.put("product_description",rs.getString(15));
                rstmp.put("product_in_stock_qty",rs.getString(16));

                result.add(rstmp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
