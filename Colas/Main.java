package Colas;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Programa principal que muestra el menu por consola para interacturar con la bateria virtual
 */

public class Main {
	/**
     * Punto de entrada del programa.
     */
    public static void main(String[] args) {
     
        Scanner sc = new Scanner(System.in);
        BateriaVirtual bateria = new BateriaVirtual();
        int opcion = 0;

        do {
            System.out.println("\nMenu bateria virtual");
            System.out.println(" 1. Inyectar excedente de energia");
            System.out.println(" 2. Consumir energia de la bateria");
            System.out.println(" 3. Estado de la bateria y ahorro acumulado");
            System.out.println(" 4. Salir");
            System.out.print("Elige una opcion: ");

            try {
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.print("Introduce la energia (kwh): ");
                        double kwhIn = sc.nextDouble();
                        System.out.print("Introduce el precio actual por kwh (€): ");
                        double precioIn = sc.nextDouble();
                        
                        bateria.inyectar(kwhIn, precioIn);
                        
                        System.out.println ("Excendente inyectado correctamente");
                        break;
                        
                    case 2:
                        System.out.print("Introduce la energia a consumir (kwh): ");
                        double kwhOut = sc.nextDouble();
                        System.out.print("Introduce el precio actual por kwh (€): ");
                        double precioOut = sc.nextDouble();
                        
                        double importe = bateria.consumir(kwhOut, precioOut);
                        
                        System.out.printf("Se han consumido %.2f euros de la bateria.%n", importe);
                        
                        break;
                        
                    case 3:
                        System.out.println("Estado de la bateria");
                        int contador = 1;
                        for (BloqueExcedente b : bateria.getBloques()) {
                        	System.out.println("Bloque " + contador + ": " + b.getKwh() + " kWh | " + b.getPrecioKwh() + " €/kWh | " + b.getSaldoEuros() + " €");
                        	contador++;
                        }
                        
                        System.out.println("kWh totales: " + bateria.getKwhTotal());
                        System.out.println("Saldo total disponible: " + bateria.getSaldoTotal() + " €");
                        System.out.println("Ahorro total acumulado: " + bateria.getAhorroTotal() + " €");
                        
                        break;
                        
                    case 4:
                        System.out.println("Saliendo...");
                        
                        break;
                        
                    default:
                        System.out.println("Opcion no valida, elige un numero del 1 al 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no valida, introduce un numero.");
                sc.nextLine(); // limpia el buffer
                opcion = 0;
            } catch (BateriaExcepcion e) { 
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 4);

        sc.close();
    }

}
