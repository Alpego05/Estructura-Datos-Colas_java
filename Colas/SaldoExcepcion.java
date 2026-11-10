package Colas;

/**
 * Excepción lanzada cuando se intenta consumir más saldo del disponible
 */
public class SaldoExcepcion extends BateriaExcepcion {
    /**
     * Crea la excepción con un mensaje descriptivo
     * @param mensaje descripción del error
     */
    public SaldoExcepcion(String mensaje) {
        super(mensaje);
    }
}