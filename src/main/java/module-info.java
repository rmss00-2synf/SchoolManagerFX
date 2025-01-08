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
    requires kernel;
    requires layout;
    requires io;

    // Open controller package for FXML reflection
    opens com.ensat.schoolmanagerfx.controller to javafx.fxml;
    opens com.ensat.schoolmanagerfx to javafx.fxml;

    // Export main package
    exports com.ensat.schoolmanagerfx;
    opens com.ensat.schoolmanagerfx.controller.admin to javafx.fxml;
    opens com.ensat.schoolmanagerfx.controller.Secretaire to javafx.fxml;
    opens com.ensat.schoolmanagerfx.controller.prof to javafx.fxml;

}
