package ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class VertexController {
    private final Vertex vertex;
    public boolean selected = false;
    private GraphicsContext gc;

    public VertexController(GraphicsContext gc, Vertex vertex) {
        this.vertex = vertex;
        this.gc = gc;
    }

    public void draw_selected() {
        selected = true;

        gc.setFill(Color.WHITE);

        gc.fillRect(vertex.x(), vertex.y(), vertex.length(), vertex.length());
    }

    public void draw_deselected() {
        selected = false;

        gc.setFill(Color.DARKGRAY);
        gc.fillRect(vertex.x(), vertex.y(), vertex.length(), vertex.length());
    }

    public boolean isSelected() {
        return selected;
    }
}
