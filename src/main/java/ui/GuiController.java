package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

public class GuiController {
    @FXML
    private Label info_text;
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

    public void update_total_weight() {
        total_weight.setText(graphController.get_string_with_total_weight_of_found_path());
    }

    private static boolean are_parameters_valid(int x, int y, int n) {
        return (x * y <= 10000 && x > 0 && y > 0 && n >= 1 && n <= (x * y) / 2);
    }

    @FXML
    protected void onGenerateButtonClick() {
        String gWidth = width.getText();
        String gHeight = height.getText();
        String gParts = parts.getText();

        try {
            int Width = Integer.parseInt(gWidth);
            int Height = Integer.parseInt(gHeight);
            int Parts = Integer.parseInt(gParts);

            assert are_parameters_valid(Width, Height, Parts) == true;

            info_text.setText("Generuję graf");
            graphController.generate_graph(Height, Width, Parts);
            total_weight.setText(graphController.get_string_with_total_weight_of_found_path());

        } catch (NumberFormatException | AssertionError e) {
            info_text.setText("Podano bledne dane!");
        }
    }

    @FXML
    protected void onClearButtonClick() {
        info_text.setText("Wyczyszczono");
        graphController.clear_selections_from_graph();
        update_total_weight();
    }

    @FXML
    protected void onOpenButtonClick() {
        String openPath = gOpen.getText();
        graphController.reset_or_initialize_graph();

        if (graphController.read_graph_from_file(openPath) == 0) {
            graphController.draw_graph();
            info_text.setText("Otwarto graf");
            update_total_weight();
        } else {
            info_text.setText("Wystapil blad, podaj poprawna sciezke do pliku");
        }
    }

    @FXML
    protected void onSaveButtonClick() {
        if (!graphController.is_graph_generated()) {
            info_text.setText("Najpierw wygeneruj graf");
        } else {
            String savePath = gSave.getText();
            update_total_weight();
            info_text.setText("Zapisany");
        }
    }

}