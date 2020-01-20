package logica.codeGenerator;


import Generator.Generador;
import java.util.List;
import logica.analizadorLexico.IDefaultToken;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author davidcermeno
 */
public class GeneradorDeCodigo {
    
    private List<IDefaultToken> codeWords;
    private Generador generador;

    public GeneradorDeCodigo(List<IDefaultToken> codeWords, Generador generador) {
        this.codeWords = codeWords;
        this.generador = generador;
    }
    
    public String generateCode(List<IDefaultToken> tokens){
        String codeRetrieved = generador.generateCode(tokens);
        
        return codeRetrieved;
    }
}
