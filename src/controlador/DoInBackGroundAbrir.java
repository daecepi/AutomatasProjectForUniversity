package controlador;

import logica.ControlDeArchivos;
import java.io.File;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;

/**
 * Clase que extiende de la clase SwingWorker, para cargar el archivo en un
 * hilo aparte, con el fin de no comprometer el hilo principal de la aplicación.
 *
 * @author David E Cermeño Pinzon
 */
public class DoInBackGroundAbrir extends SwingWorker<Void, Void> {

    private final File file;
    private final JTextPane pane;

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor general.
     *
     * @param ep
     * @param file
     */
    public DoInBackGroundAbrir(JTextPane ep, File file) {
        this.file = file;
        this.pane = ep;
    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    @Override
    protected Void doInBackground() throws Exception {
        this.pane.setText(new ControlDeArchivos()
                .abrirArchivo(file.getAbsolutePath()));
        return null;
    }
//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================
}
