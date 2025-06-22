module com.example.pmtjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires com.google.gson;

    opens com.example.pmtjavafx to javafx.fxml;
    exports com.example.pmtjavafx;
    exports com.example.pmtjavafx.controller;
    opens com.example.pmtjavafx.controller to javafx.fxml;
    opens com.example.pmtjavafx.model to com.google.gson;
}