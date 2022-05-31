package ui;

import javafx.scene.canvas.GraphicsContext;

public interface Shape {
        void draw(GraphicsContext gc, double x, double y, int width, int height, double length);
}
