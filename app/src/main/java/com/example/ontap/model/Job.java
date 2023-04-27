package com.example.ontap.model;

public class Job {
    private int img;
    private String gt,name,des,time;

    public Job(int img, String gt, String name, String des, String time) {
        this.img = img;
        this.gt = gt;
        this.name = name;
        this.des = des;
        this.time = time;
    }

    public Job() {

    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getGt() {
        return gt;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
