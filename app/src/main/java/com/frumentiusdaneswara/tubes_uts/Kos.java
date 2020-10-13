package com.frumentiusdaneswara.tubes_uts;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.Serializable;

public class Kos implements Serializable {
    public String namakos;
    public String alamatkos;
    public String hargakos;
    public String nohpkos;
    public String imageID;
    public double longitude;
    public double latitude;

    public Kos(String namakos, String alamatkos, String hargakos, String nohpkos, String imageID, double latitude, double longitude) {
        this.namakos = namakos;
        this.alamatkos = alamatkos;
        this.hargakos = hargakos;
        this.nohpkos = nohpkos;
        this.imageID = imageID;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @BindingAdapter("setImage")
    public static void setImage(ImageView view, String imageimg) {
        Glide.with(view.getContext())
                .load(imageimg).apply(new RequestOptions().centerCrop())
                .into(view);
    }


    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getNamakos() {
        return namakos;
    }

    public void setNamakos(String namakos) {
        this.namakos = namakos;
    }

    public String getAlamatkos() {
        return alamatkos;
    }

    public void setAlamatkos(String alamatkos) {
        this.alamatkos = alamatkos;
    }

    public String getHargakos() {
        return hargakos;
    }

    public void setHargakos(String hargakos) {
        this.hargakos = hargakos;
    }

    public String getNohpkos() {
        return nohpkos;
    }

    public void setNohpkos(String nohpkos) {
        this.nohpkos = nohpkos;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

}