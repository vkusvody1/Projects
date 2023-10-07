package com.sport.fitnesshelper.list_classes;

public class State {
    String uri, date, name,kkal;

    public State(String uri, String date, String name, String kkal) {
        this.uri = uri;
        this.date = date;
        this.name = name;
        this.kkal = kkal;


    }

    public String getKkal() {
        return kkal;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }
}
