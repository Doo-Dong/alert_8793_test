package me.doodong.alert_8793_test;

import android.graphics.drawable.Drawable;

public class Theme_Item {
    Drawable image;
    String tv1,tv2;

    public Theme_Item(Drawable image, String tv1, String tv2) {
        this.image = image;
        this.tv1 = tv1;
        this.tv2 = tv2;
    }

    public Theme_Item() {

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

    public String getTv2() {
        return tv2;
    }

    public void setTv2(String tv2) {
        this.tv2 = tv2;
    }

}
