package com.aditas.marketplace;

import com.aditas.marketplace.Entity.Product;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListProd {
    @SerializedName("data")
    private ArrayList<Product> prodz;

    public ListProd(ArrayList<Product> prodz){
        this.prodz = prodz;
    }
    public ArrayList<Product> getProdz(){
        return prodz;
    }
}
