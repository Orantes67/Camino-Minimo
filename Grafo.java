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
        int cantidadAristas = 0;
        boolean entradaValida = false;
        do {
            System.out.println("Indica la cantidad de aristas del grafo:");
            while (!entrada.hasNextInt()) {
                System.out.println("Ingrese un número válido.");
                entrada.next(); // descarta la entrada no válida
            }
            cantidadAristas = entrada.nextInt();
            entradaValida = true;
        } while (!entradaValida || cantidadAristas <= 0);
    
        for (int i = 0; i < cantidadAristas; i++) {
            System.out.println("Ingresa el vértice inicial de la arista:");
            String inicio = entrada.next();
            System.out.println("Ingresa el vértice final de la arista:");
            String fin = entrada.next();
    
            int peso = 0;
            entradaValida = false;
            do {
                System.out.println("Ingresa el peso de la arista (debe ser mayor que 0):");
                while (!entrada.hasNextInt()) {
                    System.out.println("Ingrese un número válido.");
                    entrada.next(); // descarta la entrada no válida
                }
                peso = entrada.nextInt();
                if (peso > 0) {
                    entradaValida = true;
                } else {
                    System.out.println("El peso debe ser mayor que 0. Intente de nuevo.");
                }
            } while (!entradaValida);
    
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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Grafo: \n");
        for (Vertice vertice : listaVertices) {
            sb.append(vertice.toString()).append("\n");
        }
        return sb.toString();
    }
}
