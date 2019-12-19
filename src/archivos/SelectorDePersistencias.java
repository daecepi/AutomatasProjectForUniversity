package archivos;

/**
 * Clase que permite seleccionar el grupo de persistencias que se desea.
 *
 * @author Pedro
 */
public final class SelectorDePersistencias implements IFP {

//==============================================================================
//  Constructores y métodos de inicialización
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public SelectorDePersistencias() {
    }
//==============================================================================
//  Otros métodos.
//==============================================================================

    /**
     * Método que permite obtener la factoría de persistencias de archivos.
     *
     * @return FPArchivos.
     */
    @Override
    public FPArchivos persistenciasArchivos() {
        
        return new FPArchivos();
    }
//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================
}
