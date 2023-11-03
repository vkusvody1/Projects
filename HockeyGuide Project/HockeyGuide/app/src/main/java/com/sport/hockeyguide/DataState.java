package com.sport.hockeyguide;


public class DataState {
    private String header;
    private String text;
    private String image;

    public DataState(String header, String text,String image){

        this.header = header;

        this.text = text;
        this.image = image;

    }
    public String getHeader() {
        return this.header;
    }
    public String getText() {
        return this.text;
    }
    public String getImage() {
        return this.image;
    }
}
