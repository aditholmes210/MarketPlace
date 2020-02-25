package com.aditas.marketplace.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {
    @SerializedName("productId")
    private int id;
    @SerializedName("productQty")
    private int qty;
    @SerializedName("productName")
    private String name;
    @SerializedName("productSlug")
    private String slug;
    @SerializedName("productImage")
    private String image;
    @SerializedName("merchant")
    private Merchant merch;
    @SerializedName("category")
    private Category catg;

    public Product(int id, int qty, String name, String slug, String image, Merchant merch, Category catg){
        this.id = id;
        this.qty = qty;
        this.name = name;
        this.slug = slug;
        this.image = image;
        this.merch = merch;
        this.catg = catg;
    }

    public int getId() {
        return id;
    }

    public int getQty() {
        return qty;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getImage() {
        return image;
    }

    public Merchant getMerch() {
        return merch;
    }

    public Category getCatg() {
        return catg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.qty);
        dest.writeString(this.name);
        dest.writeString(this.slug);
        dest.writeString(this.image);
        dest.writeParcelable(this.merch, flags);
        dest.writeParcelable(this.catg, flags);
    }

    protected Product(Parcel in) {
        this.id = in.readInt();
        this.qty = in.readInt();
        this.name = in.readString();
        this.slug = in.readString();
        this.image = in.readString();
        this.merch = in.readParcelable(Merchant.class.getClassLoader());
        this.catg = in.readParcelable(Category.class.getClassLoader());
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
