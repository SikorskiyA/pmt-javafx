package com.example.pmtjavafx.controller;

import com.example.pmtjavafx.model.*;
import com.example.pmtjavafx.util.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDate;

public class DocumentDescriptionController {
    @FXML
    private Label descriptionLabel;

    private MainPageController mainController;
    private DocumentElement documentElement;

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    public void setMainController(MainPageController mainController) {
        this.mainController = mainController;
        this.documentElement = mainController.documentElement;

        InitData();
    }

    private void InitData() {
        String json = documentElement.getJson();
        Document document = documentElement.getDocument();
        String name = document.getName();
        switch (name){
            case "Накладная": {
                Nakladnaya nakladnaya = gson.fromJson(json, Nakladnaya.class);
                descriptionLabel.setText(nakladnaya.toString());
                break;
            }
            case "Платежка": {
                Platezhka platezhka = gson.fromJson(json, Platezhka.class);
                descriptionLabel.setText(platezhka.toString());
                break;
            }
            case "Заявка на оплату": {
                ZayavkaNaOplatu zayavkaNaOplatu =  gson.fromJson(json, ZayavkaNaOplatu.class);
                descriptionLabel.setText(zayavkaNaOplatu.toString());
                break;
            }
        }
    }
}
