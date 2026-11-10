package Colas;

/**
 * Excepción general para errores en las operaciones de la batería virtual
 */
public class BateriaExcepcion extends Exception {
    /**
     * Crea la excepción con un mensaje descriptivo.
     * @param mensaje descripción del error
     */
    public BateriaExcepcion(String mensaje) {
        super(mensaje);
    }
}