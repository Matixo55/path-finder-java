package ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class VertexController {
    private final Vertex vertex;
    private boolean selected = false;
    private GraphicsContext gc;

    public VertexController(GraphicsContext gc, Vertex vertex) {
        this.vertex = vertex;
        this.gc = gc;
    }

    public void draw_as_selected() {
        selected = true;

        gc.setFill(Color.WHITE);

        gc.fillRect(vertex.pos_x(), vertex.pos_y(), vertex.side_length(), vertex.side_length());
    }

    public void draw_as_deselected() {
        selected = false;

        gc.setFill(Color.DARKGRAY);
        gc.fillRect(vertex.pos_x(), vertex.pos_y(), vertex.side_length(), vertex.side_length());
    }

    public boolean is_selected() {
        return selected;
    }
}
