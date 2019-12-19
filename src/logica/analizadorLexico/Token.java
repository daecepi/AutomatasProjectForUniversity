package logica.analizadorLexico;

/**
 *
 * @author Pedro
 */
public final class Token implements IDefaultToken {

    private final String key;
    private final String value;
    private final int lineIndex;

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     *
     * @param key Un identificador que describe el tipo de token.
     * @param value La sección de código que represenat el token.
     * @param lineIndex la línea de código donde se encuentra el tóken con
     * respecto al resto del código.;
     */
    public Token(String key, String value, int lineIndex) {
        this.key = key;
        this.value = value;
        this.lineIndex = lineIndex;
    }

//==============================================================================
//  Métodos principales.
//==============================================================================
//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================
    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int getLineIndex() {
        return lineIndex;
    }

    @Override
    public String toString() {
        return "[Key: " + key + ", Value: " + value + ", Index: " + lineIndex + ']';
    }

}
