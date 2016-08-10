package com.cdh.mynote.list;

/**
 * Created by cc on 16-8-9.
 */
public class Stuff {
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private String info;
    private String date;

    public Stuff(String info, String date) {
        this.date = date;
        this.info = info;
    }
}
