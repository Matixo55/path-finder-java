package ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EdgeController implements GraphElement {
    private final int first_vertex_index;
    private final int second_vertex_index;
    private final double weight;
    private GraphicsContext gc;
    private double start_x;
    private double start_y;
    private double end_x;
    private double end_y;

    public EdgeController(int first_vertex_index, int second_vertex_index, double weight) {
        this.first_vertex_index = first_vertex_index;
        this.second_vertex_index = second_vertex_index;
        this.weight = weight;
    }

    public void draw(GraphicsContext gc, double x, double y, int width, int height, double length) {
        double start_x = (first_vertex_index % width) * x + (x / 2);
        double start_y = (first_vertex_index / width) * y + (y / 2);

        double end_x = (second_vertex_index % width) * x + (x / 2);
        double end_y = (second_vertex_index / width) * y + (y / 2);

        this.gc = gc;
        this.start_x = start_x;
        this.start_y = start_y;
        this.end_x = end_x;
        this.end_y = end_y;

        gc.setLineWidth(length / 2);
        gc.setStroke(Color.rgb((int) (255*(1-this.weight)), 0, (int) (255*this.weight)));
        gc.strokeLine(start_x, start_y, end_x, end_y);
    }

    public void select() {
        this.gc.setStroke(Color.WHITE);
        gc.strokeLine(this.start_x, this.start_y, this.end_x, this.end_y);
    }
}