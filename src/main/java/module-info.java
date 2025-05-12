module com.locar.locar {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.locar.locar to javafx.fxml;
    exports com.locar.locar;
}