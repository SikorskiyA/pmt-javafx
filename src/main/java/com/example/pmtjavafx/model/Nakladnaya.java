package com.example.pmtjavafx.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Nakladnaya {
    private String number;
    private LocalDate date;
    private String user;
    private Double price;
    private String currency;
    private Double currencyRate;
    private String product;
    private Double count;

    @Override
    public String toString() {
        return "Номер: " + number + "\nДата: " + date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                "\nПользователь: " + user + "\nЦена: " + price.toString() + "\nВалюта: " + currency +
                "\nКурс валюты: " + currencyRate.toString() + "\nТовар: " + product + "\nКоличество: " + count.toString();
    }

    public Nakladnaya() {
    }

    public Nakladnaya(String number, LocalDate date, String user, Double price, String currency, Double currencyRate, String product, Double count) {
        this.number = number;
        this.date = date;
        this.user = user;
        this.price = price;
        this.currency = currency;
        this.currencyRate = currencyRate;
        this.product = product;
        this.count = count;
    }

    public boolean isSomethingNull() {
        return number == null || date == null || user == null || price == null || currency == null || currencyRate == null || product == null || count == null;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getUser() {
        return user;
    }
}
