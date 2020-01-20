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
import Generator.CGenerator;
import logica.codeGenerator.CodeGenerator;
import Generator.Generator;

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
    
    public String codeGenerate(List<String> codigo){
        IAL al  = AnalizadorLexico.getInstance();
        List<IDefaultToken> tokens = al.getTokens(codigo);
        Generator gen = new CGenerator();
        
        CodeGenerator generator = new CodeGenerator(tokens, gen);
        
        // Logica para generar C
        return generator.generateCode(tokens);
    }

//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================

}
