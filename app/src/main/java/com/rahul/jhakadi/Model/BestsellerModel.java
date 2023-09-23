package com.rahul.jhakadi.Model;

public class BestsellerModel {
    private String dataImage;
    private String productname;
    private String productdec;
    private String rate;

    public BestsellerModel(String dataImage, String productname, String productdec, String rate) {
        this.dataImage = dataImage;
        this.productname = productname;
        this.productdec = productdec;
        this.rate = rate;
    }

    public String getDataImage() {
        return dataImage;
    }

    public void setDataImage(String dataImage) {
        this.dataImage = dataImage;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductdec() {
        return productdec;
    }

    public void setProductdec(String productdec) {
        this.productdec = productdec;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
