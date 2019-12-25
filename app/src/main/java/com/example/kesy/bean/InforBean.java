package com.example.kesy.bean;

import java.io.Serializable;

public class InforBean implements Serializable {
    private String title;
    private int pic;
    private String main_body;

    public InforBean() {
        this.title = title;
        this.pic = pic;
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

}
