package Colas;

import java.util.LinkedList;


public class BateriaVirtual {
	
	//Hacemos la cola donde se guardan los bloques en el orden que llegan
	LinkedList<BloqueExcedente> colaExcedentes = new LinkedList<>();
    double ahorroTotal = 0;

    //Primer caso: Inyectar, añade un nuevo bloque al final de la cola
    public void inyectar (double kwh, double precio ) {
		colaExcedentes.add(new BloqueExcedente(kwh, precio));
    }
    
    //Segundo caso: Consumir, gasta el dinero de los bloques mas antiguos primero
    public void consumir(double importe) throws Exception {
    	//Calculamos cuanto dinero hay en total en la bateria
    	double saldoTotal = 0;
    	
    	for (BloqueExcedente b : colaExcedentes) {
    	    saldoTotal += b.saldoEuros;
    	}
    	
    	if (importe > saldoTotal) {
    		throw new Exception ( "No hay suficiente saldo en la bateria");
    	}
    	
    	//Gastamos aplicando el orden (el primero de la lista es el mas antigua)
    	while (importe > 0 && !colaExcedentes.isEmpty()) {
    		//Cogemos directamente el primer bloque de la lista (el mas antiguo)
    		BloqueExcedente actual = colaExcedentes.get(0);
    		
    		if (actual.saldoEuros <= importe) {
    			//Si el bloque no cubre todo, nos lo gastamos entero y lo borramos 
    			importe -= actual.saldoEuros;
                ahorroTotal += actual.saldoEuros;
                colaExcedentes.remove(0); //Borra el primer elemento de la lista
                
                
    		}else {
    			//Si el bloque cubre la factura, restamos lo gastado y dejamos el restante
    			actual.saldoEuros -= importe;
                actual.kwh = actual.saldoEuros / actual.precioKwh;
                ahorroTotal += importe;
                importe = 0;
    		}
    		
    		
    	}
    	
    	
    	
    }
    
    
    
    

}
