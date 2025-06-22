package com.example.pmtjavafx.controller;

import com.example.pmtjavafx.model.*;
import com.example.pmtjavafx.util.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DocumentLoadController {
    @FXML
    private ComboBox<String> documentChoiceBox;

    @FXML
    private Label errorLabel;

    MainPageController mainPageController;

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    public void OnLoadButtonClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открыть файл");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Текстовые файлы (*.txt)", "*.txt")
        );

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        File file = fileChooser.showOpenDialog(stage);
        assert file != null;
        if (file.length() != 0) {
            try {
                Nakladnaya nakladnaya = gson.fromJson(new FileReader(file), Nakladnaya.class);
                DocumentElement documentElement = new DocumentElement();
                if (!nakladnaya.isSomethingNull()) {
                    documentElement = new DocumentElement(gson.toJson(nakladnaya), new Document("Накладная", nakladnaya.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), nakladnaya.getNumber()));
                    mainPageController.addToList(documentElement);
                    errorLabel.setVisible(false);
                } else {
                    Platezhka platezhka = gson.fromJson(new FileReader(file), Platezhka.class);
                    if (!platezhka.isSomethingNull()) {
                        documentElement = new DocumentElement(gson.toJson(platezhka), new Document("Платежка", platezhka.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), platezhka.getNumber()));
                        mainPageController.addToList(documentElement);
                        errorLabel.setVisible(false);
                    } else {
                        ZayavkaNaOplatu zayavkaNaOplatu = gson.fromJson(new FileReader(file), ZayavkaNaOplatu.class);
                        if (!zayavkaNaOplatu.isSomethingNull()) {
                            documentElement = new DocumentElement(gson.toJson(zayavkaNaOplatu), new Document("Заявка на оплату", zayavkaNaOplatu.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), zayavkaNaOplatu.getNumber()));
                            mainPageController.addToList(documentElement);
                            errorLabel.setVisible(false);
                        }
                        else {
                            errorLabel.setVisible(true);
                            return;
                        }
                    }
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Успех");
                alert.setHeaderText(null);
                alert.setContentText("Операция успешно выполнена!");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        Stage stageDialog = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stageDialog.close();
                    }
                });
            } catch (IOException e) {
                errorLabel.setVisible(true);
                e.printStackTrace();
            }
        }
    }

    public void setMainController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }
}
