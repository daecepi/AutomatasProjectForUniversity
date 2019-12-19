package controlador;

import java.io.File;
import java.util.List;
import javax.swing.JTextPane;

/**
 *
 * @author Pedro
 * 
 */
public class ControladorDeArchivos {

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public ControladorDeArchivos() {
        
    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    /**
     *
     * @param t
     * @param file
     */
    public void guardar(String t, File file) {
        new DoInBackGroundGuardar(t, file).execute();
    }

    /**
     *
     * @param ep
     * @param file
     */
    public void abrir(JTextPane ep, File file) {
        new DoInBackGroundAbrir(ep, file).execute();
    }

    /**
     *
     * @param ep
     * @param codigo
     */
    public void verificar(JTextPane ep, List<String> codigo) {
        new DoInBackGroundVerificar(ep, codigo).execute();
    }
//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================

}
