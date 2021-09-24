module com.example.medit {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires java.sql;
    requires mail;
    requires java.desktop;
    requires javafx.swing;

    opens com.example.medit to javafx.fxml;
    exports com.example.medit;
}