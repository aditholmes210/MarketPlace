package com.aditas.marketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Hashtable;
import java.util.Map;

public class AddPrdt extends AppCompatActivity {
    RequestQueue queue;
    EditText etNm, etQty, etMrc, etCtg;
    Button btnSend;
    String url = "http://210.210.154.65:4444/api/products"; //method GET


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_prdt);
        init();
        queue = Volley.newRequestQueue(this);
        btnSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //product to be send
                final String productName = etNm.getText().toString();
                final String productQty = etQty.getText().toString();;
                final String catId = etCtg.getText().toString();;
                final String merchantId = etMrc.getText().toString();;
                StringRequest addProdReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Handle response
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", error.getMessage());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> param = new Hashtable<String, String>();
                        param.put("ProductName", productName);
                        param.put("ProductQty", productQty);
                        param.put("catId", catId);
                        param.put("merchantId", merchantId);
                        return param;
                    }
                };
                queue.add(addProdReq);
            }
        });
    }

    public void init(){
        etNm = findViewById(R.id.et_name);
        etQty = findViewById(R.id.et_qty);
        etMrc = findViewById(R.id.et_merch);
        etCtg = findViewById(R.id.et_cat);
        btnSend = findViewById(R.id.btn_send);
    }
}
