package hu.unideb.inf.model;

import hu.unideb.inf.util.Database;

import java.sql.ResultSet;
import java.util.ArrayList;

public class UsersModel {
    private static Database db = Database.getInstance();
    private UsersModel(){

    }
    public static ArrayList<String> getUser(String username){
        ArrayList<String> tmp = new ArrayList();
        ArrayList<String> result = new ArrayList();
        tmp.add(username);
        ResultSet rs = db.query("SELECT * FROM users WHERE username = ?", tmp);
        try {
            if (rs.next()) {
                result.add(rs.getString(1));
                result.add(rs.getString(2));
                result.add(rs.getString(3));
            } else {
                //nincs ilyen felhasználó
                return null;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static Boolean insertUser(String username, String password){
        if(UsersModel.getUser(username) == null) {
            ArrayList tmp = new ArrayList();
            tmp.add(username);
            tmp.add(password);
            if (db.update("INSERT INTO users (username, password) VALUES (?,?)", tmp) != 1) {
                //belső sql hiba
                return false;
            }
            return true;
        }
        //már létező felhasználó
        return false;
    }
}
