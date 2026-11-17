package Colas;

/**
 * Excepcion para los errores de la bateria virtual
 */

public class SaldoExcepcion extends BateriaExcepcion {
    /**
     * Creamos el constructor de la excepcion
     * @param mensaje explicativo del error
     */
    public SaldoExcepcion(String mensaje) {
        super(mensaje);
    }
}
