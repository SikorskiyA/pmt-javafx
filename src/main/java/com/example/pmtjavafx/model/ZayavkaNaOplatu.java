package com.example.pmtjavafx.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class ZayavkaNaOplatu {
    private StringProperty number;
    private SimpleObjectProperty<Date> date;
    private StringProperty user;
    private StringProperty counterAgent;
    private DoubleProperty price;
    private StringProperty currency;
    private DoubleProperty currencyRate;
    private DoubleProperty fee;

    @Override
    public String toString() {
        return "Заявка на оплату " + date.toString() + " номер " + number;
    }
}
