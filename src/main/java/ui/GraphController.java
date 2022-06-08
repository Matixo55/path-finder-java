package ui;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import main.*;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class GraphController {
    @FXML
    public Canvas canvas;
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
        canvas.setOnMouseClicked(mouseEvent -> {
            int x = (int) (mouseEvent.getX() * graph.width / canvas.getWidth());
            int y = (int) (mouseEvent.getY() * graph.height / canvas.getHeight());

            try_selecting_vertex(x, y);
        });
    }

    private void try_selecting_vertex(int pos_x, int pos_y) {
        int index = pos_y * graph.width + pos_x;
        VertexController vertex = vertices.get(index);

        if (graph.start_index == -1 && !vertex.is_selected()) {
            graph.start_index = index;
            vertex.draw_as_selected();
        } else if (
                graph.target_index == -1 &&
                        !vertex.is_selected() &&
                        bfs.run_bfs(graph, graph.start_index, index) != 2
        ) {
            graph.target_index = index;
            vertex.draw_as_selected();
            find_path_between_selected_vertexes();
        }
    }

    private void find_path_between_selected_vertexes() {
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
            edge.draw_as_selected();
            vertices.get(vertex_index).draw_as_selected();
            vertex_index = previous_vertex_index;
        }
    }

    public void clear_selections_from_graph() {
        reset_or_initialize_graph();

        for (EdgeController edge : edges.values()) {
            edge.draw_as_deselected();
        }
        for (VertexController vertex : vertices) {
            vertex.draw_as_deselected();
        }
    }

    public void generate_graph(int height, int width, int parts) {
        reset_or_initialize_graph();
        graph.width = width;
        graph.height = height;
        graph.generate_graph(width, height);
        if (bfs.run_bfs(graph, 0, width * height - 1) != 2)
            gd.divide_graph(graph, parts);
        draw_graph();
    }

    public void reset_or_initialize_graph() {
        graph = new Graph();
        vertices = new ArrayList<VertexController>();
        edges = new HashMap<String, EdgeController>();
        gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.setWidth(600);
        canvas.setHeight(600);
    }

    public int read_graph_from_file(String file_path) {
        reset_or_initialize_graph();

        return graph.read_graph_from_file(file_path);
    }

    public int save_graph_to_file(String file_path) {
        return graph.save_graph_to_file(file_path);
    }

    public void draw_graph() {
        double x = canvas.getWidth() / graph.width;
        double y = canvas.getHeight() / graph.height;

        double length = Math.min(x, y) * 2 / 3;

        if (length < 20) {
            canvas.setWidth(graph.width * 30);
            canvas.setHeight(graph.height * 30);
            draw_graph();
            return;
        }
        if (length > 100) {
            canvas.setWidth(graph.width * 100);
            canvas.setHeight(graph.height * 100);
            draw_graph();
            return;
        }
        draw_graph_edges(x, y, length);
        draw_graph_vertices(x, y, length);
    }

    private void draw_graph_edges(double pos_x, double pos_y, double length) {
        for (int first_vertex_index = 0; first_vertex_index < graph.get_vertices_number(); first_vertex_index++) {
            for (int second_vertex_index = 0; second_vertex_index < graph.get_vertices_number(); second_vertex_index++) {
                if (
                        first_vertex_index != second_vertex_index &&
                                graph.get_edge_weight(first_vertex_index, second_vertex_index) != utils.NO_CONNECTION &&
                                edges.get(utils.get_edge_index(second_vertex_index, first_vertex_index)) == null
                ) {
                    draw_graph_edge(first_vertex_index, second_vertex_index, pos_x, pos_y, length);
                }
            }
        }
    }

    private void draw_graph_edge(int first_vertex_index, int second_vertex_index, double pos_x, double pos_y, double length) {
        String edge_index = utils.get_edge_index(first_vertex_index, second_vertex_index);

        EdgeController edgeController = new EdgeController(gc, graph.get_edge_weight(first_vertex_index, second_vertex_index), new Edge(
                edge_index,
                (first_vertex_index % graph.width) * pos_x + (pos_x / 2),
                (first_vertex_index / graph.width) * pos_y + (pos_y / 2),
                (second_vertex_index % graph.width) * pos_x + (pos_x / 2),
                (second_vertex_index / graph.width) * pos_y + (pos_y / 2),
                length
        ));

        edges.put(edge_index, edgeController);
        edgeController.draw_as_deselected();
    }

    private void draw_graph_vertices(double pos_x, double pos_y, double side_length) {
        VertexController vertexController;

        for (int index = 0; index < graph.get_vertices_number(); index++) {
            vertexController = new VertexController(gc, new Vertex(
                    index,
                    (index % graph.width) * pos_x + (pos_x - side_length) / 2,
                    (index / graph.width) * pos_y + (pos_y - side_length) / 2,
                    side_length * 4 / 5));
            vertices.add(vertexController);
            vertexController.draw_as_deselected();
        }
    }

    public String get_string_with_total_weight_of_found_path() {
        if (graph != null)
            return Double.toString(graph.total_weight);

        return "0.0";
    }

    public boolean is_graph_generated() {
        return graph != null;
    }
}