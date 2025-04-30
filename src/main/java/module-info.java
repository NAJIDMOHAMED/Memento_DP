module ma.najid.mementodp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens ma.najid.mementodp to javafx.fxml;
    exports ma.najid.mementodp;
}