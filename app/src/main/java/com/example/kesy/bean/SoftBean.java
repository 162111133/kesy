package com.example.kesy.bean;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class SoftBean implements Serializable {
    private String title;
    private int pic;
    private String small_title;
    private String main_body;

    public SoftBean() {
        this.title = title;
        this.pic = pic;
        this.small_title = small_title;
        this.main_body = main_body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public int getPic() {
        return pic;
    }

    public void setMain_body(String main_body) {
        this.main_body = main_body;
    }

    public String getMain_body() {
        return main_body;
    }

    public void setSmall_title(String small_title) {
        this.small_title = small_title;
    }

    public String getSmall_title() {
        return small_title;
    }
//
//    @NonNull
//    @Override
//    public String toString() {
//        return title+pic+small_title+main_body;
//    }
}
