package main.java;

public class Main {
    public static void main(String args[]) {
        Graph graph = new Graph();
        graph.start_index = 0;
        graph.target_index = 1;
        graph.read_from_file("graph.txt");

        Dijkstra dijkstra = new Dijkstra();
        dijkstra.find_path_with_dijkstra_algorithm(graph);
    }
}
