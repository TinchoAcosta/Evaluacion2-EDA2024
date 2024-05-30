package evaluacion2;

public class Isla {
    Nodo raiz;

    public Isla() {
        raiz = null;
    }
    
    public void insertar(int valor){
        raiz = insertarPosta(valor,raiz);
    }
    
    private Nodo insertarPosta(int valor, Nodo raiz){
        if(raiz==null){
            raiz = new Nodo(valor);
            return raiz;
        }else{
            if(valor < raiz.valor){
                raiz.izq = insertarPosta(valor,raiz.izq);
            }else{
                raiz.der = insertarPosta(valor,raiz.der);
            }
        }
        return raiz;
    }
}
