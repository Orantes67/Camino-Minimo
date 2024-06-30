import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private String dato;
    private List<Arista> listaAristas;
    private int distancia = Integer.MAX_VALUE; // Distancia mínima desde el vértice inicial
    private boolean procesado = false; // Si el vértice ya ha sido procesado

    public Vertice(String dato) {
        this.dato = dato;
        this.listaAristas = new ArrayList<>();
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getDato() {
        return dato;
    }

    public List<Arista> getAristas() {
        return listaAristas;
    }

    public void setAristas(Arista arista) {
        if (listaAristas == null) {
            listaAristas = new ArrayList<>();
        }
        listaAristas.add(arista);
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public boolean isProcesado() {
        return procesado;
    }

    public void setProcesado(boolean procesado) {
        this.procesado = procesado;
    }

    @Override
    public String toString() {
        return "\n\tVertice=" + getDato() + ", Aristas=" + getAristas();
    }
}
