package com.example.pmtjavafx.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ZayavkaNaOplatu {
    private String number;
    private LocalDate date;
    private String user;
    private String counterAgent;
    private Double price;
    private String currency;
    private Double currencyRate;
    private Double fee;

    @Override
    public String toString() {
        return "Номер: " + number + "\nДата: " + date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                "\nПользователь: " + user + "\nКонтрагент: " + counterAgent + "\nЦена: " + price.toString() + "\nВалюта: " + currency +
                "\nКурс валюты: " + currencyRate.toString() + "\nКомиссия: " + fee.toString();
    }

    public ZayavkaNaOplatu() {}

    public ZayavkaNaOplatu(String number, LocalDate date, String user, String counterAgent, Double price, String currency, Double currencyRate, Double fee) {
        this.number = number;
        this.date = date;
        this.user = user;
        this.counterAgent = counterAgent;
        this.price = price;
        this.currency = currency;
        this.currencyRate = currencyRate;
        this.fee = fee;
    }

    public boolean isSomethingNull() {
        return number == null || date == null || user == null || counterAgent ==  null || price == null || currency == null || currencyRate == null || fee == null;
    }

    public String getNumber() {
        return number;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getUser(){
        return user;
    }
}
