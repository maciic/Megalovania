package hu.unideb.inf.util;


import hu.unideb.inf.model.ProductsModel;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {

    public static ArrayList<HashMap<String,String>> cartContent = new ArrayList<>();

    public static ArrayList<HashMap<String,String>> AddToCart(HashMap<String,String> bele){
        HashMap<String,String> tmp = new HashMap<>();
        cartContent = new ArrayList<>();
        cartContent.add(bele);
        return cartContent;

    }

    public static void RemoveFromCart(HashMap<String,String> kivesz){
        HashMap<String,String> tmp = new HashMap<>();
        if(cartContent.contains(kivesz))
        cartContent.remove(kivesz);

    }
     private int cartSize(ArrayList<HashMap<String,String>> cart){
        return cart.size();
     }
}
