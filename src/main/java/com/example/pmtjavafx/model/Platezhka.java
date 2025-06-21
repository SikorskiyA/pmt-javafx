package com.example.pmtjavafx.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class Platezhka {
    private StringProperty number;
    private SimpleObjectProperty<Date> date;
    private StringProperty user;
    private DoubleProperty price;
    private StringProperty employee;

    @Override
    public String toString() {
        return "Платёжка от " + date.toString() + " номер " + number;
    }
}
