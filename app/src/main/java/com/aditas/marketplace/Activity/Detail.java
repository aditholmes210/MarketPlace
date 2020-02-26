package com.aditas.marketplace.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aditas.marketplace.Entity.Product;
import com.aditas.marketplace.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class Detail extends AppCompatActivity {
    public static String EXTRA_DATA = "";
    private ImageView image;
    private TextView name, qty, cat, merch, price, desc;
    private ProgressBar progImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
        Product prodt = getIntent().getParcelableExtra(EXTRA_DATA);
        if (prodt != null){
            String _img = prodt.getImage();
            String _name = prodt.getName();
            String _qty = String.valueOf(prodt.getQty());
            String _cat = prodt.getCatg().getName();
            String _merch = prodt.getMerch().getName();
            String _price = String.valueOf(toMoney(prodt.getPrice()));
            String _desc = prodt.getDesc();

            setImage(_img);
            name.setText(_name);
            qty.setText(_qty);
            cat.setText(_cat);
            merch.setText(_merch);
            price.setText(_price);
            desc.setText(_desc);
        }
    }

    private void setImage(String url){
        Picasso.get()
                .load("http://210.210.154.65:4444/storage/"+url)
                .error(R.drawable.ic_launcher_background)
                .fit()
                .into(image, new Callback() {
                    @Override
                    public void onSuccess() {
                        progImg.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {
                        progImg.setVisibility(View.INVISIBLE);
                        e.printStackTrace();
                    }
                });
    }

    private void init(){
        image = findViewById(R.id.img_detail);
        name = findViewById(R.id.tv_detail_name);
        qty = findViewById(R.id.tv_detail_qty);
        cat = findViewById(R.id.tv_detail_category);
        merch = findViewById(R.id.tv_detail_merch);
        price = findViewById(R.id.tv_detail_price);
        desc = findViewById(R.id.tv_detail_desc);
        progImg = findViewById(R.id.prog_detail);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    String toMoney(long money){
        DecimalFormat dec = new DecimalFormat("#,###");
        return "Rp. " + dec.format(money);
    }
}
