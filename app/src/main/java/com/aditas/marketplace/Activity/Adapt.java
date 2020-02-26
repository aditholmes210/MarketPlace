package com.aditas.marketplace.Activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditas.marketplace.Entity.Product;
import com.aditas.marketplace.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import static com.aditas.marketplace.Activity.Detail.EXTRA_DATA;

public class Adapt extends RecyclerView.Adapter<Adapt.MainHolder>{
    private List<Product> prod;

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MainHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int pos) {
        if (prod != null) {
            holder.onBInd(prod.get(pos));
        }
    }

    @Override
    public int getItemCount(){
        return prod != null ? prod.size() : 0;
    }
    public void setProd(List<Product> prds){
        this.prod = prds;
        notifyDataSetChanged();
    }


    class MainHolder extends RecyclerView.ViewHolder{
        private Context ctx;
        private ImageView img;
        private TextView prodName, merchName, price;
        private ProgressBar progImg;
        private Product prot;

        MainHolder(@NonNull View itemView){
            super(itemView);
            img = itemView.findViewById(R.id.img_prod);
            prodName = itemView.findViewById(R.id.tv_prod_name);
            merchName = itemView.findViewById(R.id.tv_merch_name);
            progImg = itemView.findViewById(R.id.progress_image);
            price = itemView.findViewById(R.id.tv_merch_price);

            ctx = itemView.getContext();
            itemView.setOnClickListener(listen);
        }
        public void onBInd(Product product) {
            this.prot=product;
            prodName.setText(product.getName());
            merchName.setText(product.getMerch().getName());
            price.setText(String.valueOf(toMoney(product.getPrice())));

            Picasso.get()
                    .load("http://210.210.154.65:4444/storage/"+product.getImage())
                    .error(R.drawable.ic_launcher_background)
                    .fit()
                    .into(img, new Callback() {
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
        View.OnClickListener listen = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(ctx, Detail.class);
                i.putExtra(EXTRA_DATA, prot);
                ctx.startActivity(i);
            }
        };
    }

    String toMoney(long money){
        DecimalFormat dec = new DecimalFormat("#,###");
        return "Rp. " + dec.format(money);
    }
}
