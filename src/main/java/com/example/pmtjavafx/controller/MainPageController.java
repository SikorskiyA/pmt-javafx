package com.example.pmtjavafx.controller;

import com.example.pmtjavafx.PmtJavafxApplication;
import com.example.pmtjavafx.model.*;
import com.example.pmtjavafx.util.LocalDateAdapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;

import com.google.gson.*;

public class MainPageController {
    @FXML
    private ListView<DocumentElement> LV_Documents;
    @FXML
    private Button exitButton;

    public DocumentElement documentElement;

    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    public void OnLoadButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PmtJavafxApplication.class.getResource("document-load-view.fxml"));
            Parent root = fxmlLoader.load();

            DocumentLoadController controller = fxmlLoader.getController();
            controller.setMainController(this);

            Stage stage = new Stage();
            stage.setTitle("Загрузка файла");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addToList(DocumentElement documentElement1) {
        documentElement = documentElement1;
        LV_Documents.getItems().add(documentElement);
    }

    public void OnSaveButtonClick(ActionEvent event) {
        if (LV_Documents.getSelectionModel().getSelectedItem() == null) { return;}
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить как...");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Текстовые файлы (*.txt)", "*.txt")
        );

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        DocumentElement documentElement = LV_Documents.getSelectionModel().getSelectedItem();
        Nakladnaya nakladnaya = new Nakladnaya();
        Platezhka platezhka = new Platezhka();
        ZayavkaNaOplatu zayavkaNaOplatu = new ZayavkaNaOplatu();

        nakladnaya = gson.fromJson(documentElement.getJson(), Nakladnaya.class);
        if (nakladnaya.isSomethingNull()) {
            nakladnaya = null;
            platezhka = gson.fromJson(documentElement.getJson(), Platezhka.class);
            if (platezhka.isSomethingNull()) {
                platezhka = null;
                zayavkaNaOplatu = gson.fromJson(documentElement.getJson(), ZayavkaNaOplatu.class);
            }
        }
        try (FileWriter writer = new FileWriter(file)) {
            if (nakladnaya != null) gson.toJson(nakladnaya, writer);
            else if (platezhka != null) gson.toJson(platezhka, writer);
            else if (zayavkaNaOplatu != null) gson.toJson(zayavkaNaOplatu, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void OnNakladnayaCreateButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PmtJavafxApplication.class.getResource("nakladnaya-create-view.fxml"));
            Parent root = fxmlLoader.load();

            NakladnayaCreateController controller = fxmlLoader.getController();
            controller.setMainController(this);

            Stage stage = new Stage();
            stage.setTitle("Создание накладной");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void OnLookButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PmtJavafxApplication.class.getResource("document-description-view.fxml"));
            Parent root = fxmlLoader.load();

            try {
                documentElement = LV_Documents.getSelectionModel().getSelectedItem();
                DocumentDescriptionController controller = fxmlLoader.getController();
                controller.setMainController(this);
            } catch (NullPointerException e) {
                return;
            }

            Stage stage = new Stage();
            stage.setTitle("Просмотр документа");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void OnExitButtonClick(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void OnPlatezhkaCreateButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PmtJavafxApplication.class.getResource("platezhka-create-view.fxml"));
            Parent root = fxmlLoader.load();

            PlatezhkaCreateController controller = fxmlLoader.getController();
            controller.setMainController(this);

            Stage stage = new Stage();
            stage.setTitle("Создание платежки");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void OnZayavkaCreateButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PmtJavafxApplication.class.getResource("zayavka-na-oplatu-create-view.fxml"));
            Parent root = fxmlLoader.load();

            ZayavkaNaOplatuController controller = fxmlLoader.getController();
            controller.setMainController(this);

            Stage stage = new Stage();
            stage.setTitle("Создание заявки на оплату");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void OnDeleteButtonClick(ActionEvent event) {
        LV_Documents.getItems().removeIf(DocumentElement::IsChecked);
    }
}