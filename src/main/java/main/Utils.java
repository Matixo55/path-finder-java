package main;

public class Utils {
    public final int INVALID_ARGUMENTS = 1;
    public final int COULD_NOT_LOAD_FROM_FILE = 2;
    public final int GRAPH_TOO_BIG = 3;
    public final int GRAPH_DATA_INVALID = 4;
    public final int VERTEX_INDEX_INVALID = 5;
    public final int COULD_NOT_SAVE_TO_FILE = 6;
    public final double NO_CONNECTION = Double.MAX_VALUE;
    public final int MAX_VERTEX_NUMBER = 25000;


    public void raise_error(int code) {
        String error_message = switch (code) {
            case INVALID_ARGUMENTS:
                yield "Invalid execution arguments";
            case COULD_NOT_LOAD_FROM_FILE:
                yield "Input file doesn't exist or requires missing permissions";
            case GRAPH_TOO_BIG:
                yield "Number of vertices cannot be higher than 25000";
            case GRAPH_DATA_INVALID:
                yield "Provided graph doesn't meet graph requirements";
            case VERTEX_INDEX_INVALID:
                yield "Provided vertex index is outside of accepted range";
            case COULD_NOT_SAVE_TO_FILE:
                yield "Output file couldn't be created";
            default:
                yield "Unknown error occurred";
        };
        System.out.println(error_message);
        System.exit(code);
    }

    public String get_edge_index(int vertex_index, int connected_vertex_index) {
        return String.format("%d-%d", vertex_index, connected_vertex_index);
    }
}
