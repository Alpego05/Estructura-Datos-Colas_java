package Colas;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Programa principal: menú de la batería virtual fotovoltaica.
 */
public class Main {

    /**
     * Punto de entrada del programa.
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // decimales con punto, tanto al leer como al mostrar
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
                        System.out.print("Introduce la energia (kWh): ");
                        double kwhIn = sc.nextDouble();
                        System.out.print("Introduce el precio actual por kWh (€): ");
                        double precioIn = sc.nextDouble();
                        bateria.inyectar(kwhIn, precioIn);
                        System.out.printf("Inyectados %.2f kWh a %.4f €/kWh (%.2f €).%n",
                                kwhIn, precioIn, kwhIn * precioIn);
                        break;
                    case 2:
                        System.out.print("Introduce la energia a consumir (kWh): ");
                        double kwhOut = sc.nextDouble();
                        System.out.print("Introduce el precio actual por kWh (€): ");
                        double precioOut = sc.nextDouble();
                        double importe = bateria.consumir(kwhOut, precioOut);
                        System.out.printf("Consumidos %.2f € de la bateria.%n", importe);
                        break;
                    case 3:
                        System.out.println("Estado de la bateria");
                        int i = 1;
                        for (BloqueExcedente b : bateria.getBloques()) {
                            System.out.printf("  Bloque %d: %.2f kWh | %.4f €/kWh | %.2f €%n",
                                    i++, b.getKwh(), b.getPrecioKwh(), b.getSaldoEuros());
                        }
                        if (i == 1) System.out.println("  (sin bloques)");
                        System.out.printf("kWh total: %.2f kWh%n", bateria.getKwhTotal());
                        System.out.printf("Saldo disponible: %.2f €%n", bateria.getSaldoTotal());
                        System.out.printf("Ahorro total acumulado: %.2f €%n", bateria.getAhorroTotal());
                        break;
                    case 4:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opcion no valida, elige un numero del 1 al 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no valida: introduce un numero.");
                sc.nextLine(); // limpia el buffer
                opcion = 0;
            } catch (BateriaExcepcion e) { // incluye SaldoExcepcion
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 4);

        sc.close();
    }
}