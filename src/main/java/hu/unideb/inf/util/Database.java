package hu.unideb.inf.util;

import com.mysql.cj.util.StringUtils;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Database db = null;
    private Connection con = null;
    private Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/megalovania", "root", "");
            if (con == null) {
                System.out.println("Error");
            }
            //Statement stat = con.createStatement();
            //String query = "SELECT * FROM users";
            //ResultSet rs = stat.executeQuery(query);
            //while (rs.next()) {
            //    System.out.println(rs.getString("username"));
            //}
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    public static Database getInstance(){
        if(db == null){
            db = new Database();
        }
        return db;
    }
    public ResultSet query(String query, ArrayList<String> data){
        PreparedStatement stat;
        try {
            stat = con.prepareStatement(query);
            for (int i = 0; i < data.size(); i++){
                if(StringUtils.isStrictlyNumeric(data.get(i))){
                    stat.setInt(i+1,Integer.parseInt((data.get(i))));
                } else {
                    stat.setString(i+1,data.get(i));
                }
            }

            return stat.executeQuery();
        }
        catch (SQLException throwables) {
            System.out.println("Database: SQLException");
            throwables.printStackTrace();
        }
        return null;
    }
    public ResultSet query(String query){
        PreparedStatement stat;
        try {
            stat = con.prepareStatement(query);
            return stat.executeQuery();
        }
        catch (SQLException throwables) {
            System.out.println("Database: SQLException");
            throwables.printStackTrace();
        }
        return null;
    }
    public int update(String query, ArrayList<String> data){
        PreparedStatement stat;
        try {
            stat = con.prepareStatement(query);
            for (int i = 0; i < data.size(); i++){
                if(StringUtils.isStrictlyNumeric(data.get(i))){
                    stat.setInt(i+1,Integer.parseInt((data.get(i))));
                } else {
                    stat.setString(i+1,data.get(i));
                }
            }

            return stat.executeUpdate();
        }
        catch (SQLException throwables) {
            System.out.println("Database: SQLException");
            throwables.printStackTrace();
        }
        return 999;
    }
}
