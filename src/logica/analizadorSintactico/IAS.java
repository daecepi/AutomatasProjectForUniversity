package logica.analizadorSintactico;

import logica.analizadorLexico.IDefaultToken;
import java.util.List;

/**
 *
 * @author Pedro
 */
public interface IAS {

    public List<String> verificarCodigo(List<IDefaultToken> tokens);
}
