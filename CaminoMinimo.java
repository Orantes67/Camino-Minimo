public class CaminoMinimo {
    int[][] pesos;
    int[] ultimo;
    int[] D;
    int s, n;
    boolean[] F;
    Grafo grafo;

    public CaminoMinimo(Grafo g, String origen) {
        grafo = g;
        n = g.getNumeroVertices();
        s = buscarIndiceVertice(origen);
        pesos = g.getMatrizAdyacencia();
        ultimo = new int[n];
        D = new int[n];
        F = new boolean[n];
    }

    void Dijkstra(Grafo g, String origen) {
        s = buscarIndiceVertice(origen);
        for (int i = 0; i < n; i++) {
            F[i] = false;
            D[i] = pesos[s][i];
            ultimo[i] = s;
        }
        F[s] = true;
        D[s] = 0;
        for (int i = 1; i < n; i++) {
            int v = minimo();
            F[v] = true;
            for (int w = 1; w < n; w++)
                if (!F[w])
                    if (D[v] + pesos[v][w] < D[w]) {
                        D[w] = D[v] + pesos[v][w];
                        ultimo[w] = v;
                    }
        }
    }

    public int minimo() {
        double mx = Grafo.INFINITO;
        int v = 1;
        for (int j = 0; j < n; j++)
            if (!F[j] && (mx >= D[j])) {
                mx = D[j];
                v = j;
            }
        return v;
    }

    public void recuperaCamino(String vertice) {
        int v = buscarIndiceVertice(vertice);
        recuperaCamino(v);
    }

    public void recuperaCamino(int v) {
        int anterior = ultimo[v];
        if (v != s) {
            recuperaCamino(anterior);
            System.out.println(grafo.getVertices().get(v).getDato() + " <-- ");
        } else
            System.out.println(grafo.getVertices().get(s).getDato());
    }

    public int getDistancia(String vertice) {
        int v = buscarIndiceVertice(vertice);
        return D[v];
    }

    private int buscarIndiceVertice(String dato) {
        for (int i = 0; i < grafo.getVertices().size(); i++) {
            if (grafo.getVertices().get(i).getDato().equals(dato)) {
                return i;
            }
        }
        return -1;
    }
}
