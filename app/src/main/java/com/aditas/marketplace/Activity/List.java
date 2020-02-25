package com.aditas.marketplace.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.aditas.marketplace.ListProd;
import com.aditas.marketplace.Entity.Product;
import com.aditas.marketplace.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;

public class List extends AppCompatActivity {
    private RecyclerView rvMarket;
    private Adapt adpt;
    private java.util.List<Product> prds = new ArrayList<>();
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMarket = findViewById(R.id.rv_market);
        adpt = new Adapt();
        rvMarket.setAdapter(adpt);
        rvMarket.setLayoutManager(new GridLayoutManager(this, 2));
        queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://210.210.154.65:4444/api/products";

        StringRequest prodReq;
        prodReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Handle response
                Gson data = new Gson();
                ListProd lp = data.fromJson(response, ListProd.class);
                adpt.setProd(lp.getProdz());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error", error.getMessage());
            }
        });
        queue.add(prodReq);
    }
}
