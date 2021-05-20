package hu.unideb.inf.util;


import hu.unideb.inf.model.ProductsModel;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {

    public static ArrayList<HashMap<String,String>> cartContent = new ArrayList<>();

    public static ArrayList<HashMap<String,String>> AddToCart(HashMap<String,String> bele){
        HashMap<String,String> tmp = new HashMap<>();
        cartContent.add(bele);
        return cartContent;

    }

    public static void RemoveFromCart(int index){

        cartContent.remove(index);


    }
     private int cartSize(ArrayList<HashMap<String,String>> cart){
        return cart.size();
     }
}
