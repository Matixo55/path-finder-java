package ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EdgeController {
    private GraphicsContext gc;
    private Edge edge;
    private Color default_color;

    public EdgeController(GraphicsContext gc, double weight, Edge edge) {
        this.gc = gc;
        this.edge = edge;
        this.default_color = Color.rgb((int) (255 * weight), 0, (int) (255 * (1 - weight)));
    }

    public void draw_as_selected() {
        gc.setLineWidth(edge.length() / 3);
        gc.setStroke(Color.WHITE);
        gc.strokeLine(edge.start_pos_x(), edge.start_pos_y(), edge.end_pos_x(), edge.end_pos_y());
    }

    public void draw_as_deselected() {
        gc.setLineWidth(edge.length() / 3);
        gc.setStroke(default_color);
        gc.strokeLine(edge.start_pos_x(), edge.start_pos_y(), edge.end_pos_x(), edge.end_pos_y());
    }
}