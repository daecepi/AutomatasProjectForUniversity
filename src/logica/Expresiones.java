package logica;

/**
 *
 * @author David E Cermeño Pinzon
 */
public class Expresiones {

    //PALABRAS RESERVADAS.    
    //[Palabra reservada INICIO]
    public static final String START = "(INICIO|EMPEZAR|COMENZAR)";
    //[Palabra reservada FINAL]
    public static final String FINALIZE = "(FINAL|TERMINAR|FINALIZAR)";
    //[Palabra reservada cierre]
    public static final String END = "FIN";
    //[Palabra reservada retorno]
    public static final String RETURN = "RETORNO";

    //[Palabra reservada SI]
    public static final String IF = "SI";
    //[Palabra reservada SINO]
    public static final String ELSE = "SINO";
    //[Palabra reservada MIENTRAS]
    public static final String WHILE = "MIENTRAS";
    //[Palabra reservada PARA]
    public static final String FOR = "PARA";
    //[Palabra reservada LEER]
    public static final String READ = "LEER";
    //[Palabra reservada IMPRIMIR]
    public static final String PRINT = "(IMPRIMIR|MOSTRAR|ESCRIBIR)";
    //[Palabra reservada VERDADERO]
    public static final String TRUE = "V(ERDADERO)?";
    //[Palabra reservada FALSO]
    public static final String FALSE = "F(ALSO)?";

//TOKENS BASICOS.
    //[Comentario]
    public static final String COMT = "(\\#)(.*)";
    //[Espacio]
    public static final String S = "\\s*";
    //[Inicio de linea]
    public static final String IL = "^" + S + "";
    //[Final de linea]
    public static final String FL = "((" + S + ")(" + COMT + "))|(" + S + "$)";
    //[Numeros Enteros]
    public static final String E = "\\d+";
    //[Numeros Decimales]
    public static final String D = "\\d+\\.\\d+";
    //[Valor Numerico]
    public static final String N = "(" + E + "|" + D + ")";
    //[Valores Bool]
    public static final String B = "(" + TRUE + ")|(" + FALSE + ")";
    //[Identificador]
    public static final String ID = "(\\$)([a-zñÑA-Z])(\\w|ñ|Ñ|[\\_])*";
    //[Vector]
    public static final String VC = "(" + ID + ")(" + S + ")(\\[)(" + ID + "|" + E + ")(\\])";
    //[Matriz]
    public static final String MZ = "(" + ID + ")(" + S + ")(\\[)(" + ID + "|" + E + ")(\\])(" + S + ")(\\[)(" + ID + "|" + E + ")(\\])";
    //[Agrupadores]
    public static final String CONJ = "(" + VC + "|" + MZ + ")";
    //[Operadores Matematicos]
    public static final String OM = "[\\+\\-\\*\\/\\%\\^]";
    //[Operadores Logicos]
    public static final String OL = "[\\&\\|]";
    //[Operadores Comparativos]
    public static final String OC = "(\\<\\=|\\>\\=|\\!\\=|\\=|\\<|\\>)";
    //[Operador de Asignacion]
    public static final String OA = "\\:\\=";
    //[Apertura de Parentesis]
    public static final String APS = "(\\(|(" + S + "))*";
    //[Cierre de Parentesis]
    public static final String CPS = "(\\)|(" + S + "))*";
    //[Simbolos Admitidos]
    public static final String SAC = "(\\!|\\#|\\$|\\%|\\&|\\(|\\)|\\=|\\?|\\¡|\\¿|\\*|\\+|\\[|\\]|\\{|\\}|\\^|\\:|\\;|\\,|\\.|\\-|\\_|\\<|\\>|\\\\)*";

//TOKENS COMPUESTOS    
    //[Cadena Simple]
    public static final String CS = "((\\\")((\\w|ñ|Ñ|\\s|(" + SAC + "))*)(\\\"))|(" + ID + ")";
    //[Cadena Compuesta]
    public static final String CC = "(" + CS + "|" + N + "|" + CONJ + ")(" + S + ")((\\+)(" + S + ")(" + CS + "|" + N + "|" + CONJ + "))*";
    //[Operacion de Enteros]
    public static final String OE = "(" + APS + ")(" + ID + "|" + E + "|" + CONJ + ")(" + CPS + ")((" + OM + ")(" + APS + ")(" + ID + "|" + E + "|" + CONJ + ")(" + CPS + "))+";
    //[Operacion de decimales]
    public static final String OD = "(" + APS + ")(" + ID + "|" + D + "|" + CONJ + ")(" + CPS + ")((" + OM + ")(" + APS + ")(" + ID + "|" + D + "|" + CONJ + ")(" + CPS + "))+";
    //[Operacion numerica]
    public static final String ON = "(" + OE + ")|(" + OD + ")";
    //[Comparacion]
    public static final String COM = "(" + APS + ")(" + ID + "|" + N + "|" + CONJ + "|" + ON + ")(" + CPS + ")((" + OC + ")(" + APS + ")(" + ID + "|" + N + "|" + CONJ + "|" + ON + ")(" + CPS + "))+";
    //[Comparacion Logica]
    public static final String COML = "(" + COM + ")((" + OL + ")(" + COM + "))+";
    //[Operacion]
    public static final String O = "(" + ON + ")|(" + COM + ")|(" + COML + ")";
    //[Condicion]
    public static final String CON = "(" + ID + ")|(" + B + ")|(" + COM + ")|(" + COML + ")";

    //[Atributos función]
    public static final String AFUN = "(" + ID + ")(" + S + ")((,)(" + S + ")(" + ID + "))*";
    //[Función]
    public static final String FUN = "(" + ID + ")(" + S + ")(\\()?(" + S + ")(" + AFUN + ")?(" + S + ")(\\))?";

    //[Asignacion Entera]
    public static final String AINT = "(" + ID + ")(" + S + ")(" + OA + ")(" + S + ")(" + E + "|" + ID + "|" + CONJ + "|" + OE + "|" + FUN + ")";
    //[Asignacion Decimal]
    public static final String AFLO = "(" + ID + ")(" + S + ")(" + OA + ")(" + S + ")(" + D + "|" + ID + "|" + CONJ + "|" + OD + "|" + FUN + ")";
    //[Asignacion numerica]
    public static final String ANUM = "(" + AINT + ")|(" + AFLO + ")";
    //[Asignacion Vector 1]
    public static final String AVEC1 = "(" + VC + ")(" + S + ")(" + OA + ")(" + S + ")(" + N + "|" + ID + "|" + CONJ + "|" + OE + "|" + CC + "|" + FUN + ")";
    //[Asignacion Matriz 1]
    public static final String AMTZ1 = "(" + MZ + ")(" + S + ")(" + OA + ")(" + S + ")(" + N + "|" + ID + "|" + CONJ + "|" + OE + "|" + CC + "|" + FUN + ")";
    //[Asignacion vector 2]
    public static final String AVEC2 = "(" + ID + ")(" + S + ")(" + OA + ")(" + S + ")(\\{)(((" + S + ")(" + N + "|" + ID + "|" + CONJ + "|" + OE + "|" + CC + ")(" + S + ")(\\,(" + S + ")(" + N + "|" + ID + "|" + CONJ + "|" + OE + "|" + CC + ")(" + S + "))*)|)(\\})";
    //[Asignacion Matriz 2]
    public static final String AMTZ2 = "(" + ID + ")(" + S + ")(" + OA + ")(" + S + ")(\\{)((\\{(" + N + "|" + ID + "|" + CONJ + "|" + OE + "|" + CC + ")(\\,(" + N + "|" + ID + "|" + CONJ + "|" + OE + "|" + CC + "))*(\\}))(\\,(\\{(" + N + "|" + ID + "|" + CONJ + "|" + OE + "|" + CC + ")(\\,(" + N + "|" + ID + "|" + CONJ + "|" + OE + "|" + CC + "))*(\\})))*)(\\})";
    //[Asignacion Vector]
    public static final String AVEC = "(" + AVEC1 + ")|(" + AVEC2 + ")";
    //[Asignacion Matriz]
    public static final String AMTZ = "(" + AMTZ1 + ")|(" + AMTZ2 + ")";
    //[Asignacion Booleana]
    public static final String ABOO = "(" + ID + ")(" + S + ")(" + OA + ")(" + S + ")(" + ID + "|" + CON + "|" + FUN + ")";
    //[Asignacion de texto]
    public static final String ATXT = "(" + ID + ")(" + S + ")(" + OA + ")(" + S + ")(" + CC + "|" + ID + "|" + FUN + ")";
    //[Atributos FOR]
    public static final String ATBF = "(" + ANUM + ")(" + S + ")(\\,)(" + S + ")(\\-|\\+)(" + N + ")(" + S + ")(\\,)(" + S + ")(" + ON + "|" + ID + "|" + N + "|" + COM + ")";
    //[Asignación v-función]
    public static final String ASIGF = "(" + ID + ")(" + S + ")(" + OA + ")(" + S + ")(" + FUN + ")";
    //[Asignación arreglos-función]
    public static final String ACFUN = "(" + CONJ + ")(" + S + ")(" + OA + ")(" + S + ")(" + FUN + ")";
    //[Asignacion]
    public static final String ASIG = "(" + ANUM + ")|(" + ABOO + ")|(" + ATXT + ")|(" + AVEC + ")|(" + AMTZ + ")";

//EXPRESIONES    
    //[Linea Vacia]
    public static final String EMPL = "(" + IL + ")(" + FL + ")";
    //[Exp Asig Entera]
    public static final String EASIGE = "(" + IL + ")(" + AINT + ")(" + S + ")(\\;)(" + FL + ")";
    //[Exp Asig decimal]
    public static final String EASIGD = "(" + IL + ")(" + AFLO + ")(" + S + ")(\\;)(" + FL + ")";
    //[Exp Asig numerica]
    public static final String EASIGN = "(" + IL + ")(" + ANUM + ")(" + S + ")(\\;)(" + FL + ")";
    //[Exp Asig vector 1]
    public static final String EAVEC1 = "(" + IL + ")(" + AVEC1 + ")(" + S + ")(\\;)(" + FL + ")";
    //[Exp Asig vector 2]
    public static final String EAVEC2 = "(" + IL + ")(" + AVEC2 + ")(" + S + ")(\\;)(" + FL + ")";
    //[Numeros Enteros]
    public static final String EAMTZ1 = "(" + IL + ")(" + AMTZ1 + ")(" + S + ")(\\;)(" + FL + ")";
    //[Exp Asig matriz 1]
    public static final String EAMTZ2 = "(" + IL + ")(" + AMTZ2 + ")(" + S + ")(\\;)(" + FL + ")";
    //[Exp Asig boolena]
    public static final String EASIGB = "(" + IL + ")(" + ABOO + ")(" + S + ")(\\;)(" + FL + ")";
    //[Exp Asig texto]
    public static final String EASIGC = "(" + IL + ")(" + ATXT + ")(" + S + ")(\\;)(" + FL + ")";
    //[Exp asig variable-función]
    public static final String EASIGF = "(" + IL + ")(" + S + ")(" + ASIGF + ")(\\;)(" + FL + ")";
    //[Exp asignación arreglo-función]
    public static final String EACFUN = "(" + IL + ")(" + S + ")(" + ACFUN + ")(\\;)(" + FL + ")";
    //[Exp Asignacion]
    public static final String EASIG = "(" + IL + ")(" + ASIG + ")(" + S + ")(\\;)(" + FL + ")";
    //[Exp Condicional]
    public static final String ECOND = "(" + IL + ")(" + IF + ")(" + S + ")(\\()?(" + S + ")(" + CON + ")(" + S + ")(\\))?(" + S + ")(\\:)(" + FL + ")";
    //[Exp Sino]
    public static final String ESINO = "(" + IL + ")(" + ELSE + ")(" + S + ")(\\:)(" + FL + ")";
    //[Exp mientras]
    public static final String EMIEN = "(" + IL + ")(" + WHILE + ")(" + S + ")(\\()?(" + S + ")(" + CON + ")(" + S + ")(\\))?(" + S + ")(\\:)(" + FL + ")";
    //[Exp para]
    public static final String EPARA = "(" + IL + ")(" + FOR + ")(" + S + ")(\\()?(" + S + ")(" + ATBF + ")(" + S + ")(\\))?(" + S + ")(\\:)(" + FL + ")";
    //[Exp apertura de funciónes]
    public static final String EFUNC = "(" + IL + ")(" + FUN + ")(" + S + ")(\\:)(" + FL + ")";
    //[Exp llamada a función]
    public static final String EIFUN = "(" + IL + ")(" + FUN + ")(" + S + ")(\\;)(" + FL + ")";
    //[Exp apertura]
    public static final String EOPEN = "(" + ECOND + ")|(" + ESINO + ")|(" + EMIEN + ")|(" + EPARA + ")|(" + EFUNC + ")";
    //[Exp leer]
    public static final String ELEER = "(" + IL + ")(" + READ + ")(" + S + ")(\\()?(" + S + ")(" + ID + "|" + CONJ + ")(" + S + ")(\\))?(" + S + ")(\\;)(" + FL + ")";
    //[Exp escribir]
    public static final String EIMPR = "(" + IL + ")(" + PRINT + ")(" + S + ")(\\()?(" + S + ")(" + CC + ")(" + S + ")(\\))?(" + S + ")(\\;)(" + FL + ")";
    //[Exp retorno]
    public static final String ERETO = "(" + IL + ")(" + RETURN + ")(" + S + ")(" + ID + "|" + N + "|" + CC + "|" + O + "|" + B + "|" + FUN + ")(" + S + ")(\\;)(" + FL + ")";
    //[Exp FIN]
    public static final String EEND = "(" + IL + ")(" + END + ")(" + FL + ")";
    //[Exp cierre]
    public static final String CLOSE = "(" + ERETO + ")|(" + EEND + ")";
    //[Exp comenzar]
    public static final String ESTART = "(" + IL + ")(" + START + ")(" + FL + ")";
    //[Exp finalizar]
    public static final String EFINALIZE = "(" + IL + ")(" + FINALIZE + ")(" + FL + ")";

}
