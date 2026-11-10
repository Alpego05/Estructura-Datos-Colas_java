package Colas;
import java.util.Scanner;

public class Main {
	public static void main (String [] args) {
		Scanner sc = new Scanner(System.in);
		BateriaVirtual bateria = new BateriaVirtual();
		int opcion = 0;
		
		do {
			System.out.println ("Menu bateria virtual");
			System.out.println (" 1. Inyectar excedente de energia");
			System.out.println (" 2. Consumir energia de la bateria");
			System.out.println (" 3. Estado de la bateria y ahorro acumulado");
			System.out.println (" 4. Salir");
			System.out.println ("Elige una opcion");
			
			//Comprobamos que el usuario ha introducido un numero 
			if (!sc.hasNextInt()) {
			System.out.println ("Introduzca un numero valido");
			sc.next();
            continue;
            
			}
			opcion = sc.nextInt();
			
			try {
                if (opcion == 1) {
                	//Opcion 1. inyectar excedente
                	System.out.print("Introduce la cantidad de energia (en kwh): ");
                    double kwh = sc.nextDouble();
                    System.out.print("Introduce el precio actual por kwh (€): ");
                    double precio = sc.nextDouble();
                    
                    bateria.inyectar(kwh, precio);
                }else if (opcion == 2) {
                	//Opcion 2. consumir energia
                	System.out.print("Introduce el importe a consumir de la factura (€): ");
                    double importe = sc.nextDouble();
                	
                }else if (opcion == 3) {
                	//Opcion 3. estado de la bateria y del ahorro
                	double totalKwh = 0;
                    double totalEuros = 0;
                    
                    for (BloqueExcedente b : bateria.colaExcedentes) {
                        totalKwh += b.getKwh();
                        totalEuros += b.getSaldoEuros();
                    }
                    
                    System.out.println ("Estado de la bateria ");
                    System.out.printf ("Kwh total: %.2f kwh\n", totalKwh);
                    System.out.printf ("Saldo disponible: %.2f €\n", totalEuros);
                    System.out.printf ("Ahorro total acumulado: %.2f €\n", bateria.ahorroTotal);
                    
                   
                    
                }else if (opcion == 4) {
                	System.out.println("Saliendo...");
                	
                }else {
                	System.out.println("Opcion no valida, elige un numero del 1-4");
                }
                }catch (Exception e) {
                	//Captura el error si intentas gastar mas dinero del que hay
                	System.out.println(e.getMessage());
                	
                }
                	
                }while (opcion != 4);
		sc.close();
		}
		
		
		
		
	

}
