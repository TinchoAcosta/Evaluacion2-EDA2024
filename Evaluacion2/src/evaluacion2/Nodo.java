package evaluacion2;

public class Nodo {
    int valor;
    Nodo izq;
    Nodo der;

    public Nodo(int valor) {
        this.valor = valor;
        this.izq = null;
        this.der = null;
    }

    public int getValor() {
        return valor;
    }

    public Nodo getIzq() {
        return izq;
    }

    public Nodo getDer() {
        return der;
    }

    
    
    @Override
    public String toString() {
        return "Nodo: "+ valor+"\n Izq"+valor+":"+izq+"\n Der"+valor+":"+der+"|FIN DE "+valor+"|";
    }
    
}
