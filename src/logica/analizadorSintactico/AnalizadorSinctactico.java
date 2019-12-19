package logica.analizadorSintactico;

import logica.Expresiones;
import logica.analizadorLexico.IDefaultToken;
import logica.analizadorLexico.Token;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Pedro 
 */
public final class AnalizadorSinctactico implements IAS {

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public AnalizadorSinctactico() {

    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    /**
     * Método encargado de verificar que el código tenga la sintaxis adecuada.
     *
     * @param tokens Una lista de tokens.
     * @return un lista de Strings en donde cada String es un error. Si la lista
     * reornada está vacía quiere decir que no se encontró ningún error en el
     * código.
     */
    @Override
    public List<String> verificarCodigo(List<IDefaultToken> tokens) {
        List<String> totalErrores = new ArrayList<>();

        //Verifica que el código tenga un inicio y un final. 
        //y que dentro de los mismo. no existan aperturas de funciones 
        //ni sentencias de retorno.
        totalErrores.addAll(verificarEstructuraBasica(tokens));
        if (totalErrores.isEmpty()) {
            //Verifica que todas las estructuras básicas tengan 
            //un inicio y un final.
            totalErrores.addAll(verificarEstructuras(tokens));
            if (totalErrores.isEmpty()) {
                //Verifica que todos los parentesis tengan un inicio y un final.
                tokens.stream().forEach(t -> {
                    totalErrores.addAll(this.verificarParentesis(t));
                });
            }
        }

        return totalErrores;
    }

//==============================================================================
//  Métodos secundarios.
//==============================================================================
    /**
     * Autómata finíto no determinita que se encarga de verificar que el
     * pseudocódigo empiece con una sentencia de apertura y una de cierre.
     *
     * @param tokens lista de tóquens.
     * @return Una lista con los errores.
     */
    private Collection<? extends String> verificarEstructuraBasica(List<IDefaultToken> tokens) {
        List<String> errores = new ArrayList<>();
        String estado = "Inicio";
        int f = 0;
        IDefaultToken t = null;

        while (f < tokens.size()) {
            try {
                t = tokens.get(f);
            } catch (Exception e) {
            }
//            System.err.println("[1][Token: " + t.getKey() + ", F: " + f + ", Estado: " + estado + "]");

            switch (estado) {
                case "Inicio":
                    switch (t.getKey()) {
                        case "Vacío":
                            estado = "Inicio";
                            break;
                        case "Start":
                            estado = "Q1";
                            break;
                        default:
                            estado = "Error";
                            errores.add("Error en la línea: " + t.getLineIndex()
                                    + "\n" + "El código principal debe estar "
                                    + "entre las etiquetas INICIO y FINAL,"
                                    + " o alguna de sus variantes.");
                            break;
                    }
                    break;
                case "Q1":
                    if (t.getKey().equals("Finalize")) {
                        estado = "Q2";
                    } else if (t.getKey().equals("Start")) {
                        estado = "Error";
                        errores.add("Error en la línea: " + t.getLineIndex()
                                + "\nSólo se admite una sentencia "
                                + t.getValue() + ".");
                    } else if (Pattern.compile(Expresiones.EFUNC)
                            .matcher(t.getValue()).find()
                            || Pattern.compile(Expresiones.ERETO)
                            .matcher(t.getValue()).find()) {
                        estado = "Error";
                        errores.add("Error en la línea: " + t.getLineIndex()
                                + "\nNo se admiten declaraciones ni retornos "
                                + "de funciones dentro de la estructura "
                                + "principal del programa.");
                    } else if (f >= tokens.size()) {
                        estado = "Error";
                    } else {
                        estado = "Q1";
                    }
                    break;
                case "Q2":
                    if (Pattern.compile(Expresiones.EFUNC)
                            .matcher(t.getValue()).find()) {
                        estado = "Q2";
                    } else if (t.getKey().equals("Vacío")) {
                        estado = "Q2";
                    } else if (Pattern.compile(Expresiones.ERETO)
                            .matcher(t.getValue()).find()) {
                        estado = "Q2";
                    } else if (t.getKey().equals("Finalize")) {
                        estado = "Error";
                        errores.add("Error en la línea: " + t.getLineIndex()
                                + "\nSólo se admite una sentencia "
                                + t.getValue() + ".");
                    } else {
                        estado = "Error";
                        errores.add("\nSólo se admiten implementaciones de "
                                + "funciones después del bloque principal de "
                                + "código.");
                    }
                    break;

                case "Error":

                    break;
            }

            f++;
        }
        if (estado.equals("Q1")) {
            //error.
            errores.add("No se encontró una sentencia de cierre para el "
                    + "bloque principal del pseudocódigo.");
        }
        return errores;
    }

    /**
     * Autómata de pila que recorre todos los tokens y verifica que cada
     * estructura (SI, MIENTRAS, PARA, Función) tenga su correspondiente
     * sentencia de cierre (FIN).
     *
     * @param tokens
     * @return
     */
    private List<String> verificarEstructuras(List<IDefaultToken> tokens) {
        List<String> errores = new ArrayList<>();
        List<IDefaultToken> pila = new ArrayList<>();
        pila.add(new Token("####", "####", -1)); //Elemento de pila vacía.

        //Se recorre la lista de tokens.
        tokens.stream().forEach(t -> {
            switch (t.getKey()) {
                case "Apertura":
                    if (Pattern.compile(Expresiones.ESINO).matcher(t.getValue()).find()) {
                        if (isPilaVacia(pila, "####") || !Pattern.compile(Expresiones.ECOND).matcher(pila.get(pila.size() - 1).getValue()).find()) {
                            //Si la pila está vacía, o lo encontrado no es un SI.
                            errores.add("Error en la línea " + t.getLineIndex()
                                    + ":\nLa sentencias SINO encontrada no "
                                    + "corresponde a la apertura de ningún SI.");
                        }
                        pila.remove(pila.size() - 1);
                    }
                    pila.add(t);
                    System.err.println(t.getValue());
                    break;
                case "Cierre":
                    if (!isPilaVacia(pila, "####")) {
                        //Si la pila no está vacía.
                        if (Pattern.compile(Expresiones.ERETO).matcher(t.getValue()).find()) {
                            if (!Pattern.compile(Expresiones.EFUNC).matcher(pila.get(pila.size() - 1).getValue()).find()) {
                                errores.add("Error en la línea " + t.getLineIndex()
                                        + ":\nLa sentencia RETORNO encontrada no corresponde"
                                        + " a ninguna función.");
                            } else {
                                pila.remove(pila.size() - 1);
                            }
                        } else {
                            if (Pattern.compile(Expresiones.EFUNC).matcher(pila.get(pila.size() - 1).getValue()).find()) {
                                errores.add("Error en la línea " + t.getLineIndex()
                                        + ":\nLa sentencia FIN encontrada no es "
                                        + "adecuada para ese contexto.");
                            } else {
                                pila.remove(pila.size() - 1);
                            }
                        }
                    } else {
                        //Si la pila está vacía.
                        errores.add("Error en la línea " + t.getLineIndex()
                                + ":\nLa sentencia FIN encontrada no corresponde"
                                + " a la apertura de ninguna estructura.");
                    }

                    break;
                case "No reconocido":
                    errores.add("Error en la línea " + t.getLineIndex()
                            + ":\nNo se reconoce el código escrito en esa línea.");
                    break;
            }
        });

        pila.stream().forEach(ep -> {
            if (!ep.getKey().equals("####")) {
                //Si hay un elemento en la pila adicional al de pila vacía.
                errores.add("Error en la línea " + ep.getLineIndex()
                        + ":\n No se encontró una sentencia de cierre para esa "
                        + ep.getKey());
            }
        });

        return errores;
    }

    /**
     * Autómata de pila que recorre una línea de código y verifica que todos los
     * parentesis tengan su correspondiente contraparte.
     *
     * @param t el token a analizar.
     * @return una lista de Strings que contiene los errores encontrados.
     */
    private List<String> verificarParentesis(IDefaultToken t) {
        List<String> errores = new ArrayList<>();
        List<String> pila = new ArrayList<>();
        pila.add("####"); //Palabra de pila vacía.

        Arrays.asList(t.getValue().split("")).stream().forEach(caracter -> {
            switch (caracter) {
                case "(":
                    pila.add("(");
                    break;
                case ")":
                    if (pila.get(pila.size() - 1).equals("####")) {
                        //Si la pila está vacía.
                        errores.add("Error en la línea " + t.getLineIndex()
                                + ":\nFalta un parentesis de apertura.");
                    } else {
                        pila.remove(pila.size() - 1);
                    }
                    break;
            }
        });

        if (!pila.get(pila.size() - 1).equals("####")) {
            //Si al finalizar la pila no está vacía.
            errores.add("Error en la línea " + t.getLineIndex()
                    + ":\nFalta(n) " + (pila.size() - 1)
                    + " parentesis de cierre.");
        }

        return errores;
    }

    /**
     * Método que verifica que la pila contenga un valor especifico en el último
     * lugar.
     *
     * @param p Una lista de tokens.
     * @param cpv El valor de la key que se buscará.
     * @return true en caso de que el valor especificado se encuentre en el
     * último lugar. Retorna false en caso contrario.
     */
    private boolean isPilaVacia(List<IDefaultToken> p, String cpv) {
        return p.get(p.size() - 1).getKey().equals(cpv);
    }
//==============================================================================
//  Getters and Setters.
//==============================================================================

}
