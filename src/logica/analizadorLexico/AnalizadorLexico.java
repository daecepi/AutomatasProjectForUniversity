package logica.analizadorLexico;

import logica.Expresiones;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author David E Cermeño Pinzon
 */
public final class AnalizadorLexico implements IAL {

    private static AnalizadorLexico instance;
    private final Pattern REGEX_ASIGNACIONES = Pattern.compile(Expresiones.EASIG);
    private final Pattern REGEX_LLAMADA = Pattern.compile(Expresiones.EIFUN);
    private final Pattern REGEX_APERTURA = Pattern.compile(Expresiones.EOPEN);
    private final Pattern REGEX_LEER = Pattern.compile(Expresiones.ELEER);
    private final Pattern REGEX_ESCRIBIR = Pattern.compile(Expresiones.EIMPR);
    private final Pattern REGEX_CIERRES = Pattern.compile(Expresiones.CLOSE);
    private final Pattern REGEX_COMENZAR = Pattern.compile(Expresiones.ESTART);
    private final Pattern REGEX_FINALIZAR = Pattern.compile(Expresiones.EFINALIZE);
    private final Pattern REGEX_VACIO = Pattern.compile(Expresiones.EMPL);

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    private AnalizadorLexico() {

    }

    /**
     * Método que retorna una única instancia de la clase.
     *
     * @return una instancia de la clase.
     */
    public static AnalizadorLexico getInstance() {
        if (instance == null) {
            instance = new AnalizadorLexico();
        }

        return instance;
    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    /**
     * Combierte el código en una lista de tokens.
     *
     * @param codigo una lista con todas las líneas del código. .
     */
    @Override
    public List<IDefaultToken> getTokens(List<String> codigo) {
        List<IDefaultToken> tokens = new ArrayList<>();

        codigo.stream().forEach((lineaCodigo) -> {
            tokens.add(new Token(this.getTipoToken(lineaCodigo),
                    lineaCodigo,
                    codigo.indexOf(lineaCodigo) + 1));
        });

        return tokens;
    }

    /**
     * Determina el tipo de token al que pertenece una línea de código.
     *
     *
     * @return el valor correspondiente a determinado tipo de token, Si la línea
     * de código no coincide con ningúno de los tokens se retornará "No
     * reconocido".
     */
    private String getTipoToken(String sCode) {
        if (REGEX_VACIO.matcher(sCode).find()) {
            //Línea vacía.
            System.err.println("Vacío");
            return "Vacío";
        } else if (REGEX_COMENZAR.matcher(sCode).find()) {
            //Expresión INICIAR
            System.err.println("START");
            return "Start";
        } else if (REGEX_FINALIZAR.matcher(sCode).find()) {
            //Expresión FINALIZAR
            System.err.println("FINALIZE");
            return "Finalize";
        } else if (REGEX_ASIGNACIONES.matcher(sCode).find()) {
            //Expresión de asignación.
            System.err.println("Asignación");
            return "Asignación";
        } else if (REGEX_LLAMADA.matcher(sCode).find()) {
            //Llamada a función.
            System.err.println("Llamada");
            return "Llamada";
        } else if (REGEX_APERTURA.matcher(sCode).find()) {
            //Expresión de apertura.
            System.err.println("Apertura");
            return "Apertura";
        } else if (REGEX_LEER.matcher(sCode).find()) {
            //Expresión leer.
            System.err.println("Leer");
            return "Leer";
        } else if (REGEX_ESCRIBIR.matcher(sCode).find()) {
            //Expresión escribir.
            System.err.println("Escribir");
            return "Escrebir";
        } else if (REGEX_CIERRES.matcher(sCode).find()) {
            //Expresión cerrar.
            System.err.println("FIN");
            return "Cierre";
        }

        System.err.println("No reconocido.");
        //Token no reconocido (Error de escritura).
        return "No reconocido";
    }
//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================

}
