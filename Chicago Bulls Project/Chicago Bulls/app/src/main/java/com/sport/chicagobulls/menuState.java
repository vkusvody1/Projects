package com.sport.chicagobulls;

public class menuState {
    String header, img1, img2, text;

    public menuState(String header, String img, String img2, String text) {
        this.header = header;
        this.img1 = img;
        this.img2 = img2;
        this.text = text;
    }

    public String getHeader() {
        return header;
    }

    public String getText() {
        return text;
    }

    public String getImg1() {
        return img1;
    }

    public String getImg2() {
        return img2;
    }
}
