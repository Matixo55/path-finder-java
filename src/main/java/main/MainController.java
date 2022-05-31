package main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ui.GraphController;

public class MainController {
    @FXML
    private GridPane main_grid;
    @FXML
    private Pane graph_pane;
    @FXML
    private GraphController graph_controller;

    @FXML
    public void initialize() {
        System.out.println("xdxdxd");

    }
}