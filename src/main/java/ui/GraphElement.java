package ui;

import javafx.scene.canvas.GraphicsContext;

public interface GraphElement {
        void draw(GraphicsContext gc, double x, double y, int width, int height, double length);
}
