package main.java;

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
                yield "Number of verticles cannot be higher than 25000";
            case GRAPH_DATA_INVALID:
                yield "Provided graph doesn't meet graph requirements";
            case VERTEX_INDEX_INVALID:
                yield "Provided vertex index is outside of accepted range";
            case COULD_NOT_SAVE_TO_FILE:
                yield "Output file couldn't be created";
            default:
                yield "Unknown error occured";
        };
        System.out.println(error_message);
        System.exit(code);
    }

    void print_help() {
        System.out.println("""
                Lista flag sterujacych programem:\n\n
                -y {int}\n
                \tLiczba wierszy grafu do wygenerowania.\n\n
                -x {int}\n
                \tLiczba kolumn grafu do wygenerowania.\n\n
                UWAGA\n
                Flagi -y i -x musza wystepowac rownoczesnie, i spelniac warunek:\n
                liczba kolumn * liczba wierszy <= 25 000\n\n
                -i {string}\n
                \tSciezka do pliku z zapisanym grafem.\n
                \tZastosowanie flagi spowoduje wczytanie grafu zamiast generowania nowego.\n\n
                UWAGA\n
                Flagi -x / -y i flaga -i nie moga wystepowac rownoczesnie\n\n
                -n {int}\n
                \tLiczba czesci, na jakie graf ma zostac podzielony.\n
                \tWartosc domyslna to 1.\n\n
                -s {int}\n
                \tIndex wierzcholka poczatkowego.\n
                \tIndex musi byc zawarty w zakresie indexow grafu.\n\n
                -t {int}\n
                \tIndex wierzcholka docelowego.\n\n
                UWAGA\n
                Index poczatkowy i docelowy sa wymagane.\n
                Indexy musza byc zawarte w zakresie wygenerowanych wierzcholkow.\n\n
                -o {string}\n
                \tSciezka w ktorej ma byc zapisany plik z grafem.\n\n
                -v\n
                \tWypisz w konsoli graficzna reprezentacje grafu.\n\n
                -h\n
                \tWyswietla to menu pomocy.\n
                """);
    }
}
