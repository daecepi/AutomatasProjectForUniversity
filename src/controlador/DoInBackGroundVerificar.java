package controlador;

import logica.ControlDeArchivos;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Pedro
 */
public class DoInBackGroundVerificar extends SwingWorker<Void, Void>{

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
    public DoInBackGroundVerificar(JTextPane pane, List<String> codigo) {
        this.codigo = codigo;
        this.pane = pane;
    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    @Override
        protected Void doInBackground() throws Exception {
            pane.setText("Verificando...");
            List<String> errores = new ControlDeArchivos().verificarCódigo(codigo);
            
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
