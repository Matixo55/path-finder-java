package main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    }
    @FXML
    protected void onOpenButtonClick() {
        isGenerated = true;
        welcomeText.setText("Otwarto graf");
    }
    @FXML
    protected void onSaveButtonClick() {
        if(!isGenerated){
            welcomeText.setText("Najpierw wygeneruj graf");
        } else {
            welcomeText.setText("Zapisany");
        }
    }

}