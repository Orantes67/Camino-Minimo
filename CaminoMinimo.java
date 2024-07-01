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
            if (D[i] == Grafo.INFINITO) {
                ultimo[i] = -1; // No hay camino directo
            } else {
                ultimo[i] = s;
            }
        }
        F[s] = true;
        D[s] = 0;
        for (int i = 1; i < n; i++) {
            int v = minimo();
            F[v] = true;
            for (int w = 0; w < n; w++) {
                if (!F[w] && pesos[v][w] != Grafo.INFINITO && D[v] != Grafo.INFINITO
                        && D[v] + pesos[v][w] < D[w]) {
                    D[w] = D[v] + pesos[v][w];
                    ultimo[w] = v;
                }
            }
        }
    }
    
    public int minimo() {
        int minDist = Grafo.INFINITO;
        int minIndex = -1;
        for (int i = 0; i < n; i++) {
            if (!F[i] && D[i] < minDist) {
                minDist = D[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
    

    public void recuperaCamino(String vertice) {
        int v = buscarIndiceVertice(vertice);
        if (v == -1) {
            System.out.println("Vértice no encontrado");
            return;
        }
        recuperaCamino(v);
        System.out.println();
    }
    
    public void recuperaCamino(int v) {
        if (v != s) {
            if (ultimo[v] == -1) {
                System.out.print("No hay camino desde " + grafo.getVertices().get(s).getDato() + " a " + grafo.getVertices().get(v).getDato());
                return;
            } else {
                recuperaCamino(ultimo[v]);
                System.out.print(" -->" + grafo.getVertices().get(v).getDato());
            }
        } else {
            System.out.print(grafo.getVertices().get(s).getDato());
        }
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
