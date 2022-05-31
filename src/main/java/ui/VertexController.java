package ui;

import javafx.scene.canvas.GraphicsContext;

public class VertexController implements Shape{
        private final Vertex vertex;

    public VertexController(Vertex vertex) {
        this.vertex = vertex;
    }

    public void draw(GraphicsContext gc, double x, double y, int width, int height, double length) {
        double rectangleX = (this.vertex.number() % width) * x + (x - length) / 2;
        double rectangleY = (this.vertex.number() / width) * y + (y - length) / 2;

        gc.fillRect(rectangleX, rectangleY, length, length);
    }
}
