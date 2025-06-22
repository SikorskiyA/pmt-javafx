package com.example.pmtjavafx.model;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class DocumentElement extends AnchorPane {
    private CheckBox checkBox;
    private String fileName;
    private Document document;
    private String json;

    public DocumentElement(String json, Document document) {
        this.checkBox = new CheckBox();
        this.json = json;
        this.document = document;
        Label label = new Label(document.toString());
        this.getChildren().add(label);
        this.getChildren().add(checkBox);
        AnchorPane.setTopAnchor(label, 10.0);
        AnchorPane.setRightAnchor(checkBox, 10.0);
        AnchorPane.setTopAnchor(checkBox, 6.0);
        AnchorPane.setTopAnchor(label, 5.0);
        label.setStyle("-fx-font-size: 15px;");
    }

    public DocumentElement() {}

    public boolean IsChecked() {
        return checkBox.isSelected();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Document getDocument() {
        return document;
    }

    public String getJson() {
        return json;
    }

    @Override
    public String toString() {
        return document.toString();
    }
}
