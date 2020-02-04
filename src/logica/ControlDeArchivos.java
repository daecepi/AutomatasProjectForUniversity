package logica;

import logica.analizadorLexico.AnalizadorLexico;
import logica.analizadorLexico.IAL;
import logica.analizadorLexico.IDefaultToken;
import logica.analizadorSintactico.AnalizadorSinctactico;
import logica.analizadorSintactico.IAS;
import archivos.FPArchivos;
import archivos.IFP;
import archivos.SelectorDePersistencias;
import java.util.List;
import Generator.GeneradorDeC;
import logica.codeGenerator.GeneradorDeCodigo;
import logica.codeGenerator.CGI;
import Generator.Generador;

/**
 *
 * @author David E Cermeño Pinzon
 */
public final class ControlDeArchivos {

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public ControlDeArchivos() {

    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    /**
     *
     * @param t
     * @param path
     * @return
     */
    public boolean guardarArchivo(String t, String path) {
        IFP sp = new SelectorDePersistencias();
        return sp.persistenciasArchivos()
                .getPersistencias(FPArchivos.ARCHIVOS_PLANO)
                .escribir(t, path);
    }

    /**
     *
     * @param path
     * @return
     */
    public String abrirArchivo(String path) {
        IFP sp = new SelectorDePersistencias();
        return (String) sp.persistenciasArchivos()
                .getPersistencias(FPArchivos.ARCHIVOS_PLANO)
                .leer(path);
    }
    

    /**
     *
     * @param codigo
     * @return
     */
    public List<String> verificarCódigo(List<String> codigo) {
        IAL al = AnalizadorLexico.getInstance();
        List<IDefaultToken> tokens = al.getTokens(codigo);
        IAS as = new AnalizadorSinctactico();

        return as.verificarCodigo(tokens);
    }
    
    /**
     * Metodo que genera el codigo basado en el la clase de generacion de codigo
     * */
    public String codeGenerate(List<String> codigo, String lenguaje){
        IAL al  = AnalizadorLexico.getInstance();
        List<IDefaultToken> tokens = al.getTokens(codigo);
        
        System.out.println(tokens);
        Generador gen = selectorDeGenerador(lenguaje);
        
        
        CGI generator = new GeneradorDeCodigo(tokens, gen);
        
        // Logica para generar C
        return generator.generateCode(tokens);
    }

    public String getExtension(String lenguaje){
        Generador gen = selectorDeGenerador(lenguaje);
        
        return gen.getGeneratorExtension();
    }
    
    private Generador selectorDeGenerador(String lenguaje){
        switch(lenguaje){
            case "C":
                return new GeneradorDeC();
            // Any other lenguage in which support is added
            default:
                return new Generador();
        }
    }
//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================

}
