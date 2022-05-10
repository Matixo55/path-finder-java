package main.java;

public class Bfs {

    private static Utils utils = new Utils();
    private int add_to_list(int[] list, int vertex, int index)
    {
        int appears = 0;
        for (int i = 0; i < index; i++)
        {
            if (list[i] == vertex)
            {
                appears = 1;
                break;
            }
        }
        if (appears == 0)
        {
            list[index] = vertex;
            return 0; // Dodalo do listy
        }
        return 1; // Nie dodalo, bo juz jest na liscie
    }


    public int run_bfs(Graph graph, int start_index, int finish_index)
    {
        int[] list = new int[graph.height * graph.width];
        for (int i = 0; i < graph.height * graph.width; i++)
        {
            list[i] = 0;
        }

        int index = 0, counter = 1, finish_appears = 0;
        list[index] = start_index;
        for (int i = 0; i < counter; i++)
        {
            index = list[i];
            for (int vertex = 0; vertex < (graph.height * graph.width); vertex++)
            {
                if (graph.edges[index][vertex] != 0 && graph.edges[index][vertex] != utils.NO_CONNECTION)
                {
                    if (add_to_list(list, vertex, counter) == 0)
                    {
                        counter++;
                    }
                }
            }
        }

        if (counter == graph.height * graph.width)
        {
            return 0; // spójny
        }

        for (int j = 0; j < counter; j++)
        {
            if (list[j] == finish_index)
            {
                finish_appears = 1;
                break;
            }
        }
        if (finish_appears == 1)
        {
            return 1; // niespójny, ale jest połączenie między wierzchołkami
        }

        return 2; // niespójny, brak połączenia
    }
}
