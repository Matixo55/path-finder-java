package main;

public class Main {
    public static void main(String args[]) {
        Graph graph = new Graph();
        graph.start_index = 0;
        graph.target_index = 1;
        graph.read_from_file("graph.txt");

        Dijkstra dijkstra = new Dijkstra();
        Bfs bfs = new Bfs();
        Geometric_division gd = new Geometric_division();
        bfs.run_bfs(graph, 0, 1);
        gd.divide_graph(graph, 2);
        dijkstra.find_path_with_dijkstra_algorithm(graph);
    }
}