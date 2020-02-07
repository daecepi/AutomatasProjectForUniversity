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
public class GeneradorDeCodigo implements CGI {
    
    private Generador generador;

    public GeneradorDeCodigo() {
    }

    @Override
    public void anadirGenerador(Generador generador){
        this.generador = generador;
    }

    @Override
    public String generateCode(List<IDefaultToken> tokens){
        String codeRetrieved = this.generador.generateCode(tokens);
        System.out.println("Code retreived"+codeRetrieved);
        return codeRetrieved;
    }
}
