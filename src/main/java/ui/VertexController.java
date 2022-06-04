package ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class VertexController implements GraphElement {
    private final int index;
    public boolean selected = false;
    private double x;
    private  double y;
    private  double length;
    private  GraphicsContext gc;
    public VertexController(int index) {
        this.index = index;
    }

    public void draw(GraphicsContext gc, double x_index, double y_index, int width, int height, double length) {
        double x = (index % width) * x_index + (x_index - length) / 2;
        double y = (index / width) * y_index + (y_index - length) / 2;

        this.gc = gc;
        this.x =  x;
        this.y =  y;
        this.length =  length;

        gc.setFill(Color.BLACK);
        gc.fillRect(x, y , length, length);
    }

    public void select(){
        this.selected = true;

        gc.setFill(Color.WHITE);
        gc.fillRect(this.x, this.y , this.length, this.length);
    }
    public boolean isSelected(){
        return this.selected;
    }
}
