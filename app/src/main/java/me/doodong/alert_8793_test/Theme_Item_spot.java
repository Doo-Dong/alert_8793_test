package me.doodong.alert_8793_test;

import android.graphics.drawable.Drawable;

public class Theme_Item_spot {
    Drawable image;
    String tv1;

    public Theme_Item_spot(Drawable image, String tv1) {
        this.image = image;
        this.tv1= tv1;
    }

    public Theme_Item_spot() {

    }


    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getTv1() {
        return tv1;
    }

    public void setTv1(String tv1) {
        this.tv1 = tv1;
    }
}
