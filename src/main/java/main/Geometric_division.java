package main;

import java.util.Random;

public class Geometric_division {

    private static Utils utils = new Utils();

    private int check_the_number_of_edges(Graph graph, int start) {
        int edge_counter = 0;
        if (start > 0) {
            if (graph.edges[start][start - 1] != utils.NO_CONNECTION) {
                edge_counter++;
            }
        }
        if (start < graph.width * graph.height - 1) {
            if (graph.edges[start][start + 1] != utils.NO_CONNECTION) {
                edge_counter++;
            }
        }
        if (start >= graph.width) {
            if (graph.edges[start - graph.width][start] != utils.NO_CONNECTION) {
                edge_counter++;
            }
        }
        if (start < (graph.height - 1) * graph.width) {
            if (graph.edges[start + graph.width][start] != utils.NO_CONNECTION) {
                edge_counter++;
            }
        }

        return edge_counter;
    }

    int is_corner(Graph graph, int vertex) // je�li wierzcho�ek jest na rogu, funkcja zwraca 1, je�li wewn�trz linii, funkcja zwraca 0
    {
        if (vertex == 0 || vertex == graph.width - 1 || vertex == graph.height * graph.width - graph.width - 1 || vertex == graph.height * graph.width - 1) {
            return 1;
        }
        if (vertex < graph.width) {
            if (graph.edges[vertex][vertex + graph.width] != utils.NO_CONNECTION) {
                return 1;
            } else {
                return 0;
            }
        } else if (vertex >= (graph.height - 1) * graph.width) {
            if (graph.edges[vertex][vertex - graph.width] != utils.NO_CONNECTION) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (graph.edges[vertex][vertex + 1] != utils.NO_CONNECTION && graph.edges[vertex][vertex - 1] != utils.NO_CONNECTION) {
                return 0;
            } else if (graph.edges[vertex][vertex + graph.width] != utils.NO_CONNECTION && graph.edges[vertex][vertex - graph.width] != utils.NO_CONNECTION) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    int which_row(Graph graph, int vertex) {
        for (int i = 1; i <= graph.height; i++) {
            if (vertex < graph.width * i) {
                return i - 1;
            }
        }
        return 10;
    }

    void divide(Graph graph, int start, int is_line) {
        if (is_line == 1) {
            if (start < graph.width) {
                if (graph.edges[start][start + 1] != utils.NO_CONNECTION) {
                    graph.edges[start][start + 1] = utils.NO_CONNECTION;
                    graph.edges[start + 1][start] = utils.NO_CONNECTION;
                } else {
                    graph.edges[start][start - 1] = utils.NO_CONNECTION;
                    graph.edges[start - 1][start] = utils.NO_CONNECTION;
                }
            } else if (start >= (graph.height - 1) * graph.width) {
                if (graph.edges[start][start + 1] != utils.NO_CONNECTION) {
                    graph.edges[start][start + 1] = utils.NO_CONNECTION;
                    graph.edges[start + 1][start] = utils.NO_CONNECTION;
                } else {
                    graph.edges[start][start - 1] = utils.NO_CONNECTION;
                    graph.edges[start - 1][start] = utils.NO_CONNECTION;
                }
            } else {
                if (graph.edges[start][start - graph.width] != utils.NO_CONNECTION) {
                    graph.edges[start][start - graph.width] = utils.NO_CONNECTION;
                    graph.edges[start - graph.width][start] = utils.NO_CONNECTION;
                } else {
                    graph.edges[start][start + 1] = utils.NO_CONNECTION;
                    graph.edges[start + 1][start] = utils.NO_CONNECTION;
                }
            }
        } else {
            if (start < graph.width) {
                if (graph.edges[start][start + 1] != utils.NO_CONNECTION) {
                    int i;
                    for (i = 0; i < graph.height - 1; i++) {
                        if (graph.edges[start + i * graph.width][start + i * graph.width + graph.width] != utils.NO_CONNECTION) {
                            graph.edges[start + i * graph.width][start + i * graph.width + 1] = utils.NO_CONNECTION;
                            graph.edges[start + i * graph.width + 1][start + i * graph.width] = utils.NO_CONNECTION;
                        } else {
                            break;
                        }
                    }
                    graph.edges[start + i * graph.width][start + i * graph.width + 1] = utils.NO_CONNECTION;
                    graph.edges[start + i * graph.width + 1][start + i * graph.width] = utils.NO_CONNECTION;
                } else {
                    int i;
                    for (i = 0; i < graph.height - 1; i++) {
                        if (graph.edges[start + i * graph.width][start + i * graph.width + graph.width] != utils.NO_CONNECTION) {
                            graph.edges[start + i * graph.width][start + i * graph.width - 1] = utils.NO_CONNECTION;
                            graph.edges[start + i * graph.width - 1][start + i * graph.width] = utils.NO_CONNECTION;
                        } else {
                            break;
                        }
                    }
                    graph.edges[start + i * graph.width][start + i * graph.width - 1] = utils.NO_CONNECTION;
                    graph.edges[start + i * graph.width - 1][start + i * graph.width] = utils.NO_CONNECTION;
                }
            } else if (start >= (graph.height - 1) * graph.width) {
                if (graph.edges[start][start + 1] != utils.NO_CONNECTION) {
                    int i;
                    for (i = 0; i < graph.height - 1; i++) {
                        if (graph.edges[start - i * graph.width][start - i * graph.width - graph.width] != utils.NO_CONNECTION) {
                            graph.edges[start - i * graph.width][start - i * graph.width + 1] = utils.NO_CONNECTION;
                            graph.edges[start - i * graph.width + 1][start - i * graph.width] = utils.NO_CONNECTION;
                        } else {
                            break;
                        }
                    }
                    graph.edges[start - i * graph.width][start - i * graph.width + 1] = utils.NO_CONNECTION;
                    graph.edges[start - i * graph.width + 1][start - i * graph.width] = utils.NO_CONNECTION;
                } else {
                    int i;
                    for (i = 0; i < graph.height - 1; i++) {
                        if (graph.edges[start - i * graph.width][start - i * graph.width + graph.width] != utils.NO_CONNECTION) {
                            graph.edges[start - i * graph.width][start - i * graph.width - 1] = utils.NO_CONNECTION;
                            graph.edges[start - i * graph.width - 1][start - i * graph.width] = utils.NO_CONNECTION;
                        } else {
                            break;
                        }
                    }
                    graph.edges[start - i * graph.width][start - i * graph.width - 1] = utils.NO_CONNECTION;
                    graph.edges[start - i * graph.width - 1][start - i * graph.width] = utils.NO_CONNECTION;
                }
            } else {
                if (graph.edges[start][start - graph.width] == utils.NO_CONNECTION) // je�li wierzcho�ek nie ma g�rnej kraw�dzi to idziemy w d�
                {
                    if (graph.edges[start][start + 1] != utils.NO_CONNECTION) {
                        int i, does_next_exist = 1;
                        for (i = 0; i < graph.height - which_row(graph, start) - 1; i++) {
                            if (graph.edges[start + i * graph.width][start + i * graph.width + graph.width] != utils.NO_CONNECTION) {
                                graph.edges[start + i * graph.width][start + i * graph.width + 1] = utils.NO_CONNECTION;
                                graph.edges[start + i * graph.width + 1][start + i * graph.width] = utils.NO_CONNECTION;
                            } else {
                                graph.edges[start + i * graph.width][start + i * graph.width + 1] = utils.NO_CONNECTION;
                                graph.edges[start + i * graph.width + 1][start + i * graph.width] = utils.NO_CONNECTION;
                                does_next_exist = 0;
                                break;
                            }
                        }
                        if (does_next_exist == 1) {
                            graph.edges[start + i * graph.width][start + i * graph.width + 1] = utils.NO_CONNECTION;
                            graph.edges[start + i * graph.width + 1][start + i * graph.width] = utils.NO_CONNECTION;
                        }
                    } else {
                        int i, does_not_exist = 1;
                        for (i = 0; i < graph.height - which_row(graph, start) - 1; i++) {
                            if (graph.edges[start + i * graph.width][start + i * graph.width + graph.width] != utils.NO_CONNECTION) {
                                graph.edges[start + i * graph.width][start + i * graph.width - 1] = utils.NO_CONNECTION;
                                graph.edges[start + i * graph.width - 1][start + i * graph.width] = utils.NO_CONNECTION;
                            } else {
                                graph.edges[start + i * graph.width][start + i * graph.width - 1] = utils.NO_CONNECTION;
                                graph.edges[start + i * graph.width - 1][start + i * graph.width] = utils.NO_CONNECTION;
                                does_not_exist = 0;
                                break;
                            }
                        }
                        if (does_not_exist == 1) {
                            graph.edges[start + i * graph.width][start + i * graph.width - 1] = utils.NO_CONNECTION;
                            graph.edges[start + i * graph.width - 1][start + i * graph.width] = utils.NO_CONNECTION;
                        }
                    }
                } else if (graph.edges[start][start + graph.width] == utils.NO_CONNECTION) // je�li wierzcho�ek nie ma dolnej kraw�dzi to idziemy w g�r�
                {
                    if (graph.edges[start][start + 1] != utils.NO_CONNECTION) {
                        int i, does_next_exist = 1;
                        for (i = 0; i < which_row(graph, start); i++) {
                            if (graph.edges[start - i * graph.width][start - i * graph.width - graph.width] != utils.NO_CONNECTION) {
                                graph.edges[start - i * graph.width][start - i * graph.width + 1] = utils.NO_CONNECTION;
                                graph.edges[start - i * graph.width + 1][start - i * graph.width] = utils.NO_CONNECTION;
                            } else {
                                graph.edges[start - i * graph.width][start - i * graph.width + 1] = utils.NO_CONNECTION;
                                graph.edges[start - i * graph.width + 1][start - i * graph.width] = utils.NO_CONNECTION;
                                does_next_exist = 0;
                                break;
                            }
                        }
                        if (does_next_exist == 1) {
                            graph.edges[start - i * graph.width][start - i * graph.width + 1] = utils.NO_CONNECTION;
                            graph.edges[start - i * graph.width + 1][start - i * graph.width] = utils.NO_CONNECTION;
                        }
                    } else {
                        int i, does_not_exist = 1;
                        for (i = 0; i < which_row(graph, start); i++) {
                            if (graph.edges[start - i * graph.width][start - i * graph.width + graph.width] != utils.NO_CONNECTION) {
                                graph.edges[start - i * graph.width][start - i * graph.width - 1] = utils.NO_CONNECTION;
                                graph.edges[start - i * graph.width - 1][start - i * graph.width] = utils.NO_CONNECTION;
                            } else {
                                graph.edges[start - i * graph.width][start - i * graph.width - 1] = utils.NO_CONNECTION;
                                graph.edges[start - i * graph.width - 1][start - i * graph.width] = utils.NO_CONNECTION;
                                does_not_exist = 0;
                                break;
                            }
                        }
                        if (does_not_exist == 1) {
                            graph.edges[start - i * graph.width][start - i * graph.width - 1] = utils.NO_CONNECTION;
                            graph.edges[start - i * graph.width - 1][start - i * graph.width] = utils.NO_CONNECTION;
                        }
                    }
                } else if (graph.edges[start][start - 1] == utils.NO_CONNECTION) // je�li wierzcho�ek nie ma lewej kraw�dzi to idziemy w prawo
                {
                    if (graph.edges[start][start - graph.width] != utils.NO_CONNECTION) {
                        int i;
                        for (i = 0; i < graph.width; i++) {
                            if (graph.edges[start + i][start + i + 1] != utils.NO_CONNECTION) {
                                graph.edges[start + i][start + i - graph.width] = utils.NO_CONNECTION;
                                graph.edges[start + i - graph.width][start + i] = utils.NO_CONNECTION;
                            } else {
                                graph.edges[start + i][start + i - graph.width] = utils.NO_CONNECTION;
                                graph.edges[start + i - graph.width][start + i] = utils.NO_CONNECTION;
                                break;
                            }
                        }
                    } else {
                        int i;
                        for (i = 0; i < graph.width; i++) {
                            if (graph.edges[start + i][start + i + 1] != utils.NO_CONNECTION) {
                                graph.edges[start + i][start + i + graph.width] = utils.NO_CONNECTION;
                                graph.edges[start + i + graph.width][start + i] = utils.NO_CONNECTION;
                            } else {
                                graph.edges[start + i][start + i + graph.width] = utils.NO_CONNECTION;
                                graph.edges[start + i + graph.width][start + i] = utils.NO_CONNECTION;
                                break;
                            }
                        }
                    }
                } else if (graph.edges[start][start + 1] == utils.NO_CONNECTION) // je�li wierzcho�ek nie ma prawej kraw�dzi to idziemy w lewo
                {
                    if (graph.edges[start][start - graph.width] != utils.NO_CONNECTION) {
                        int i;
                        for (i = 0; i < graph.width; i++) {
                            if (graph.edges[start - i][start - i - 1] != utils.NO_CONNECTION) {
                                graph.edges[start - i][start - i - graph.width] = utils.NO_CONNECTION;
                                graph.edges[start - i - graph.width][start - i] = utils.NO_CONNECTION;
                            } else {
                                graph.edges[start - i][start - i - graph.width] = utils.NO_CONNECTION;
                                graph.edges[start - i - graph.width][start - i] = utils.NO_CONNECTION;
                                break;
                            }
                        }
                    } else {
                        int i;
                        for (i = 0; i < graph.width; i++) {
                            if (graph.edges[start - i][start - i - 1] != utils.NO_CONNECTION) {
                                graph.edges[start - i][start - i + graph.width] = utils.NO_CONNECTION;
                                graph.edges[start - i + graph.width][start - i] = utils.NO_CONNECTION;
                            } else {
                                graph.edges[start - i][start - i + graph.width] = utils.NO_CONNECTION;
                                graph.edges[start - i + graph.width][start - i] = utils.NO_CONNECTION;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    void divide_graph(Graph graph, int n) {
        int start, attempts, range, is_line, max_attempts;
        int resign = 0;

        range = graph.width * graph.height;

        int i = 1;

        while (true) {
            if (graph.height * graph.width > i * 1000) {
                i++;
            } else {
                max_attempts = 500 + (i - 1) * 250;
                break;
            }
        }

        for (int j = 0; j < n - 1; j++) {
            attempts = 0;
            while (true) {
                if (attempts == max_attempts) {
                    resign = 1;
                    break;
                }
                attempts++;
                Random rand = new Random();
                start = rand.nextInt(range);
                if (graph.width == 1) {
                    if (start > 0 && start < graph.height - 1) {
                        if (graph.edges[start][start + 1] != utils.NO_CONNECTION) {
                            graph.edges[start][start + 1] = utils.NO_CONNECTION;
                            graph.edges[start + 1][start] = utils.NO_CONNECTION;
                            break;
                        } else {
                            graph.edges[start][start - 1] = utils.NO_CONNECTION;
                            graph.edges[start - 1][start] = utils.NO_CONNECTION;
                            break;
                        }
                    } else {
                        continue;
                    }
                }

                if (check_the_number_of_edges(graph, start) == 3) {
                    is_line = 0;
                    divide(graph, start, is_line);
                    break;
                } else if (check_the_number_of_edges(graph, start) == 2) {
                    if (is_corner(graph, start) == 0) {
                        is_line = 1;
                        divide(graph, start, is_line);
                        break;
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            if (resign == 1) {
                System.out.println("Niestety, nie udalo sie w pelni podzielic grafu. Program wykonal " + attempts + " prob\n");
                break;
            }
        }
    }
}
