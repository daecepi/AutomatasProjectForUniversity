package archivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David E Cermeño Pinzon
 */
public class ArchivosPlanos implements IArchivo<String> {

    private String encoding;

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public ArchivosPlanos() {
        this.encoding = "ISO-8859-1";
    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    @Override
    public String leer(File file) {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), this.encoding));

            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivosPlanos.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "NA";
        } catch (IOException ex) {
            Logger.getLogger(ArchivosPlanos.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "NA";
        }
    }

    @Override
    public String leer(String path) {
        return this.leer(new File(path));
    }

    @Override
    public boolean escribir(String t, File file) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file.getAbsolutePath()),
                    this.encoding));
            bw.write(t);
            bw.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ArchivosPlanos.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean escribir(String t, String path) {
        return this.escribir(t, new File(path));
    }
//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

}
