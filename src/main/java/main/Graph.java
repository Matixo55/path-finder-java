package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;


public class Graph {
    public int height;
    public int width;
    public double[][] edges;
    public int start_index;
    public int target_index;

    private static Utils utils = new Utils();

//    public Graph(int height, int width, int start_index, int target_index) {
//        this.height = height;
//        this.width = width;
//        this.edges = new double[height * width][height * width];
//        this.start_index = start_index;
//        this.target_index = target_index;
//        fill_graph_edge_weights();
//    }

    private void fill_graph_edge_weights() {
        edges = new double[height * width][height * width];

        for (int i = 0; i < height * width; i++) {
            for (int j = 0; j < height * width; j++) {
                if (i == j) {
                    edges[i][j] = 0;
                } else {
                    edges[i][j] = utils.NO_CONNECTION;
                }
            }
        }
    }

    public void generate_graph() {

        int index = 0;
        double weight;
        Random random = new Random();

        fill_graph_edge_weights();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y != 0) {
                    weight = random.nextDouble();
                    edges[index][index - width] = weight;
                    edges[index - width][index] = weight;
                }
                if (y != (height - 1)) {
                    weight = random.nextDouble();
                    edges[index][index + width] = weight;
                    edges[index + width][index] = weight;
                }
                if (x != 0) {
                    weight = random.nextDouble();
                    edges[index][index - 1] = weight;
                    edges[index - 1][index] = weight;
                }
                if (x != (width - 1)) {
                    weight = random.nextDouble();
                    edges[index][index + 1] = weight;
                    edges[index + 1][index] = weight;
                }

                index++;
            }
        }
    }

    public void save_to_file(String file_path) {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(file_path, "UTF-8");
            writer.println(height + " " + width);

            for (int i = 0; i < height * width; i++) {
                for (int j = 0; j < height * width; j++) {
                    if (j == i || edges[i][j] == utils.NO_CONNECTION) continue;

                    writer.print("\t" + j + ":\t" + edges[i][j]);
                }
                writer.print("\n");
            }
            writer.close();

        } catch (FileNotFoundException ex) {
            utils.raise_error(utils.COULD_NOT_SAVE_TO_FILE);
        } catch (Exception ex) {
            utils.raise_error(-1);
        }
    }

    private void read_graph_dimensions(String first_line) {
        try {
            assert first_line != null;

            String[] data = first_line.split(" ");
            height = Integer.parseInt(data[0]);
            width = Integer.parseInt(data[1]);

            assert height >= 1 && width >= 1;

        } catch (IndexOutOfBoundsException | AssertionError | NumberFormatException ex) {
            utils.raise_error(utils.GRAPH_DATA_INVALID);
        } catch (Exception ex) {
            utils.raise_error(-1);
        }

        int number_of_vertexes = height * width;

        if (number_of_vertexes >= utils.MAX_VERTEX_NUMBER) {
            utils.raise_error(utils.GRAPH_TOO_BIG);
        }
        if (start_index >= number_of_vertexes || target_index >= number_of_vertexes) {
            utils.raise_error(utils.VERTEX_INDEX_INVALID);
        }

        fill_graph_edge_weights();
    }

    public void read_from_file(String file_path) {
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file_path))) {
            String line = br.readLine();
            String[] data;

            read_graph_dimensions(line);

            while ((line = br.readLine()) != null) {
                data = line.split("\t");
                data = Arrays.copyOfRange(data, 1, data.length);

                if (data.length == 0) {
                    index++;
                    continue;
                }

                assert data.length % 2 == 0;

                for (int i = 0; i < data.length; i += 2) {
                    int connected_vertex_index = Integer.parseInt(data[i].substring(0, data[i].length() - 1));
                    double weight = Double.parseDouble(data[i + 1]);

                    assert connected_vertex_index >= 0 && connected_vertex_index < height * width;

                    edges[index][connected_vertex_index] = weight;
                    edges[connected_vertex_index][index] = weight;
                }
                index++;
            }

        } catch (AssertionError | ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            utils.raise_error(utils.GRAPH_DATA_INVALID);
        } catch (Exception e) {
            utils.raise_error(-1);
        }
    }
}
