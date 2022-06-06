package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import main.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GuiController {

    private static boolean isNumeric(String str) {
        try {
            @SuppressWarnings("unused")
            int x = Integer.parseInt(str);
            return true; //String is an Integer
        } catch (NumberFormatException e) {
            return false; //String is not an Integer
        }
    }
    private static boolean checkInitialConditions(int x, int y, int n){
        if(x * y > 10000 || x <= 0 || y <= 0 || n < 1 || n > (x * y)/2){
            return true; // Warunki nie sa spelnione
        }
        return false;
    }

    private boolean isGenerated = false;
    @FXML
    private Label welcomeText;
    @FXML
    private Label total_weight;
    @FXML
    private TextField width;
    @FXML
    private TextField height;
    @FXML
    private TextField parts;
    @FXML
    private TextField gOpen;
    @FXML
    private TextField gSave;
    @FXML
    private ScrollPane graph_pane;

    @FXML
    private GraphController graphController;
    @FXML
    protected void onGenerateButtonClick(){
        String gWidth = width.getText();
        String gHeight = height.getText();
        String gParts = parts.getText();
        if(isNumeric(gWidth) && isNumeric(gHeight) && isNumeric(gParts)) {
            int Width = Integer.parseInt(gWidth);
            int Height = Integer.parseInt(gHeight);
            int Parts = Integer.parseInt(gParts);
            if(!checkInitialConditions(Width, Height, Parts)){
                welcomeText.setText("Generuję graf");
                isGenerated = true;
                graphController.initialize_graph();
                graphController.generateGraph(Height, Width, Parts);
                String weight = Double.toString(graphController.get_total_weight());
                total_weight.setText(weight);
            } else {
                welcomeText.setText("Podano bledne dane!");
            }
        } else {
            welcomeText.setText("Podano bledne dane!");
        }
    }
    @FXML
    protected void onClearButtonClick() {
        welcomeText.setText("Wyczyszczono");
        graphController.clear_graph();
    }
    @FXML
    protected void onOpenButtonClick() {
        String openPath = gOpen.getText();

        isGenerated = true;
        welcomeText.setText("Otwarto graf");
        graphController.initialize_graph();
        graphController.readFromFile(openPath);
        /*} else {
            welcomeText.setText("Wystapil blad, podaj poprawna sciezke do pliku");
        }*/
        String weight = Double.toString(graphController.get_total_weight());
        total_weight.setText(weight);
    }
    @FXML
    protected void onSaveButtonClick() {
        if(!isGenerated){
            welcomeText.setText("Najpierw wygeneruj graf");
        } else {
            String savePath = gSave.getText();
            graphController.saveToFile(savePath);
            welcomeText.setText("Zapisany");
            /*
                welcomeText.setText("Wystapil blad, podaj poprawna sciezke do pliku");
            }*/
        }
    }

}