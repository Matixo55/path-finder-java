package ui;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
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
        this.gc = canvas.getGraphicsContext2D();
//        graph.read_from_file("graph.txt");
        graph.generate_graph(10, 10);
        fill_edges();
        fill_vertices();
        drawGraph();
        canvas.setOnMouseClicked(mouseEvent -> {
            int x = (int) (mouseEvent.getX() * graph.width / canvas.getWidth());
            int y = (int) (mouseEvent.getY() * graph.height / canvas.getHeight());
            int index = y * graph.width + x;
            VertexController vertex = vertices.get(index);
            if (graph.start_index == -1 && !vertex.isSelected()) {
                this.graph.start_index = index;
                vertices.get(index).select();
            } else if (graph.target_index == -1 && !vertex.isSelected()) {
                this.graph.target_index = index;
                vertices.get(index).select();
                bfs.run_bfs(graph, 0, 1);
                int[] previous_vertexes = dijkstra.find_path_with_dijkstra_algorithm(graph);
                int vertex_index = graph.target_index;
                while (vertex_index != graph.start_index) {
                    System.out.println(vertex_index);
                    int previous_vertex_index = previous_vertexes[vertex_index];
                    EdgeController edge = edges.get(new Pair<Integer, Integer>(vertex_index, previous_vertex_index));
                    if (edge == null) {
                        edge = edges.get(new Pair<Integer, Integer>(previous_vertex_index, vertex_index));
                    }
                    edge.select();
                    vertices.get(vertex_index).select();
                    vertex_index = previous_vertex_index;
            }}
        });

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
                    this.edges.put(new Pair<>(i, j), new EdgeController(i, j, graph.edges[i][j]));
                }
            }
        }
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

        double side = Math.min(x, y)*2/3;
        System.out.println(side);
        if (side < 20){
            canvas.setWidth(graph.width * 30);
            canvas.setHeight(graph.height * 30);
            drawGraph();
            return;
        }
        if (side > 100){
            canvas.setWidth(graph.width * 100);
            canvas.setHeight(graph.height * 100);
            drawGraph();
            return;
        }
        for (EdgeController edgeController : edges.values()){
            edgeController.draw(gc, x, y, graph.width, graph.height, side);
        }
        for (VertexController vertexController : vertices){
            if (vertexController != null){
                vertexController.draw(gc, x, y, graph.width, graph.height, side);
            }
        }
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

}
