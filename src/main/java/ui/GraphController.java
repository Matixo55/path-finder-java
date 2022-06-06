package ui;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;
import main.*;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class GraphController {
    @FXML
    Canvas canvas;
    private GraphicsContext gc;
    private Bfs bfs = new Bfs();
    private Graph graph;
    private Dijkstra dijkstra = new Dijkstra();
    private Geometric_division gd = new Geometric_division();
    private Utils utils = new Utils();
    private ArrayList<VertexController> vertices;
    private HashMap<String, EdgeController> edges;

    @FXML
    private void initialize() {
        initialize_graph();
        generateGraph(120, 120, 1);

        canvas.setOnMouseClicked(mouseEvent -> {
            int x = (int) (mouseEvent.getX() * graph.width / canvas.getWidth());
            int y = (int) (mouseEvent.getY() * graph.height / canvas.getHeight());

            try_selecting_vertex(x, y);
        });
    }

    private void try_selecting_vertex(int x, int y) {
        int index = y * graph.width + x;
        VertexController vertex = vertices.get(index);

        if (graph.start_index == -1 && !vertex.isSelected()) {
            graph.start_index = index;
            vertex.draw_selected();
        } else if (
                graph.target_index == -1 &&
                        !vertex.isSelected() &&
                        bfs.run_bfs(graph, graph.start_index, index) != 2
        ) {
            graph.target_index = index;
            vertex.draw_selected();
            solveGraph();
        } else {
            clear_graph();
        }
    }

    public void solveGraph() {
        if (graph.start_index == -1 || graph.target_index == -1) {
            return;
        }
        int[] previous_vertexes = dijkstra.find_path_with_dijkstra_algorithm(graph);
        int vertex_index = graph.target_index;
        int previous_vertex_index;

        while (vertex_index != graph.start_index) {
            previous_vertex_index = previous_vertexes[vertex_index];
            EdgeController edge = edges.get(utils.get_edge_index(vertex_index, previous_vertex_index));
            if (edge == null) {
                edge = edges.get(utils.get_edge_index(previous_vertex_index, vertex_index));
            }
            edge.draw_selected();
            vertices.get(vertex_index).draw_selected();
            vertex_index = previous_vertex_index;
        }
    }

    public void clear_graph() {
        graph.start_index = -1;
        graph.target_index = -1;
        graph.total_weight = 0;
        for (EdgeController edge : edges.values()) {
            edge.draw_deselected();
        }
        for (VertexController vertex : vertices) {
            vertex.draw_deselected();
        }
    }

    public void generateGraph(int h, int w, int n) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        graph.width = w;
        graph.height = h;
        graph.generate_graph(w, h);
        //        graph.read_from_file("graph.txt");
        if (bfs.run_bfs(graph, 0, w * h - 1) != 2)
            gd.divide_graph(graph, n);
        drawGraph();
    }

    public void initialize_graph() {
        graph = new Graph();
        vertices = new ArrayList<VertexController>();
        edges = new HashMap<String, EdgeController>();
        gc = canvas.getGraphicsContext2D();
    }

    public void readFromFile(String file_path) {
        graph.read_from_file(file_path);
    }

    public void saveToFile(String file_path) {
        graph.save_to_file(file_path);
    }

    private void drawGraph() {
        double x = canvas.getWidth() / graph.width;
        double y = canvas.getHeight() / graph.height;

        double length = Math.min(x, y) * 2 / 3;

        if (length < 20) {
            canvas.setWidth(graph.width * 30);
            canvas.setHeight(graph.height * 30);
            drawGraph();
            return;
        }
        if (length > 100) {
            canvas.setWidth(graph.width * 100);
            canvas.setHeight(graph.height * 100);
            drawGraph();
            return;
        }
        drawEdges(x, y, length);
        drawVertices(x, y, length);
    }

    private void drawEdges(double x, double y, double length) {
        for (int first_vertex_index = 0; first_vertex_index < graph.get_vertices_number(); first_vertex_index++) {
            for (int second_vertex_index = 0; second_vertex_index < graph.get_vertices_number(); second_vertex_index++) {
                if (
                        first_vertex_index != second_vertex_index &&
                                graph.get_edge_weight(first_vertex_index, second_vertex_index) != utils.NO_CONNECTION &&
                                edges.get(utils.get_edge_index(second_vertex_index, first_vertex_index)) == null
                ) {
                    drawEdge(first_vertex_index, second_vertex_index, x, y, length);
                }
            }
        }
    }

    private void drawEdge(int first_vertex_index, int second_vertex_index, double x, double y, double length) {
        String edge_index = utils.get_edge_index(first_vertex_index, second_vertex_index);

        EdgeController edgeController = new EdgeController(gc, graph.get_edge_weight(first_vertex_index, second_vertex_index), new Edge(
                edge_index,
                (first_vertex_index % graph.width) * x + (x / 2),
                (first_vertex_index / graph.width) * y + (y / 2),
                (second_vertex_index % graph.width) * x + (x / 2),
                (second_vertex_index / graph.width) * y + (y / 2),
                length
        ));

        edges.put(edge_index, edgeController);
        edgeController.draw_deselected();
    }

    private void drawVertices(double x, double y, double length) {
        VertexController vertexController;

        for (int index = 0; index < graph.get_vertices_number(); index++) {
            vertexController = new VertexController(gc, new Vertex(
                    index,
                    (index % graph.width) * x + (x - length) / 2,
                    (index / graph.width) * y + (y - length) / 2,
                    length * 4 / 5));
            vertices.add(vertexController);
            vertexController.draw_deselected();
        }
    }

    public double get_total_weight() {
        return graph.total_weight;
    }
}