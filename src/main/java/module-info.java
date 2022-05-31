module main {
    requires javafx.controls;
    requires javafx.fxml;

    exports ui;
    opens ui to javafx.fxml;

    opens main to javafx.fxml;
    exports main;
}