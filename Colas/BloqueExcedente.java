package Colas;

public class BloqueExcedente {
	double kwh;
	double precioKwh;
	double saldoEuros;
	
	//Constructor: sirve para crear el bloque dandole los kwh y el precio
	public BloqueExcedente (double kwh, double precioKwh) {
		this.kwh = kwh;
		this.precioKwh = precioKwh;
		this.saldoEuros = kwh * precioKwh; 
		
	}
	public double getKwh() {
        return kwh;
    }
	public void setKwh(double kwh) {
        this.kwh = kwh;
    }
	public double getPrecioKwh() {
        return precioKwh;
    }
	public void setPrecioKwh(double precioKwh) {
        this.precioKwh = precioKwh;
    }
	public double getSaldoEuros() {
        return saldoEuros;
    }
	public void setSaldoEuros(double saldoEuros) {
        this.saldoEuros = saldoEuros;
    }
	
	}


