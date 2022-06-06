package main;

public class Dijkstra {
    private static Utils utils = new Utils();

    private int get_index_of_vertex_with_minimal_distance(Graph graph, double[] distances, int[] visited_vertices) {
        int index_of_vertex_with_minimal_distance = Integer.MAX_VALUE;

        for (int i = 0; i < graph.height * graph.width; i++) {
            if (visited_vertices[i] == 0) {
                index_of_vertex_with_minimal_distance = i;
                break;
            }
        }
        for (int i = 0; i < graph.height * graph.width; i++) {
            if (visited_vertices[i] == 0 && distances[index_of_vertex_with_minimal_distance] > distances[i]) {
                index_of_vertex_with_minimal_distance = i;
            }
        }
        return index_of_vertex_with_minimal_distance;
    }

    private int[] run_dijkstra_algorithm(Graph graph, int[] visited_vertexes, double[] distances, int[] previous_vertexes) {
        int index, x, i;
        for (i = 0; i < graph.height * graph.width - 1; i++) {
            index = get_index_of_vertex_with_minimal_distance(graph, distances, visited_vertexes);
            visited_vertexes[index] = 1;

            for (x = 0; x < graph.height * graph.width; x++) {
                double new_weight = distances[index] + graph.edges[index][x];

                if (graph.edges[index][x] != 0 && new_weight < distances[x]) {
                    distances[x] = new_weight;
                    previous_vertexes[x] = index;
                }
            }
        }
        graph.total_weight = distances[graph.target_index];
        return previous_vertexes;
    }

    public int[] find_path_with_dijkstra_algorithm(Graph graph) {
        int[] visited_vertexes = new int[graph.width * graph.height];
        int[] previous_vertexes = new int[graph.width * graph.height];
        double[] distances = new double[graph.width * graph.height];

        for (int i = 0; i < graph.width * graph.height; i++) {
            visited_vertexes[i] = 0;
            distances[i] = utils.NO_CONNECTION;
            previous_vertexes[i] = 0;
        }
        distances[graph.start_index] = 0;

        return run_dijkstra_algorithm(graph, visited_vertexes, distances, previous_vertexes);
    }

}