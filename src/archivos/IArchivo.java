package archivos;

import java.io.File;

/**
 * Interfaz que proporciona los métodos necesarios para leer o escribir
 * arcvhivos XMI.
 *
 * @author Pedro
 */
public interface IArchivo<T> {

    /**
     * Método para cargar un archivo XMI.
     *
     * @param file, representa al archivo a cargar.
     * @return T.
     */
    public T leer(File file);

    /**
     * Método para cargar un archivo XMI.
     *
     * @param path, representa la ruta del archivos.
     * @return T.
     */
    public T leer(String path);

    /**
     * Método para guardar un archivo XMI.
     *
     * @param t, los datos a guardar.
     * @param file, el archivo donde se guardarán los datos.
     * @return boolean
     */
    public boolean escribir(T t, File file);

    /**
     * Método para guardar un archivo XMI.
     *
     * @param t, los datos a guardar.
     * @param path, la ruta del archivo donde se van a guardar los datos.
     * @return bollean
     */
    public boolean escribir(T t, String path);

}
