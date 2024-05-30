package evaluacion2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CapitanBinario {
    private int pasos, cofre = 0;
    private Isla isla = new Isla();
    
    public CapitanBinario(int dificultad) {
        pasos = 0;
        crearIsla(dificultad);
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
            n = random.nextInt(d);
            if (!nums.contains(n)) {
                nums.add(n);
            }
            if (nums.size() == d / 2) {
                cofre = n;
            }
        }
        for (int numero : nums) {
            isla.insertar(numero);
        }
    }
    
    public void busqueda() {
        Nodo aux = isla.raiz;
        Scanner leer = new Scanner(System.in);
        System.out.println("COMIENZO");
        while (aux.getValor() != cofre) {
            if (aux.izq == null && aux.der == null) {
                System.out.println("Llegaste al final, empieza otra vez");
                leer.nextLine();
                aux = isla.raiz;
                //pasos = pasos * 2; ¿DESPUES DE LA SEGUNDA VUELTA?
            } else if (aux.izq == null && aux.der != null) {
                System.out.println("Camino izquierdeo bloqueado");
                leer.nextLine();
                aux = aux.der;
                pasos++;
            } else if (aux.der == null && aux.izq != null) {
                System.out.println("Camino derecho bloqueado");
                leer.nextLine();
                aux = aux.izq;
                pasos++;
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
                    System.out.println("*IZQUIERDA*");
                    break;
                }
                case "der": {
                    aux = actual.der;
                    pasos++;
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
