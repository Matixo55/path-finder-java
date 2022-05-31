package ui;

import javafx.scene.canvas.GraphicsContext;

public class VertexController implements GraphElement {
    private final int index;

    public VertexController(int index) {
        this.index = index;
    }

    public void draw(GraphicsContext gc, double x, double y, int width, int height, double length) {
        double rectangleX = (index % width) * x + (x - length) / 2;
        double rectangleY = (index / width) * y + (y - length) / 2;

        gc.fillRect(rectangleX, rectangleY, length, length);
    }
}
