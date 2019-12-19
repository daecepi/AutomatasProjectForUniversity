package archivos;

/**
 * Interfaz que declara los servicios que ofrece el componente de persistencia.
 *
 * @author Pedro
 */
public interface IFP {

    /**
     * Método que retorna la factoría de persistencias de archivos.
     *
     * @return Una instancia de FPArchivos.
     */
    public FPArchivos persistenciasArchivos();

}
