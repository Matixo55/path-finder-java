package ui;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import main.Bfs;
import main.Dijkstra;
import main.Geometric_division;
import main.Graph;

import java.util.Arrays;

public class GraphController {
    @FXML
    Canvas canvas;
    private Graph graph;
    private GraphicsContext gc;
    Dijkstra dijkstra = new Dijkstra();
    private Bfs bfs;
    private Geometric_division gd;

    @FXML
    private void initialize() {
//        gc = canvas.getGraphicsContext2D();
//        graph.start_index = 0;
//        graph.target_index = 1;
//        graph.read_from_file("graph.txt");
//        graph.generate_graph(50, 50);
//        drawGraph();
//
//        bfs.run_bfs(graph, 0, 1);
//
//        dijkstra.find_path_with_dijkstra_algorithm(graph);
    }
    public void generateGraph(int h, int w, int n){
        graph.width = w;
        graph.height = h;
        graph.generate_graph(w, h);
        bfs.run_bfs(graph, 0, w * h - 1);
        gd.divide_graph(graph, n);
    }

    public void readFromFile(String file_path){
        graph.read_from_file(file_path);
    }

    public void saveToFile(String file_path){
        graph.save_to_file(file_path);
    }

    public void solveGraph(int start, int target){
        dijkstra.find_path_with_dijkstra_algorithm(graph);
    }

    private void drawDrawable(Iterable <? extends Shape> objects) {
        double x = canvas.getWidth() / graph.width;
        double y = canvas.getHeight() / graph.height;

        double side = Math.min(x, y) / 2;

        for (var object : objects){
            if (object != null){
                object.draw(gc, x, y, graph.width, graph.height, side);
            }
        }
    }

    public synchronized void drawGraph() {
        drawDrawable(graph.ui_vertices);
        drawDrawable(graph.ui_edges.values());
    }
    public void setGraph(Graph graph) {
        this.graph = graph;
    }
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
