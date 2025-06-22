package com.example.pmtjavafx.controller;

import com.example.pmtjavafx.model.Document;
import com.example.pmtjavafx.model.DocumentElement;
import com.example.pmtjavafx.model.Platezhka;
import com.example.pmtjavafx.model.ZayavkaNaOplatu;
import com.example.pmtjavafx.util.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.UnaryOperator;

public class ZayavkaNaOplatuController {
    @FXML
    public TextArea number;
    @FXML
    public DatePicker date;
    @FXML
    public TextArea user;
    @FXML
    public TextArea price;
    @FXML
    public TextArea currency;
    @FXML
    public TextArea currencyRate;
    @FXML
    public TextArea counterAgent;
    @FXML
    public TextArea fee;
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
        TextFormatter<String> formatter2 = new TextFormatter<>(filter);
        TextFormatter<String> formatter3 = new TextFormatter<>(filter);
        price.setTextFormatter(formatter);
        currencyRate.setTextFormatter(formatter2);
        fee.setTextFormatter(formatter3);
    }

    public void OnCreateButtonClick(ActionEvent event) {
        ZayavkaNaOplatu zayavkaNaOplatu;
        if (number.getText().isEmpty() || date.getValue() == null || user.getText().isEmpty() ||
                price.getText().isEmpty() || counterAgent.getText().isEmpty() || fee.getText().isEmpty() ||
                currency.getText().isEmpty() || currencyRate.getText().isEmpty()){
            errorLabel.setVisible(true);
            return;
        } else try {
            zayavkaNaOplatu = new ZayavkaNaOplatu(number.getText(), date.getValue(), user.getText(),
                    counterAgent.getText(), Double.parseDouble(price.getText()), currency.getText(),
                    Double.parseDouble(currencyRate.getText()), Double.parseDouble(fee.getText()));
        } catch (NumberFormatException e) {
            errorLabel.setVisible(true);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Успех");
        alert.setHeaderText(null);
        alert.setContentText("Операция успешно выполнена!");
        DocumentElement documentElement = new DocumentElement(gson.toJson(zayavkaNaOplatu), new Document("Заявка на оплату", date.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), number.getText()));
        mainController.addToList(documentElement);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage stageDialog = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stageDialog.close();
            }
        });
    }
}
