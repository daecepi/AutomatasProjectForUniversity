package vista.utilidades;

import java.awt.Color;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author Pedro
 */
public class TextStyle extends DefaultStyledDocument {

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    private Color keyWordColor = new Color(0, 51, 142);
    private Color VariableColor = new Color(0, 148, 14);
    private Color defaultColor = Color.BLACK;
//    private Color comentColor = new Color(128, 0, 128);
//    private Color stringColor = Color.RED;

    private String keyWords_Regex = "(\\W)*((F(ALSO)?|V(ERDADERO)?|IMPRIMIR|MOSTRAR|ESCRIBIR)"
            + "|RETORNO|PARA|MIENTRAS|SI|SINO|LEER|(INICIO|EMPEZAR|COMENZAR)|(FINAL"
            + "|TERMINAR|FIN|FINALIZAR)|)";
//    private String coment_Regex = "(\\W)*((\\s*)((\\#)(.*)))";
    private String variable_Regex = "(\\W)*((\\$)([a-zA-Z])(\\w|[\\_])*)";

//    private String string_Regex = "(?<=\\\")(.*\\s*)(?=\\\")";
    private final StyleContext cont = StyleContext.getDefaultStyleContext();
    private final AttributeSet attrKeyW = cont.addAttribute(cont.getEmptySet(),
            StyleConstants.Foreground, keyWordColor);
    private final AttributeSet attrDef = cont.addAttribute(cont.getEmptySet(),
            StyleConstants.Foreground, defaultColor);
//    private final AttributeSet attrComent = cont.addAttribute(cont.getEmptySet(),
//            StyleConstants.Foreground, comentColor);
    private final AttributeSet attrVar = cont.addAttribute(cont.getEmptySet(),
            StyleConstants.Foreground, VariableColor);
//    private final AttributeSet attrStr = cont.addAttribute(cont.getEmptySet(),
//            StyleConstants.Foreground, stringColor);

    /**
     * Constructor por defecto.
     */
    public TextStyle() {

    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    private int findLastNonWordChar(String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    private int findFirstNonWordChar(String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
        super.insertString(offset, str, a);

        String text = getText(0, getLength());
        int before = findLastNonWordChar(text, offset);
        if (before < 0) {
            before = 0;
        }
        int after = findFirstNonWordChar(text, offset + str.length());
        int wordL = before;
        int wordR = before;

        while (wordR <= after) {
            if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                if (text.substring(wordL, wordR).matches(keyWords_Regex)) {
                    setCharacterAttributes(wordL, wordR - wordL, attrKeyW, false);
                } /*else if (text.substring(wordL, wordR).matches(coment_Regex)) {
                 setCharacterAttributes(wordL, wordR - wordL, attrComent, false);
                 }*/ else if (text.substring(wordL, wordR).matches(variable_Regex)) {
                    setCharacterAttributes(wordL, wordR - wordL, attrVar, false);
                } /*else if (text.substring(wordL, wordR).matches(string_Regex)) {
                 setCharacterAttributes(wordL, wordR - wordL, attrStr, false);
                 } */ else {
                    setCharacterAttributes(wordL, wordR - wordL, attrDef, false);
                }
                wordL = wordR;
            }
            wordR++;
        }
    }

    @Override
    public void remove(int offs, int len) throws BadLocationException {
        super.remove(offs, len);

        String text = getText(0, getLength());
        int before = findLastNonWordChar(text, offs);
        if (before < 0) {
            before = 0;
        }
        int after = findFirstNonWordChar(text, offs);

        if (text.substring(before, after).matches(keyWords_Regex)) {
            setCharacterAttributes(before, after - before, attrKeyW, false);
        } /*else if (text.substring(before, after).matches(coment_Regex)) {
         setCharacterAttributes(before, after - before, attrComent, false);
         } */ else if (text.substring(before, after).matches(variable_Regex)) {
            setCharacterAttributes(before, after - before, attrVar, false);
        } /*else if (text.substring(before, after).matches(string_Regex)) {
         setCharacterAttributes(before, after - before, attrStr, false);
         } */ else {
            setCharacterAttributes(before, after - before, attrDef, false);
        }
    }
//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================

    public Color getKeyWordColor() {
        return keyWordColor;
    }

    public void setKeyWordColor(Color keyWordColor) {
        this.keyWordColor = keyWordColor;
    }

    public Color getcVar() {
        return VariableColor;
    }

    public void setcVar(Color cVar) {
        this.VariableColor = cVar;
    }

    public Color getDefColor() {
        return defaultColor;
    }

    public void setDefColor(Color defColor) {
        this.defaultColor = defColor;
    }

    public String getKeywords() {
        return keyWords_Regex;
    }

    public void setKeywords(String keywords) {
        this.keyWords_Regex = keywords;
    }

    public String getVariable() {
        return variable_Regex;
    }

    public void setVariable(String variable) {
        this.variable_Regex = variable;
    }

}
