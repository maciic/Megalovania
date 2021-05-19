package hu.unideb.inf.util;


import hu.unideb.inf.model.ProductsModel;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {

    ArrayList<HashMap<String,String>> cartContent;

    private ArrayList<HashMap<String,String>> AddToCart(HashMap<String,String> bele){
        HashMap<String,String> tmp = new HashMap<>();
        cartContent = new ArrayList<>();
        cartContent.add(bele);
        return cartContent;

    }

    private void RemoveFromCart(HashMap<String,String> kivesz){
        HashMap<String,String> tmp = new HashMap<>();
        if(cartContent.contains(kivesz))
        cartContent.remove(kivesz);

    }
     private int cartSize(ArrayList<HashMap<String,String>> cart){
        return cart.size();
     }
}
