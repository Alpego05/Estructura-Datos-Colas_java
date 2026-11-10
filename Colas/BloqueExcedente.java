package Colas;

/**
 * Representa un bloque de energía excedente inyectado a un precio concreto
 */
public class BloqueExcedente {
    private double kwh;
    private final double precioKwh;
    private double saldoEuros;

    /**
     * Crea un bloque a partir de los kWh y el precio del momento
     * @param kwh energía inyectada (kWh)
     * @param precioKwh precio por kWh (€)
     */
    public BloqueExcedente(double kwh, double precioKwh) {
        this.kwh = kwh;
        this.precioKwh = precioKwh;
        this.saldoEuros = kwh * precioKwh;
    }

    /** @return kWh que quedan en el bloque */
    public double getKwh() { return kwh; }

    /** @return precio por kWh al que se inyectó */
    public double getPrecioKwh() { return precioKwh; }

    /** @return saldo en euros que queda en el bloque */
    public double getSaldoEuros() { return saldoEuros; }

    /**
     * Descuenta un importe del bloque y actualiza los kWh restantes
     * @param importe euros a descontar (no mayor que el saldo del bloque)
     */
    public void descontar(double importe) {
        saldoEuros -= importe;
        kwh = saldoEuros / precioKwh;
    }
}

