package Colas;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Batería virtual fotovoltaica que gestiona los excedentes con política FIFO.
 */
public class BateriaVirtual {
    private static final double EPSILON = 1e-9;

    private final Queue<BloqueExcedente> colaExcedentes = new LinkedList<>();
    private double ahorroTotal = 0;

    /**
     * Inyecta un nuevo bloque de excedente al final de la cola.
     * @param kwh energía inyectada (kWh), mayor que 0
     * @param precio precio por kWh (€), mayor que 0
     * @throws BateriaExcepcion si algún valor no es positivo
     */
    public void inyectar(double kwh, double precio) throws BateriaExcepcion {
        if (kwh <= 0 || precio <= 0) {
            throw new BateriaExcepcion("Los kWh y el precio deben ser mayores que 0.");
        }
        colaExcedentes.add(new BloqueExcedente(kwh, precio));
    }

    /**
     * Consume energía de la batería liquidando primero los bloques más antiguos (FIFO).
     * El importe consumido es kWh * precio.
     * @param kwh energía a consumir (kWh), mayor que 0
     * @param precio precio actual por kWh (€), mayor que 0
     * @return importe total consumido en euros
     * @throws BateriaExcepcion si algún valor no es positivo
     * @throws SaldoExcepcion si no hay saldo suficiente
     */
    public double consumir(double kwh, double precio) throws BateriaExcepcion {
        if (kwh <= 0 || precio <= 0) {
            throw new BateriaExcepcion("Los kWh y el precio deben ser mayores que 0.");
        }
        double importe = kwh * precio;
        double total = importe;
        double saldoTotal = getSaldoTotal();

        if (importe > saldoTotal + EPSILON) {
            throw new SaldoExcepcion(String.format(
                "Saldo insuficiente: necesitas %.2f € y solo hay %.2f €.", importe, saldoTotal));
        }

        while (importe > EPSILON && !colaExcedentes.isEmpty()) {
            BloqueExcedente actual = colaExcedentes.peek();
            if (actual.getSaldoEuros() <= importe + EPSILON) {
                importe -= actual.getSaldoEuros();
                ahorroTotal += actual.getSaldoEuros();
                colaExcedentes.poll();
            } else {
                actual.descontar(importe);
                ahorroTotal += importe;
                importe = 0;
            }
        }
        return total;
    }

    /** @return saldo total en euros */
    public double getSaldoTotal() {
        double total = 0;
        for (BloqueExcedente b : colaExcedentes) total += b.getSaldoEuros();
        return total;
    }

    /** @return kWh totales almacenados */
    public double getKwhTotal() {
        double total = 0;
        for (BloqueExcedente b : colaExcedentes) total += b.getKwh();
        return total;
    }

    /** @return ahorro total acumulado en euros */
    public double getAhorroTotal() { return ahorroTotal; }

    /** @return vista de solo lectura de los bloques, del más antiguo al más reciente */
    public Collection<BloqueExcedente> getBloques() {
        return Collections.unmodifiableCollection(colaExcedentes);
    }
}