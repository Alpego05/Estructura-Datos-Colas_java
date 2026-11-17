package Colas;
/**
 * Representa un bloque de energia excedente
 */

public class BloqueExcedente {
	
	    private double kwh;
	    private final double precioKwh;
	    private double saldoEuros;

	    /**
	     * Crea un bloque excedente con los datos introducidos
	     * @param kwh Cantidad de energia en Kwh
	     * @param precioKwh Precio de la energia en el momento de la inyeccion
	     */
	    public BloqueExcedente(double kwh, double precioKwh) {
	        this.kwh = kwh;
	        this.precioKwh = precioKwh;
	        this.saldoEuros = kwh * precioKwh;
	    }

	    /** @return Los kwh que quedan en el bloque */
	    public double getKwh() { 
	    	return kwh; }

	    /** @return El precio por Kwh del bloque */
	    public double getPrecioKwh() { 
	    	return precioKwh; }

	    /** @return El saldo en euros en el bloque */
	    public double getSaldoEuros() { 
	    	return saldoEuros; }

	    /**
	     * Descuenta la cantidad de dinero del bloque y recalcula los Kwh
	     * @param importeSacar: importe en euros a descontar
	     */
	    public void descontar(double importeSacar) {
	        saldoEuros -= importeSacar;
	        kwh = saldoEuros / precioKwh;
	    }
	

}
