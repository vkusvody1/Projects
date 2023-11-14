package com.example.sample.catalogClasses;




public class CatalogState {
    String head, about,  img , url;
    public CatalogState(String head, String about, String img, String url) {
        this.head = head;
        this.about = about;
        this.url = url;
        this.img = img;


    }

    public String getImg() {
        return img;
    }

    public String getAbout() {
        return about;
    }

    public String getHead() {
        return head;
    }

    public String getUrl() {
        return url;
    }
}
