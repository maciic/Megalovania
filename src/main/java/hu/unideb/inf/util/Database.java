package hu.unideb.inf.util;

import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionCreator;

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
    public ResultSet query(String query, ArrayList data){
        data.add(1);
        data.add("asd");
        data.add(new char[] {'1', '2'});
        PreparedStatement stat;
        try {
            stat = con.prepareStatement("SELECT * FROM users WHERE id=?;");
            stat.setInt(1,1);
            return stat.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
