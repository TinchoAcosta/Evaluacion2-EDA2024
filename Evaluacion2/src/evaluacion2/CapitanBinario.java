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
            while(aux.der!=null || aux.izq!=null){
                if(aux.der!=null){
                    aux = aux.der;//no cambian las direcciones
                }else{
                    aux = aux.izq;//cambia la direccion
                }
           }
            //aux = buscarHoja(aux,aux.der,aux.izq);            
        }else{
            while(aux.der!=null || aux.izq!=null){
                if(aux.izq!=null){
                    aux = aux.izq;//no cambian las direcciones
                }else{
                    aux = aux.der;//cambia la direccion
                }
           }
            //aux = buscarHoja(aux,aux.izq,aux.der);
        }
        cofre = aux.valor;
    }
    
   // public Nodo buscarHoja(Nodo aux, Nodo direccionDeseada, Nodo direccionInversa){
        //while(aux.der!=null || aux.izq!=null){
                //if(direccionDeseada!=null){
                  //  aux = direccionDeseada;//no cambian las direcciones
                //}else{
                 //   aux = direccionInversa;
                //}
           // }
            //return aux;
    //}//bucle infinito
    
    public void busqueda() {
        int contVuelta=0;
        Nodo aux = isla.raiz;
        Scanner leer = new Scanner(System.in);
        System.out.println("¡Bienvenido, Capitán! Estás en busca de un tesoro legendario perdido en esta isla misteriosa.");
        System.out.println("¿Estás listo para comenzar tu búsqueda? Presiona Enter para empezar.");
        leer.nextLine();
        System.out.println("COMIENZO");
        while (aux.getValor() != cofre) {
            if (aux.izq == null && aux.der == null) {
                System.out.println("Llegaste al final, ¡empieza otra vez! Presiona Enter para volver a empezar.");
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
                System.out.println("El camino hacia la izquierda está bloqueado. Deberías probar el otro camino.");
                aux = aux.der;
                pasos++;
                pasosPorVuelta++;
            } else if (aux.der == null && aux.izq != null) {
                System.out.println("El camino hacia la derecha está bloqueado. Deberías probar el otro camino."); 
                aux = aux.izq;
                pasos++;
                pasosPorVuelta++;
            } else if (aux.der != null && aux.izq != null) {
                if (aux.getValor() <= cofre) {
                    System.out.println("Pista: El tesoro está más hacia la derecha.");
                    //System.out.println("DERECHA: bien || IZQUIERDA: mal");
                    aux = bifurcacion(leer, aux);

                } else {
                    System.out.println("Pista: El tesoro está más hacia la izquierda.");
                    //System.out.println("DERECHA: mal || IZQUIERDA: bien");
                    aux = bifurcacion(leer, aux);
                }

            }
        }
        System.out.println("¡Has encontrado el tesoro en solo " + pasos + " pasos! ¡Eres un verdadero capitán legendario!");
    }
    
    private Nodo bifurcacion(Scanner leer, Nodo actual) {
        System.out.println("¿Quieres ir a la izquierda (izq) o a la derecha (der)?");
        String opcion = "";
        Nodo aux = null;
        while (aux == null) {
            opcion = leer.nextLine();
            switch (opcion) {
                case "izq": {
                    aux = actual.izq;
                    pasos++;
                    pasosPorVuelta++;
                    System.out.println("Tomaste el camino a la Izquierda. ¡Sigamos avanzando en nuestra búsqueda del tesoro!");
                    break;
                }
                case "der": {
                    aux = actual.der;
                    pasos++;
                    pasosPorVuelta++;
                    System.out.println("Tomaste el camino a la Derecha. ¡Sigamos avanzando en nuestra búsqueda del tesoro!");
                    break;
                }
                default: {
                    System.out.println("Ingrese una opcion valida(izq o der)");
                }
            }
        }
        return aux;
    }
}
