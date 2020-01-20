package logica.codeGenerator;


import Generator.Generator;
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
public class CodeGenerator {
    
    private List<IDefaultToken> codeWords;
    private Generator generator;

    public CodeGenerator(List<IDefaultToken> codeWords, Generator generator) {
        this.codeWords = codeWords;
        this.generator = generator;
    }
    
    public void generateCode(List<IDefaultToken> tokens){
        
    }
}
