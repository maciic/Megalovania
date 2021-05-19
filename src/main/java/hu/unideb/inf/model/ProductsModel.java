package hu.unideb.inf.model;

import hu.unideb.inf.util.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductsModel {
    public enum OrderByName {
        None,
        Ascending,
        Descending
    }
    public enum OrderByPrice {
        None,
        Ascending,
        Descending
    }
    private static Database db = Database.getInstance();
    private ProductsModel(){

    }
    public static HashMap<String,String> getProduct(String id){
        ArrayList tmp = new ArrayList();
        HashMap<String,String> result = new HashMap<>();
        tmp.add(id);
        String query = "SELECT * FROM products WHERE id = ?";
        ResultSet rs = db.query(query, tmp);
        try {
            if(rs.next()){
                result.put("id",rs.getString("id"));
                result.put("price",rs.getString("price"));
                result.put("name",rs.getString("name"));
                result.put("description",rs.getString("description"));
                result.put("in_stock_qty",rs.getString("in_stock_qty"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    public static ArrayList<HashMap<String,String>> getProducts(OrderByName name, OrderByPrice price){
        ArrayList<HashMap<String,String>> result = new ArrayList<>();
        String query = "SELECT * FROM products";
        switch(name) {
            case Ascending:
                query += " ORDER BY name ASC";
                break;
            case Descending:
                query += " ORDER BY name DESC";
                break;
        }
        switch(price){
            case Ascending:
                if(name != OrderByName.None)
                    query += ", price ASC";
                else
                    query += " ORDER BY price ASC";
                break;
            case Descending:
                if(name != OrderByName.None)
                    query += ", price DESC";
                else
                    query += " ORDER BY price DESC";
                break;
        }
        ResultSet rs = db.query(query);
        try {
            while (rs.next()) {
                HashMap<String,String> tmp = new HashMap<>();
                tmp.put("id",rs.getString("id"));
                //System.out.println(rs.getString("id"));
                tmp.put("price",rs.getString("price"));
                //System.out.println(rs.getString("price"));
                tmp.put("name",rs.getString("name"));
                //System.out.println(rs.getString("name"));
                tmp.put("description",rs.getString("description"));
                //System.out.println(rs.getString("description"));
                tmp.put("in_stock_qty",rs.getString("in_stock_qty"));
                //System.out.println(rs.getString("in_stock_qty"));
                result.add(tmp);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
