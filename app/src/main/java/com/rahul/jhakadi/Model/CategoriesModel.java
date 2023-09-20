package com.rahul.jhakadi.Model;

public class CategoriesModel {
    private String dataImage;
    private String name;

    public CategoriesModel(String dataImage, String name) {
        this.dataImage = dataImage;
        this.name = name;
    }

    public String getDataImage() {
        return dataImage;
    }

    public void setDataImage(String dataImage) {
        this.dataImage = dataImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
