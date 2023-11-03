package com.basketball.lakers.players_classes;

public class State {
    String header, text, img;

    public State(String header, String text, String img){

        this.header = header;
        this.text = text;
        this.img = img;
    }

    public String getImg() {
        return this.img;
    }

    public String getHeader() {
        return this.header;
    }

    public String getText() {
        return this.text;
    }
}
