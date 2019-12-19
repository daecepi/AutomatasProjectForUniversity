package controlador;

import logica.ControlDeArchivos;
import java.io.File;
import javax.swing.SwingWorker;

/**
 *
 * @author Pedro
 * 
 */
public class DoInBackGroundGuardar extends SwingWorker<Void, Void> {

    private final File file;
    private final String text;

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor general.
     *
     * @param t
     * @param file
     */
    public DoInBackGroundGuardar(String t, File file) {
        this.file = file;
        this.text = t;
    }

//==============================================================================
//  Métodos principales.
//============================================================================== 
    @Override
    protected Void doInBackground() throws Exception {
        new ControlDeArchivos().guardarArchivo(text, file.getAbsolutePath());
        return null;
    }
//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================

}
