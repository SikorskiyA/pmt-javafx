package com.example.pmtjavafx.controller;

import com.example.pmtjavafx.model.Document;
import com.example.pmtjavafx.model.DocumentElement;
import com.example.pmtjavafx.model.Nakladnaya;
import com.example.pmtjavafx.model.Platezhka;
import com.example.pmtjavafx.util.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.UnaryOperator;

public class PlatezhkaCreateController {
    @FXML
    public TextArea number;
    @FXML
    public DatePicker date;
    @FXML
    public TextArea user;
    @FXML
    public TextArea price;
    @FXML
    public TextArea employee;
    @FXML
    public Label errorLabel;

    private MainPageController mainController;

    public void setMainController(MainPageController controller) {
        this.mainController = controller;
    }

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    public void initialize() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getControlNewText();
            if (text.matches("\\d*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> formatter = new TextFormatter<>(filter);
        price.setTextFormatter(formatter);
    }

    public void OnCreateButtonClick(ActionEvent event) {
        Platezhka platezhka;
        if (number.getText().isEmpty() || date.getValue() == null || user.getText().isEmpty() ||
                price.getText().isEmpty() || employee.getText().isEmpty()) {
            errorLabel.setVisible(true);
            return;
        } else try {
            platezhka = new Platezhka(number.getText(), date.getValue(), user.getText(),
                    Double.parseDouble(price.getText()), employee.getText());
        } catch (NumberFormatException e) {
            errorLabel.setVisible(true);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Успех");
        alert.setHeaderText(null);
        alert.setContentText("Операция успешно выполнена!");
        DocumentElement documentElement = new DocumentElement(gson.toJson(platezhka), new Document("Платежка", date.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), number.getText()));
        mainController.addToList(documentElement);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage stageDialog = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageDialog.close();
            }
        });
    }
}
