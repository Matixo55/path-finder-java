package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import main.Bfs;
import main.Dijkstra;
import main.Geometric_division;

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
    private Pane graph_pane;
    @FXML
    private GraphController graph_controller;

    Bfs bfs = new Bfs();
    Dijkstra dijkstra = new Dijkstra();
    Geometric_division gd = new Geometric_division();
    @FXML
    protected void onGenerateButtonClick() {
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
//                graph_controller.generateGraph(Height, Width, Parts);
            } else {
                welcomeText.setText("Podano bledne dane!");
            }
        } else {
            welcomeText.setText("Podano bledne dane!");
        }
    }
    @FXML
    protected void onSolveClick(){
        //solver.solveGraph(start, target);
        welcomeText.setText("Solved");
    }
    @FXML
    protected void onClearButtonClick() {
        welcomeText.setText("Wyczyszczono");
    }
    @FXML
    protected void onOpenButtonClick() {
        String openPath = gOpen.getText();
        /*if (solver.readFromFile(openPath)==0){
            isGenerated = true;
            welcomeText.setText("Otwarto graf");
        } else {
            welcomeText.setText("Wystapil blad, podaj poprawna sciezke do pliku");
        }*/
    }
    @FXML
    protected void onSaveButtonClick() {
        if(!isGenerated){
            welcomeText.setText("Najpierw wygeneruj graf");
        } else {
            String savePath = gSave.getText();
            /*if (solver.saveToFile(savePath)==0){
                welcomeText.setText("Zapisany");
            } else{
                welcomeText.setText("Wystapil blad, podaj poprawna sciezke do pliku");
            }*/
        }
    }

}