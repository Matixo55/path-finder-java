package main;

public class Main {
    Graph graph = new Graph();
    Dijkstra dijkstra = new Dijkstra();
    Bfs bfs = new Bfs();
    Geometric_division gd = new Geometric_division();
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

    public void generateGraph(int h, int w, int n){
        graph.width = w;
        graph.height = h;
        graph.edges = new double[h * w][h * w];
        graph.generate_graph();
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

}