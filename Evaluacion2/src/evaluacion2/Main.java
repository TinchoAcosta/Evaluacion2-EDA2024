package evaluacion2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("JUEGO");
        System.out.print("Ingrese la dificultad: ");
        int d = scanner.nextInt();
        scanner.nextLine();
        CapitanBinario cap = new CapitanBinario(d);
        System.out.println("--------------------------------------------------");
        System.out.println("Arbol Guia");
        System.out.println(cap.getIsla().raiz);
        System.out.println("Cofre: "+cap.getCofre());
        System.out.println("--------------------------------------------------");
        cap.busqueda();
        
    }

}
