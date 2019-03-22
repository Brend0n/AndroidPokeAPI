package com.brendon.myapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sprites {

    @SerializedName("back_default")
    @Expose
    private String backDefault;

    @SerializedName("front_default")
    @Expose
    private String frontDefault;

    public String getBackDefault() {
        return backDefault;
    }

    public void setBackDefault(String backDefault) {
        this.backDefault = backDefault;
    }

    public String getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }

    public String toString(){
        return "Front=> "+frontDefault+" Back=> "+backDefault;
    }

}
