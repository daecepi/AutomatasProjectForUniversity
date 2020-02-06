package archivos;

/**
 * Clase que proporciona las instancias de persistencias de archivos.
 *
 * @author David E Cermeño Pinzon
 */
public final class FPArchivos {

     /**
     * Una constante empleada para especificar la instancia que retornará el
     * método getInstance(), en este caso retornará una instancia de PlainFiles.
     */
    public final static int ARCHIVOS_PLANO = 3;

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public FPArchivos() {
        
    }

//==============================================================================
//  Otros métodos.
//==============================================================================
    /**
     * Método que retorna un objeto de tipo IArchivo, la instancia dependerá del
     * valor del entero que se pase por parámetro.
     *
     * @return IArchivo
     * @param tipo, un entero que determina la instancia del objeto de retorno.
     */
    @SuppressWarnings("null")
    public IArchivo getPersistencias(int tipo) {

        IArchivo instance = null;

        switch (tipo) {
            case FPArchivos.ARCHIVOS_PLANO:
                instance = new ArchivosPlanos();
        }

        return instance;
    }
//==============================================================================
//  Metodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================

}
