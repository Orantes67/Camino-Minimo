public class Arista {
    private Vertice inicio;
    private Vertice fin;
    private int peso;

    public Arista(Vertice origen, Vertice destino, int valor) {
        this.inicio = origen;
        this.fin = destino;
        this.peso = valor;
    }

    public Vertice getInicio() {
        return inicio;
    }

    public void setInicio(Vertice origen) {
        this.inicio = origen;
    }

    public Vertice getFin() {
        return fin;
    }

    public void setFin(Vertice destino) {
        this.fin = destino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int valor) {
        this.peso = valor;
    }

    @Override
    public String toString() {
        return "[" + inicio.getDato() + "-" + peso + "-" + fin.getDato() + "]";
    }
}
