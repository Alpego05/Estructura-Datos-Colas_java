package Colas;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Gestiona la cola de excedentes y el calculo del ahorro aplicando FIFO
 */

public class BateriaVirtual {
	private final Queue<BloqueExcedente> colaExcedentes = new LinkedList<>();
    private double ahorroTotal = 0;

    /**
     * Inyecta un bloque de excedentes a la cola
     * @param kwh Energia en Kwh (debe ser mayor que 0)
     * @param precio Precio por Kwh (debe ser mayr que 0)
     * @throws BateriaExcepcion Si los valores no son validos
     */
    public void inyectar(double kwh, double precio) throws BateriaExcepcion {
        if (kwh <= 0 || precio <= 0) {
            throw new BateriaExcepcion("Los valores deben ser mayores que cero");
        }
        colaExcedentes.add(new BloqueExcedente(kwh, precio));
    }

    /**
     * Consume energia de la bateria utilizando el criterio FIFO
     * @param Kwh Energia a consumir en Kwh
     * @param precio Precio actual por Kwh
     * @return El importe total consumido
     * @throws BateriaExcepcion Si los valores son incorrectos
     * @throws SaldoExcepcion si no hay saldo suficiente
     */
    public double consumir(double kwh, double precio) throws BateriaExcepcion {
        if (kwh <= 0 || precio <= 0) {
            throw new BateriaExcepcion("Los valores deben ser mayores que cero");
        }
        double importeNecesario = kwh * precio;
       

        if (importeNecesario > getSaldoTotal()) {
            throw new BateriaExcepcion("No hay suficiente saldo en la bateria");
        }
        
        double importeRestante = importeNecesario;
        
        while (importeRestante > 0 && !colaExcedentes.isEmpty()) {
            BloqueExcedente bloqueActual = colaExcedentes.peek();

            if (bloqueActual.getSaldoEuros() <= importeRestante) {
                importeRestante -= bloqueActual.getSaldoEuros();
                ahorroTotal += bloqueActual.getSaldoEuros();
                colaExcedentes.poll();
            } else {
                bloqueActual.descontar(importeRestante);
                ahorroTotal += importeRestante;
                importeRestante = 0;
            }
        }
        return importeNecesario;
    }

       

    /** @return Saldo total disponible en euros */
    public double getSaldoTotal() {
        double total = 0;
        for (BloqueExcedente b : colaExcedentes) {
        	total += b.getSaldoEuros();
        }
        return total;
    }

    /** @return Total de Kwh almacenados en la bateria */
    public double getKwhTotal() {
        double total = 0;
        for (BloqueExcedente b : colaExcedentes) {
        	total += b.getKwh();
        }
        return total;
    }

    /** @return Ahorro total acumulado en euros */
    public double getAhorroTotal() { 
    	return ahorroTotal; 
    	}

    /** @return Cola de bloques de excendentes */
    public Queue<BloqueExcedente> getBloques() {
        return colaExcedentes;
    }

}
