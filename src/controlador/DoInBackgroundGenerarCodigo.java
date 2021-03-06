/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Component;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;
import javax.swing.text.BadLocationException;
import logica.ControlDeArchivos;

/**
 *
 * @author davidcermeno
 */
public class DoInBackgroundGenerarCodigo extends SwingWorker<Void, Void>{
    private final List<String> codigo;
    private final JTextPane pane;
    private String lenguaje;
    private Component contexto;

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     * @param contexto : maneja el contexto del cualquier Pop-up
     * @param pane : Maneja el contexto del Panel de texto donde se encuentra el codigo
     * @param codigo : maneja el conjunto de token obtenido del codigo
     * @param lenguaje : mantiene
     */
    public DoInBackgroundGenerarCodigo(Component contexto, JTextPane ep, List<String> codigo, String lenguaje) {
        this.contexto = contexto;
        this.codigo = codigo;
        this.pane = ep;
        System.out.println("Contructor"+lenguaje);
        this.lenguaje = lenguaje;
    }
    
    
//==============================================================================
//  Métodos principales.==
//==============================================================================
    @Override
    protected Void doInBackground() throws Exception {
        pane.setText("Generando...");
        List<String> errores = new ControlDeArchivos().verificarCódigo(codigo);

        if(errores.size() > 1){
            pane.setText(errores.size() + " error(es) encontrados.\n");
            errores.stream().forEach(ln -> {
                try {
                    pane.getDocument().insertString(pane.getDocument()
                            .getLength(), "=============================\n",
                            null);
                    pane.getDocument().insertString(pane.getDocument()
                            .getLength(), ln + "\n", null);
                } catch (BadLocationException ex) {
                    Logger.getLogger(ControladorDeArchivos.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            });
        }else{
            //String nameOfFileGotten = JOptionPane.showInputDialog(this.contexto, pane, lenguaje);
            System.out.println("Entrando en la generacion");
            String codeGenerated = new ControlDeArchivos().codeGenerate(codigo, lenguaje);
            System.out.println("Code "+codigo);
            // new ControlDeArchivos().guardarArchivo(codeGenerated, System.getProperty("user.home") + "/Desktop/" + nameOfFileGotten + new ControlArchivos().getExtension(lenguaje));
            // pane.setText("New file with code in C generated");
        }
        return null;
    }
//==============================================================================
//  Métodos secundarios.
//==============================================================================
//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================
}
