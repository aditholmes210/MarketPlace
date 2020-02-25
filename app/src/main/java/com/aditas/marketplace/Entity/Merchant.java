package com.aditas.marketplace.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Merchant implements Parcelable {
    @SerializedName("merchantId")
    private int id;
    @SerializedName("merchantName")
    private String name;
    @SerializedName("merchantSlug")
    private String slug;

    public Merchant(int id, String name, String slug){
        this.id = id;
        this.name = name;
        this.slug = slug;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.slug);
    }

    protected Merchant(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.slug = in.readString();
    }

    public static final Parcelable.Creator<Merchant> CREATOR = new Parcelable.Creator<Merchant>() {
        @Override
        public Merchant createFromParcel(Parcel source) {
            return new Merchant(source);
        }

        @Override
        public Merchant[] newArray(int size) {
            return new Merchant[size];
        }
    };
}
