package ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EdgeController implements Shape{
    private final Edge edge;

    public EdgeController(Edge edge) {
        this.edge = edge;
    }

    public void draw(GraphicsContext gc, double x, double y, int width, int height, double length) {
        double startX = (edge.firstVertex() % width) * x + (x / 2);
        double startY = (edge.secondVertex() / width) * y + (y / 2);

        double endX = (edge.firstVertex() % width) * x + (x / 2);
        double endY = (edge.firstVertex() / width) * y + (y / 2);

        gc.setLineWidth(length / 4);
        gc.setStroke(Color.BLACK);
        gc.strokeLine(startX, startY, endX, endY);
    }
}