package com.example.pmtjavafx.model;

public class Document {
    private String name;
    private String date;
    private String number;

    public Document(String name, String date, String number) {
        this.name = name;
        this.date = date;
        this.number = number;
    }
    public String getName() {
        return name;
    }
    public String getDate() {
        return date;
    }
    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return name + " от " + date + " номер " + number;
    }
}
