module com.ensat.schoolmanagerfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires static lombok;
    requires java.sql;
    requires java.validation;

    opens com.ensat.schoolmanagerfx to javafx.fxml;
    exports com.ensat.schoolmanagerfx;
    exports com.ensat.schoolmanagerfx.controller;
    opens com.ensat.schoolmanagerfx.controller to javafx.fxml;
}