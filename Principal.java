import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner leer = new Scanner(System.in);
        int opc = 0;
        Grafo grafo = null;

        do {
            System.out.println("INGRESE LO QUE DESEE HACER \n1:crear vertice\n2:ejecutar Dijkstra\n3:salir");

            boolean entradaValida = false;
            while (!entradaValida) {
                try {
                    opc = leer.nextInt();
                    entradaValida = true;
                } catch (Exception e) {
                    System.out.println("Por favor, ingrese un número válido.");
                    leer.next();
                }
            }
            switch (opc) {
                case 1:
                    grafo = verificarVertices(leer);
                    System.out.println(grafo);
                    break;
                case 2:
                    if (grafo != null) {
                        System.out.println("Ingrese el vértice inicial para ejecutar Dijkstra:");
                        String verticeInicialDijkstra = leer.next();
                        CaminoMinimo caminoMinimo = new CaminoMinimo(grafo, verticeInicialDijkstra);
                        caminoMinimo.Dijkstra(grafo, verticeInicialDijkstra);
                        for (int i = 0; i < grafo.getVertices().size(); i++) {
                            Vertice v = grafo.getVertices().get(i);
                            System.out.println("Camino más corto a " + v.getDato() + ":");
                            caminoMinimo.recuperaCamino(v.getDato());
                            System.out.println("Distancia: " + caminoMinimo.getDistancia(v.getDato()));
                        }
                    } else {
                        System.out.println("Primero debe crear el grafo.");
                    }
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opc != 3);
        leer.close();
    }

    public static Vertice crearVertice(Scanner entrada) {
        String dato;
        do {
            System.out.println("Ingresa el valor del vértice (letra):");
            dato = entrada.next();
        } while (!validarVertice(dato));
        return new Vertice(dato);
    }

    public static Grafo verificarVertices(Scanner entrada) {
        int cantidad = 0;
        do {
            System.out.println("Indica la cantidad de vértices del grafo (debe ser mayor a 0):");
            while (!entrada.hasNextInt()) {
                System.out.println("Ingrese un número");
                entrada.next();
            }
            cantidad = entrada.nextInt();
        } while (cantidad <= 0);

        Grafo grafo = new Grafo();
        int verticesAgregados = 0;

        while (verticesAgregados < cantidad) {
            Vertice verticeNuevo = crearVertice(entrada);
            boolean duplicado = false;

            for (int i = 0; i < grafo.getVertices().size(); i++) {
                Vertice v = grafo.getVertices().get(i);
                if (v.getDato().equals(verticeNuevo.getDato())) {
                    duplicado = true;
                }
            }

            if (!duplicado) {
                grafo.agregarNodo(verticeNuevo);
                verticesAgregados++;
            } else {
                System.out.println("El valor ingresado ya está presente en un vértice existente.");
            }
        }

        grafo.agregarAristas(entrada);
        return grafo;
    }

    public static boolean validarVertice(String dato) {
        return dato.matches("[a-zA-Z]");
    }
}
