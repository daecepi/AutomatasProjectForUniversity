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
public class GeneradorDeC extends Generador{
    
    public String FILE_EXTENSION = ".c";
    
    
    
     public String generateCode(List<IDefaultToken> tokens){
         // Primero se divide el codigo para saber las dos funciones necesarias
         
         
         
         String codeBuilt = "#include <stdio.h>; \n\n // function declaration";
         String mainFunctionCode = "";
         String functionDeclaration = "";
         String functionsDefinitions = "";
         
         
         
         // Appeding code's end of program
         codeBuilt += functionDeclaration
                 + "\n\nint main(){\n"
                 + mainFunctionCode
                 + "return 0;\n}\n\n// Functions defined"
                 + functionsDefinitions;
         
         return codeBuilt;
     }
     
     public String getGeneratorExtension(){
         return this.FILE_EXTENSION;
     }
     
     private String convertirLoop(){
         return "";
     }
}
