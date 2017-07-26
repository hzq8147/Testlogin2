package com.example.a52n.testlogin;

import android.graphics.Bitmap;

/**
 * Created by Administrartor on 2017/7/24.
 */
public class Newsclass {
    String newsname;
    String newsclass;
    String context;
    String picurl;

    Bitmap pic;

    public Bitmap getPic() {
        return pic;
    }

    public void setPic(Bitmap pic) {
        this.pic = pic;
    }

    public String getNewsname() {
        return newsname;
    }

    public void setNewsname(String newsname) {
        this.newsname = newsname;
    }

    public String getNewsclass() {
        return newsclass;
    }

    public void setNewsclass(String newsclass) {
        this.newsclass = newsclass;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }


}
