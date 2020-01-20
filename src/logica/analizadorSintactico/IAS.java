package logica.analizadorSintactico;

import logica.analizadorLexico.IDefaultToken;
import java.util.List;

/**
 *
 * @author David E Cermeño Pinzon
 */
public interface IAS {

    public List<String> verificarCodigo(List<IDefaultToken> tokens);
}
