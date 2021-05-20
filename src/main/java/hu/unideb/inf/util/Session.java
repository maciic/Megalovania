package hu.unideb.inf.util;

import hu.unideb.inf.model.UsersModel;

import java.util.ArrayList;

public class Session {
    private static String userId;
    private static String userName;
    private Session() {

    }
    public static String getUserId() {
        return userId;
    }

    public static String getUserName() {
        return userName;
    }
    public static void login(String username){
        ArrayList<String> tmp = UsersModel.getUser(username);
        userId = tmp.get(0);
        userName = tmp.get(1);
    }
    public static void logout(){
        userId = null;
        userName = null;
    }
}
