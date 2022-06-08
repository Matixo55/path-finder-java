package main;

public class Utils {
    public final double NO_CONNECTION = Double.MAX_VALUE;
    public final int MAX_VERTEX_NUMBER = 10000;

    public String get_edge_index(int vertex_index, int connected_vertex_index) {
        return String.format("%d-%d", vertex_index, connected_vertex_index);
    }
}
