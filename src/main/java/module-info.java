module main {
    requires javafx.controls;
    requires javafx.fxml;

    exports main;
    opens main to javafx.fxml;

    opens ui to javafx.fxml;
    exports ui;
}