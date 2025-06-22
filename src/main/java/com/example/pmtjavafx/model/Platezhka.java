package com.example.pmtjavafx.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Platezhka {
    private String number;
    private LocalDate date;
    private String user;
    private Double price;
    private String employee;

    @Override
    public String toString() {
        return "Номер: " + number + "\nДата: " + date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                "\nПользователь" + user + "\nЦена: " + price + "\nСотрудник: " + employee;
    }

    public Platezhka() {
    }

    public Platezhka(String number, LocalDate date, String user, Double price, String employee) {
        this.number = number;
        this.date = date;
        this.user = user;
        this.price = price;
        this.employee = employee;
    }

    public boolean isSomethingNull() {
        return number == null || date == null || user == null || price == null || employee == null;
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
