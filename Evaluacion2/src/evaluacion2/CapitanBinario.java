package evaluacion2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CapitanBinario {
    private int pasos, pasosPorVuelta, cofre = -1;
    private Isla isla = new Isla();
    
    public CapitanBinario(int dificultad) {
        pasos = 0;
        crearIsla2(dificultad);
    }

    public int getPasos() {
        return pasos;
    }

    public int getCofre() {
        return cofre;
    }

    public Isla getIsla() {
        return isla;
    }
    
    private void crearIsla(int d) {
        int n; 
        Random random = new Random();
        ArrayList<Integer> nums = new ArrayList<>();
        while (nums.size() != d) {
            n = random.nextInt(d)+1;
            if (!nums.contains(n)) {
                nums.add(n);
            }
            if (nums.size() == d / 2 && cofre==-1) {
                cofre = n;
            }
        }
        for (int numero : nums) {
            isla.insertar(numero);
        }
    }
    
    private void crearIsla2(int d){
        Random random = new Random();
        int n=-1;
        for (int i = 0; i < d*2; i++) {
            n = random.nextInt(d)+1;
            isla.insertar(n);           
        }
        Nodo aux = isla.raiz;
        if(n%2==0){
            aux = buscarHoja(aux,aux.der,aux.izq);            
        }else{
            aux = buscarHoja(aux,aux.izq,aux.der);
        }
        cofre = aux.valor;
    }
    
    public Nodo buscarHoja(Nodo aux, Nodo direccionDeseada, Nodo direccionInversa){
        while(aux.der!=null || aux.izq!=null){
                if(direccionDeseada!=null){
                    aux = direccionDeseada;//no cambian las direcciones
                }else{
                    aux = direccionInversa;
                }
            }
        return aux;
    }//bucle infinito
    
    public void busqueda() {
        int contVuelta=0;
        Nodo aux = isla.raiz;
        Scanner leer = new Scanner(System.in);
        System.out.println("COMIENZO");
        while (aux.getValor() != cofre) {
            if (aux.izq == null && aux.der == null) {
                System.out.println("Llegaste al final, empieza otra vez");
                contVuelta++;
                leer.nextLine();
                aux = isla.raiz;
                if(contVuelta==1){
                    pasos = pasos*2;
                }else{
                    pasos = pasos + pasosPorVuelta;
                }
                pasosPorVuelta = 0;                    
            } else if (aux.izq == null && aux.der != null) {
                System.out.println("Camino izquierdeo bloqueado");
                leer.nextLine();
                aux = aux.der;
                pasos++;
                pasosPorVuelta++;
            } else if (aux.der == null && aux.izq != null) {
                System.out.println("Camino derecho bloqueado");
                leer.nextLine();
                aux = aux.izq;
                pasos++;
                pasosPorVuelta++;
            } else if (aux.der != null && aux.izq != null) {
                if (aux.getValor() <= cofre) {
                    System.out.println("DERECHA: bien || IZQUIERDA: mal");
                    aux = bifurcacion(leer, aux);

                } else {
                    System.out.println("DERECHA: mal || IZQUIERDA: bien");
                    aux = bifurcacion(leer, aux);
                }
            }
        }
        System.out.println("ENCONTRASTE EL COFRE " + pasos);
    }
    
    private Nodo bifurcacion(Scanner leer, Nodo actual) {
        String opcion = "";
        Nodo aux = null;
        while (aux == null) {
            opcion = leer.nextLine();
            switch (opcion) {
                case "izq": {
                    aux = actual.izq;
                    pasos++;
                    pasosPorVuelta++;
                    System.out.println("*IZQUIERDA*");
                    break;
                }
                case "der": {
                    aux = actual.der;
                    pasos++;
                    pasosPorVuelta++;
                    System.out.println("*DERECHA*");
                    break;
                }
                default: {
                    System.out.println("Ingrese una opcion valida");
                }
            }
        }
        return aux;
    }
}
