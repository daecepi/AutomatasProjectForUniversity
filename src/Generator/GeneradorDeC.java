/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generator;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import jdk.swing.interop.SwingInterOpUtils;
import logica.analizadorLexico.IDefaultToken;
import java.util.ArrayList;

/**
 *
 * @author davidcermeno
 */
public class GeneradorDeC extends Generador{
    
    public String FILE_EXTENSION = ".c";
    private Dictionary listOfVariables = new Hashtable();
    
    
    
     public String generateCode(List<IDefaultToken> tokens){
         // Primero se divide el codigo para saber las dos funciones necesarias
         List<List<IDefaultToken>> dividedTokens = this.divideCodigo(tokens);

         List<IDefaultToken> mainTokens = dividedTokens.get(0);
         List<IDefaultToken> functionTokens = dividedTokens.get(1);

         System.out.println("Main tokens" + mainTokens);
         System.out.println("Function tokens" + functionTokens);

         /*for (int i = 0 ; i < mainTokens.size(); i++){

         }*/
         
         String codeBuilt = "#include <stdio.h> \n\n // function declaration";
         String mainFunctionCode = "";
         String functionDeclaration = "";
         String functionsDefinitions = "";

         List<IDefaultToken> mainTokensCopy = mainTokens;
         mainFunctionCode += analizeBlock(mainTokensCopy);



         // Appeding code's end of program
         codeBuilt += functionDeclaration
                 + "\n\nint main(){\n"
                 + mainFunctionCode
                 + "return 0;\n}\n\n// Functions defined"
                 + functionsDefinitions;

         return codeBuilt;
     }



    @Override
    public String analizeBlock(List<IDefaultToken> tokens){
        List<IDefaultToken> tokensCopy = tokens;

        System.out.println( "Size" + tokensCopy.size());

        String returned = "";
        for (int i = 0; i < tokensCopy.size(); i++){
            IDefaultToken token = null;

            //try{
            token = tokensCopy.get(i);
            //}catch (Exception e){
            //    return null ;
            //}

            System.out.println((token.getKey()));
            switch (token.getKey()){
                case "Apertura":
                    System.out.println("entre");
                    returned += (convertIf(token, tokensCopy));
                    break;
                case "Asignación":
                    System.out.println("entre");
                    returned += (convertAssignement(token, tokensCopy));
                    break;
                case "Escrebir":
                    System.out.println("entre");
                    returned += (convertWrite(token, tokensCopy));
                    break;
                case "Leer":
                    System.out.println("entre");
                    returned += convertRead(token, tokensCopy);
                    break;
                case "Vacío":
                    System.out.println("entre");
                    returned += token.getValue().replace("#", "//");
                    break;
                case "Cierre":
                    returned += "}";
                    break;
            }
            returned += "\n";

        }

        System.out.println("Hello"+returned);

         return returned;
    }

    @Override
    public String convertAssignement(IDefaultToken token, List<IDefaultToken> tokens) {
         String finalSentence = "";
        try{
            //Validating the data type
            String tokenValue = token.getValue();
            int divisionToken =tokenValue.indexOf(":=");
            String variableName = tokenValue.substring(1, divisionToken -1);
            String value = tokenValue.substring( divisionToken + 2, tokenValue.length() - 1);

            variableName.replace("$", "");

            if (listOfVariables.get(variableName) == null){
                String dataType = "";
                if(value.indexOf("\"") != -1){ //Variable is a String
                    int count = value.replace("*", "").length();
                    System.out.println("Still the same string must be " + value);
                    finalSentence += "char " + variableName + "[" + count + "]";

                    dataType = "%s";
                }else if (value.contains(".") || value.contains(",")){ // Data type is float
                    finalSentence += "double " + variableName;

                    dataType = "%f";
                }else{
                    finalSentence += "long " + variableName;

                    dataType = "%d";
                }

                this.listOfVariables.put(variableName, dataType); // Adding the variable to the ones registered
                // Still no support for vectors
            }else{
                finalSentence += variableName;
            }
            if(value.contains("$")){
                finalSentence += " = " + value.replace("$", "") + ";";
            }else{
                finalSentence += " = " + value + ";";
            }
        }catch (Exception e){
            return null;
        }
         return finalSentence;
    }

    @Override
    public String convertWrite(IDefaultToken token, List<IDefaultToken> tokens){
         String finalSentence = "printf(\"";

         System.out.println("Entre write");

         // Quitamos las palabras reservadas
         String toPrint = token.getValue().replace("ESCRIBIR", "").replace("MOSTRAR", "").replace("IMPRIMIR", "").replace(";", "");

        System.out.println("Entre write");

         // Atendemos a la concatenacion
        String[] listOfPrints = toPrint.split("+", 2);
        String listForTypes = "";


        for(String print : listOfPrints){
            if (print.contains("\"") || print.contains("'")){ // string
                finalSentence += ("%s");

                listForTypes += (", " + print);
            }else if(print.contains("$")){ // variable
                String variableName = print.replace("$", "");
                finalSentence += (this.listOfVariables.get(variableName).toString());

                listForTypes += (", " + variableName);
            }else{ // number
                finalSentence += ("%d");

                listForTypes += (", " + print);
            }
        }

        System.out.println("Entre write");

        finalSentence +=("\", "); // End of string
        finalSentence += (listForTypes);

        finalSentence += ");";

        return finalSentence;
    }


    /*
    * NOTIFICATION: currently reading only strings and not must be modified
    * */
    @Override
    public String convertRead(IDefaultToken token, List<IDefaultToken> tokens){
         String finalSentence = "";

         return finalSentence;
    }

    @Override
    public String convertIf(IDefaultToken token, List<IDefaultToken> tokens) {
        String finalSentence = token.getValue().replace("SINO", "}else").replace(":=", "=").replace("SI", "if").replace(":","{").replace("$", "").replace("MIENTRAS", "while").replace("PARA", "for");
        return finalSentence;
    }

    @Override
    public String convertFor(IDefaultToken token, List<IDefaultToken> tokens) {
        return null;
    }

    @Override
    public String converWhile(IDefaultToken token, List<IDefaultToken> tokens){
         return null;
    }

    @Override
    public String getGeneratorExtension(){
         return this.FILE_EXTENSION;
     }

}
