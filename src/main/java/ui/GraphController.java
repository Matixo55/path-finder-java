package ui;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.util.Pair;
import main.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GraphController {
    @FXML
    Canvas canvas;
    private GraphicsContext gc;
    private Bfs bfs = new Bfs();
    private Graph graph = new Graph();
    private Dijkstra dijkstra = new Dijkstra();
    private Geometric_division gd = new Geometric_division();
    private Utils utils = new Utils();
    private ArrayList<VertexController> vertices = new ArrayList<VertexController>();
    private HashMap<Pair<Integer, Integer>, EdgeController> edges = new HashMap<Pair<Integer, Integer>, EdgeController>();

    @FXML
    private void initialize() {
        gc = canvas.getGraphicsContext2D();
        graph.start_index = 0;
        graph.target_index = 1;
//        graph.read_from_file("graph.txt");
        graph.generate_graph(50, 50);
        fill_edges();
        fill_vertices();
        drawGraph();

        bfs.run_bfs(graph, 0, 1);

        dijkstra.find_path_with_dijkstra_algorithm(graph);
    }
    public void generateGraph(int h, int w, int n){
        graph.width = w;
        graph.height = h;
        graph.generate_graph(w, h);
        bfs.run_bfs(graph, 0, w * h - 1);
        gd.divide_graph(graph, n);
    }

    public void fill_vertices(){
        for (int i = 0; i < graph.get_vertices_number(); i++) {
            this.vertices.add(new VertexController(i));
        }
    }

    public void fill_edges(){
        for (int i = 0; i < graph.get_vertices_number(); i++) {
            for (int j = 0; j < graph.get_vertices_number(); j++) {
                if (i != j && graph.get_edge_weight(i, j) != utils.NO_CONNECTION && edges.get(new Pair<Integer, Integer>(j, i)) == null) {
                    edges.put(new Pair<>(i, j), new EdgeController(i, j, graph.edges[i][j]));
                }
            }
        }
        System.out.println(edges.size());
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

    private void drawGraph() {
        double x = canvas.getWidth() / graph.width;
        double y = canvas.getHeight() / graph.height;

        double side = Math.min(x, y) / 2;

        for (VertexController vertexController : vertices){
            if (vertexController != null){
                vertexController.draw(gc, x, y, graph.width, graph.height, side);
            }
        }
        for (EdgeController edgeController : edges.values()){
            edgeController.draw(gc, x, y, graph.width, graph.height, side);
        }
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

}
