import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class Grafo {
    private List<Vertice> listaVertices;
    public static final int INFINITO = Integer.MAX_VALUE;

    public Grafo() {
        this.listaVertices = new ArrayList<>();
    }

    public void agregarNodo(Vertice verticeNuevo) {
        listaVertices.add(verticeNuevo);
    }

    public List<Vertice> getVertices() {
        return listaVertices;
    }

    public int getNumeroVertices() {
        return listaVertices.size();
    }

    public int[][] getMatrizAdyacencia() {
        int n = listaVertices.size();
        int[][] matriz = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(matriz[i], INFINITO);
        }

        for (int i = 0; i < n; i++) {
            Vertice vertice = listaVertices.get(i);
            for (int j = 0; j < vertice.getAristas().size(); j++) {
                Arista arista = vertice.getAristas().get(j);
                int destinoIndex = listaVertices.indexOf(arista.getFin());
                matriz[i][destinoIndex] = arista.getPeso();
            }
        }

        return matriz;
    }

    public void agregarAristas(Scanner entrada) {
        System.out.println("Indica la cantidad de aristas del grafo:");
        int cantidadAristas = entrada.nextInt();

        for (int i = 0; i < cantidadAristas; i++) {
            System.out.println("Ingresa el vértice inicial de la arista:");
            String inicio = entrada.next();
            System.out.println("Ingresa el vértice final de la arista:");
            String fin = entrada.next();
            System.out.println("Ingresa el peso de la arista:");
            int peso = entrada.nextInt();

            Vertice verticeInicio = buscarVertice(inicio);
            Vertice verticeFin = buscarVertice(fin);

            if (verticeInicio != null && verticeFin != null) {
                Arista arista = new Arista(verticeInicio, verticeFin, peso);
                verticeInicio.setAristas(arista);
            } else {
                System.out.println("Vértice no encontrado.");
            }
        }
    }

    private Vertice buscarVertice(String dato) {
        for (int i = 0; i < listaVertices.size(); i++) {
            Vertice vertice = listaVertices.get(i);
            if (vertice.getDato().equals(dato)) {
                return vertice;
            }
        }
        return null;
    }
}
