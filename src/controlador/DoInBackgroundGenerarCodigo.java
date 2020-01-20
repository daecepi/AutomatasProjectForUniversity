/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     * @param pane
     * @param codigo
     */
    public DoInBackgroundGenerarCodigo(JTextPane ep, List<String> codigo) {
        this.codigo = codigo;
        this.pane = ep;
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
            
            pane.setText("New file with code in C generated");
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
