package Colas;
/**
 * Excepcion para los errores de la bateria virtual
 */


public class BateriaExcepcion extends Exception {
    /**
     * Creamos el constructor de la excepcion
     * @param mensaje explicativo del error
     */
    public BateriaExcepcion(String mensaje) {
        super(mensaje);
    }
}
