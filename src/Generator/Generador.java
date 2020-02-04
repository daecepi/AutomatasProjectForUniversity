/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generator;

import java.util.List;
import logica.analizadorLexico.IDefaultToken;
import java.util.ArrayList;

/**
 *
 * @author davidcermeno
 */
public abstract class Generador {
    public String FILE_EXTENSION = ".unknown";
    
    /*
        Funcion para obtener la porsion de la lista que contiene el codigo a estar en los tokens
    */
    public List<List<IDefaultToken>> divideCodigo(List<IDefaultToken> tokens){
        
        int startIndex = 0;
        int endIndex = 0;
        int i = 0;
        while(i < tokens.size()){
            IDefaultToken t;
            try {
                t = tokens.get(i);
            } catch (Exception e) {
                t = null;
                System.out.println("Error ocurred on index " + i);
                continue;
            }
            
            
            switch(t.getKey()){
                case "Inicio":
                    startIndex = i;
                    break;
            }
                
        }
        
        List<List<IDefaultToken>> finalList = new ArrayList<List<IDefaultToken>>();
        finalList.add(tokens.subList(startIndex, endIndex)); // Porcion with the main code
        finalList.add(tokens.subList(endIndex+1, tokens.size() - 1)); // Porcion with the function definitions
        return finalList;
    }
    
    public abstract String generateCode(List<IDefaultToken> tokens);
    
    
     public abstract String getGeneratorExtension();
}
